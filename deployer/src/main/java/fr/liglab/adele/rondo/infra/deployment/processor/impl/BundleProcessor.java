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
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
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
import java.util.List;
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

    @Override
    public boolean check(ResourceDeclaration resource) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public class BundleDeploymentParticipant extends DefaultDeploymentParticipant {

        private final Bundle m_bundleDef;

        private File cacheDirectory = null;

        private File cachedBundle = null;

        private ResourceState initialBundleState;

        private Resource bundle = null;

        public BundleDeploymentParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(resource,transaction);
            if (resource instanceof Bundle) {
                m_bundleDef = (Bundle) resource;
                transaction.extendTimeout(3000);
            } else {
                throw new DeploymentException("Received resource " + resource.id() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void prepare() throws DeploymentException {
            Resource bundle = findBundle(m_bundleDef);

            if(m_bundleDef.source()!=null){
                // prepare cache directory
                File directory = (File) this.get("working.dir");
                cacheDirectory = new File(directory, "bundle-" + m_bundleDef.id());
                cacheDirectory.mkdirs();
                // first find the resource corresponding to bundle declaration
                if (bundle != null) {  // if you found it download & verify it from bundle-location
                    String bundleLocation = bundle.getMetadata().get("bundle-location", String.class);
                    File initialBundleFile = this.downloadAndVerifyBundle(cacheDirectory, m_bundleDef, bundleLocation);
                    if(initialBundleFile!= null){
                        this.initialBundleState = new ResourceState(initialBundleFile,bundle);
                    }
                } else if (this.initialBundleState==null) { // if you don't, download & verify it from bundle declaration source
                    this.cachedBundle = this.downloadAndVerifyBundle(cacheDirectory, m_bundleDef, m_bundleDef.source());
                }
            } else {
                // we only check for the bundle
                if(bundle==null){
                    throw new DeploymentException("Any source was given, cannot find corresponding bundle");
                }
                Version version = bundle.getMetadata().get(Constants.BUNDLE_VERSION_ATTRIBUTE, Version.class);
                if(m_bundleDef.version()!=null && !version.equals(Version.parseVersion(m_bundleDef.version()))){
                    throw new DeploymentException("Any source was given, given bundle-version doesn't correspond to the found bundle. found: "
                            +version.toString()+ " expected: "+m_bundleDef.version());
                }
                this.initialBundleState = new ResourceState(null,bundle);
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
                Map<String, Object> updateParams = new HashMap<String, Object>();
                if(initialBundleState!=null){
                    bundle = m_everest.process(new DefaultRequest(Action.READ, initialBundleState.getPath(), null));
                    Version version = bundle.getMetadata().get(Constants.BUNDLE_VERSION_ATTRIBUTE, Version.class);
                    if(m_bundleDef.version()!=null && !version.equals(Version.parseVersion(m_bundleDef.version()))){
                        updateParams.put("update",true);
                        updateParams.put("input",new ByteArrayInputStream(FileUtils.readFileToByteArray(this.cachedBundle)));
                    }
                }
                if(bundle==null){
                    if (this.cachedBundle == null || !this.cachedBundle.exists()) {
                        throw new DeploymentException("Bundle did not existed and we could not prepared it");
                    }
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("location", m_bundleDef.source());
                    params.put("input", new ByteArrayInputStream(FileUtils.readFileToByteArray(this.cachedBundle)));
                    bundle = m_everest.process(new DefaultRequest(Action.CREATE, Path.from("/osgi/bundles"), params));
                    if (bundle == null) {
                        throw new DeploymentException("Error on resource creating from source: " + m_bundleDef.source());
                    }
                }
                // Bundle was already installed or just installed, try to update to given state
                updateParams.put("newState", m_bundleDef.state());
                Path canonicalPath = bundle.getCanonicalPath();
                System.out.println(canonicalPath.toString()+" "+updateParams);
                bundle = m_everest.process(new DefaultRequest(Action.UPDATE, canonicalPath, updateParams));
                if (bundle == null) {
                    throw new DeploymentException("Error on resource updating on path: " + canonicalPath);
                }
                // Last exit before hell!
                //TODO can check bundle properties
                if(!m_bundleDef.state().equals(bundle.getMetadata().get("bundle-state", String.class))){
                    throw new DeploymentException("Cannot reach expected state: " + m_bundleDef.name());
                }
            } catch (ResourceNotFoundException e) {
                throw new DeploymentException("Cannot find Everest bundle resource:" + e.getMessage());
            } catch (IllegalActionOnResourceException e) {
                throw new DeploymentException("Illegal action on Everest bundle resource:" + e.getMessage());
            } catch (FileNotFoundException e) {
                throw new DeploymentException(e.getMessage() + " from " + m_bundleDef.source());
            } catch (IOException e) {
                throw new DeploymentException(e.getMessage() + " from " + m_bundleDef.source());
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
            File cachedFile = null;
            try {
                // create file for cache and save url contents
                URL url = new URL(urlString);
                String fileName = calculateFileName(url);
                cachedFile = new File(directory, fileName);
                FileUtils.copyURLToFile(url,cachedFile);
            } catch (MalformedURLException e) {
                throw new DeploymentException("Malformed url " + bundleDef.source()+" : "+e.getMessage());
            } catch (IOException e) {
                throw new DeploymentException("Cannot get resource file from " + bundleDef.source()+" : "+e.getMessage());
            }
            //verify checksum if it is set in properties
            //TODO find a way to take checksum algo as parameter. SHA1, SHA-256,..
            if (bundleDef.properties().containsKey("checksum")) {
                Object checksum = bundleDef.properties().get("checksum");
                try {
                    String fileChecksum = DeploymentUtils.checksum(cachedFile, "SHA1");
                    if (!fileChecksum.equals(checksum)) {
                        throw new DeploymentException("Checksum failed : found: " + fileChecksum + ", expected: " + checksum);
                    }
                } catch (NoSuchAlgorithmException e) {
                    // log
                    System.out.println("Failed to find the checksum algorithm " + "SHA1");
                } catch (IOException e) {
                    throw new DeploymentException("Failed at looking the checksum: "+e.getMessage());
                }
            }

            try {
                // check if the file contains the bundle with correct manifest
                JarFile jarFile = new JarFile(cachedFile);
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
                return cachedFile;
            }catch (IOException e) {
                // log
                throw new DeploymentException("Error handling cached file: "+e.getMessage());
            }
        }

        private Resource findBundle(Bundle bundleDef) {
            Resource bundle = null;
            // return fast
            if(bundleDef.symbolicName()==null){
                return null;
            }
            try{
                Resource bundles = m_everest.process(new DefaultRequest(Action.READ, Path.from("/osgi/bundles"), null));
                List<Resource> matchingBundles = bundles.getResources(new BundleResourceFilter(bundles.getCanonicalPath(),bundleDef));
                if(!matchingBundles.isEmpty()){
                    bundle = matchingBundles.get(0);
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
                fileName = fileName.concat(".jar");
            }
            return fileName;
        }

    }

    private class BundleResourceFilter implements ResourceFilter {

        final Bundle bundleDeclaration;
        private final Path parentPath;

        private BundleResourceFilter(Path parentPath, Bundle bundleDeclaration) {
            this.parentPath = parentPath;
            this.bundleDeclaration = bundleDeclaration;
        }

        @Override
        public boolean accept(Resource resource) {
            if(!parentPath.equals(resource.getCanonicalPath().getParent())){
                return false;
            }
            String symbolicName = resource.getMetadata().get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class);
            return symbolicName.equals(bundleDeclaration.symbolicName());
        }
    }

}
