package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceState;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.deployment.util.DeploymentUtils;
import fr.liglab.adele.rondo.infra.model.Bundle;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.impl.ImmutableResourceMetadata;
import org.apache.felix.ipojo.everest.services.*;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/30/13
 * Time: 9:38 AM
 */

@Component
@Instantiate
@Provides(specifications = ResourceProcessor.class)
public class BundleProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    public EverestService m_everest;

    @ServiceProperty(name = "resource.type", value = "fr.liglab.adele.rondo.infra.model.Bundle")
    public final String m_resourceType = "fr.liglab.adele.rondo.infra.model.Bundle";

    BundleContext m_context;

    public BundleProcessor(BundleContext context) {
        super();
        this.m_context = context;
    }

    @Override
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
        BundleDeploymentParticipant participant = new BundleDeploymentParticipant(resource, transaction);
        this.addParticipant(participant);
        return participant;
    }

    public class BundleDeploymentParticipant extends DefaultDeploymentParticipant {

        private final Bundle bundleDef;

        private File cacheDirectory = null;

        private File cachedBundle = null;

        private ResourceState initialBundleState;

        private Resource bundle = null;

        public BundleDeploymentParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(resource,transaction);
            if (resource instanceof Bundle) {
                bundleDef = (Bundle) resource;
            } else {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void prepare() throws DeploymentException {
            // prepare cache directory
            File directory = (File) this.get("working.dir");
            cacheDirectory = new File(directory, "bundle-" + bundleDef.name());
            cacheDirectory.mkdirs();
            // first find the resource corresponding to bundle declaration
            Resource bundle = findBundle();
            if (bundle != null) {  // if you found it download & verify it from bundle-location
                String bundleLocation = bundle.getMetadata().get("bundle-location", String.class);
                File initialBundleFile = this.downloadAndVerifyBundle(cacheDirectory, bundleDef, bundleLocation);
                if(initialBundleFile!= null){
                    this.initialBundleState = new ResourceState(initialBundleFile,bundle);
                }
            } else if (this.initialBundleState==null) { // if you don't, download & verify it from bundle declaration source
                this.cachedBundle = this.downloadAndVerifyBundle(cacheDirectory, bundleDef, bundleDef.source());
            }
        }

        @Override
        public void cleanup() {
            try {
                FileUtils.deleteDirectory(cacheDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void commit() throws DeploymentException {
            try {
                if(initialBundleState!=null){
                    bundle = initialBundleState.getResourceUsing(m_everest);
                }
                if(bundle==null){
                    if (this.cachedBundle == null || !this.cachedBundle.exists()) {
                        throw new DeploymentException("Bundle did not existed and we could not prepared it");
                    }
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("location", bundleDef.source());
                    params.put("input", new ByteArrayInputStream(FileUtils.readFileToByteArray(this.cachedBundle)));
                    bundle = m_everest.process(new DefaultRequest(Action.CREATE, Path.from("/osgi/bundles"), params));
                    if (bundle == null) {
                        throw new DeploymentException("Error on resource creating from source : " + bundleDef.source());
                    }
                }
                // Bundle was already installed or just installed, try to update to given state
                Map<String, Object> updateParams = new HashMap<String, Object>();
                updateParams.put("newState", bundleDef.state());
                Path canonicalPath = bundle.getCanonicalPath();
                bundle = m_everest.process(new DefaultRequest(Action.UPDATE, canonicalPath, updateParams));
                if (bundle == null) {
                    throw new DeploymentException("Error on resource updating on path : " + canonicalPath);
                }
                // Last exit before hell!
                //TODO can check bundle properties
                if(!bundleDef.state().equals(bundle.getMetadata().get("bundle-state", String.class))){
                    throw new DeploymentException("Cannot reach expected state " + bundleDef.name());
                }
            } catch (ResourceNotFoundException e) {
                throw new DeploymentException("Cannot find Everest bundle resource:" + e.getMessage());
            } catch (IllegalActionOnResourceException e) {
                throw new DeploymentException("Illegal action on Everest bundle resource:" + e.getMessage());
            } catch (FileNotFoundException e) {
                throw new DeploymentException(e.getMessage() + " from " + bundleDef.source());
            } catch (IOException e) {
                throw new DeploymentException(e.getMessage() + " from " + bundleDef.source());
            }
        }

        @Override
        public void rollback() {
            try {
                if (initialBundleState == null) {
                    m_everest.process(new DefaultRequest(Action.DELETE, bundle.getCanonicalPath(), null));
                } else {
                    Map<String, Object> updateParams = new HashMap<String, Object>();
                    updateParams.put("newState", initialBundleState.getResourceState().get("bundle-state"));
                    updateParams.put("input", initialBundleState.getFile());
                    m_everest.process(new DefaultRequest(Action.UPDATE, initialBundleState.getPath(), updateParams));
                }
            } catch (IllegalActionOnResourceException e) {
                System.out.println("failed to roll back");
            } catch (ResourceNotFoundException e) {
                System.out.println("failed to roll back");
            }
        }

        private File downloadAndVerifyBundle(File directory, Bundle bundleDef, String urlString) throws DeploymentException {
            try {
                // create file for cache and save url contents
                URL url = new URL(urlString);
                String fileName = calculateFileName(url);
                File jarCache = new File(directory, fileName);
                IOUtils.copy(url.openStream(), new FileOutputStream(jarCache));

                //verify checksum if it is set in properties
                //TODO find a way to take checksum algo as parameter. SHA1, SHA-256,..
                if (bundleDef.properties().containsKey("checksum")) {
                    Object checksum = bundleDef.properties().get("checksum");
                    try {
                        String fileChecksum = DeploymentUtils.checksum(jarCache, "SHA1");
                        if (!fileChecksum.equals(checksum)) {
                            throw new DeploymentException("Checksum failed : found: " + fileChecksum + ", expected: " + checksum);
                        }
                    } catch (NoSuchAlgorithmException e) {
                        // log
                        System.out.println("Failed to find the checksum algorithm " + "SHA1");
                    }
                }
                // check if the file contains the bundle with correct manifest
                JarFile jarFile = new JarFile(jarCache);
                Manifest manifest = jarFile.getManifest();
                Attributes mainAttributes = manifest.getMainAttributes();
                Object headers = bundleDef.properties().get("headers");
                if (headers instanceof Map) {
                    Map<Object, Object> map = (Map) headers;
                    for (Map.Entry entry : map.entrySet()) {
                        String keyString = entry.getKey().toString();
                        if (mainAttributes.getValue(keyString) != null) {
                            if (!mainAttributes.getValue(keyString).equals(bundleDef.properties().get(keyString))) {
                                // FAIL! should delete the downloaded file
                                FileUtils.deleteDirectory(directory);
                                throw new DeploymentException("Bundle header : " + keyString + "  found in the manifest of bundle "
                                        + urlString + " doesn't match given value :" + mainAttributes.getValue(keyString) + ", expected :" + entry.getValue());
                            }
                        }
                    }
                }
                return jarCache;
            } catch (MalformedURLException e) {
                throw new DeploymentException("Malformed url " + bundleDef.source()+" : "+e.getMessage());
            } catch (IOException e) {
                throw new DeploymentException("Cannot get resource file from " + bundleDef.source()+" : "+e.getMessage());
            }
        }

        private Resource findBundle() {
            Resource bundle = null;
            try{
                Resource bundles = m_everest.process(new DefaultRequest(Action.READ, Path.from("/osgi/bundles"), null));
                Iterator<Resource> iterator = bundles.getResources().iterator();
                while (iterator.hasNext() && bundle == null) {
                    Resource next = iterator.next();
                    if (new ResourceFilter() {

                        public boolean accept(Resource resource) {
                            String symbolicName = resource.getMetadata().get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class);
                            Version version = resource.getMetadata().get(Constants.BUNDLE_VERSION_ATTRIBUTE, Version.class);
                            //String bundleLocation = resource.getMetadata().get("bundle-location", String.class);
                            //String state = resource.getMetadata().get("bundle-state", String.class);
                            return ((bundleDef.symbolicName() == null || symbolicName.equals(bundleDef.symbolicName())) &&
                                    (bundleDef.version() == null || version.equals(new Version(bundleDef.version()))))
                                    ;
                        }
                    }.accept(next)) {
                        bundle = next;
                    }
                }
            } catch (ResourceNotFoundException e) {
                // Well this should not have happened..
            } catch (IllegalActionOnResourceException e) {
                // this should not to happen
            }
            return bundle;
        }

        private String calculateFileName(URL url) {
            String urlString = url.toString();
            // lets look if url finishes with a filename
            String fileName = urlString.substring(urlString.lastIndexOf('/') + 1, urlString.length());
            if (!fileName.endsWith(".jar")) {
                //else we create our file id
                fileName = bundleDef.symbolicName() + "-" + bundleDef.version() + ".jar";
            }
            return fileName;
        }

    }

}
