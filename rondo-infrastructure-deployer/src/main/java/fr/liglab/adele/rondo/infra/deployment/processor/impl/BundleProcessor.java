package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
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
@Provides(specifications = {ResourceProcessor.class})
public class BundleProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    public EverestService m_everest;

    @ServiceProperty(name = "resource.type", value = "fr.liglab.adele.rondo.infra.model.Bundle")
    public final String m_resourceType = "fr.liglab.adele.rondo.infra.model.Bundle";

    BundleContext m_context;

    //private boolean cachingEnabled;

    public BundleProcessor(BundleContext context) {
        super();
        this.m_context = context;
    }

    @Override
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
        BundleDeploymentParticipant participant = new BundleDeploymentParticipant(resource, transaction);
        addParticipant(participant);
        return participant;
    }

    public class BundleDeploymentParticipant extends DefaultDeploymentParticipant {

        private final Bundle bundleDef;

        private File cacheDirectory = null;

        private File cachedBundle = null;

        private ImmutableResourceMetadata initialState = null;

        public BundleDeploymentParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(transaction);
            if (resource instanceof Bundle) {
                bundleDef = (Bundle) resource;
            } else {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void prepare() throws DeploymentException {
            System.out.println("Preparing bundle: " + bundleDef.name());
            File directory = (File) this.get("working.dir");
            cacheDirectory = new File(directory, "bundle-" + bundleDef.name());
            cacheDirectory.mkdirs();
            this.cachedBundle = downloadAndVerifyBundle(cacheDirectory);

            Resource bundle = null;
            try {
                bundle = findBundle();
            } catch (ResourceNotFoundException e) {
                // Well this should not have happened..
            } catch (IllegalActionOnResourceException e) {
                // this should not to happen
            }
            if (bundle != null) {
                this.initialState = new ImmutableResourceMetadata.Builder(bundle.getMetadata()).build();
                // TODO we can save the bundle jar somewhere and do a checksum for verifying if it is exactly the same version
                //File initialCached = initialState.getMetadata().get("local-cache",File.class);
            }

        }

        @Override
        public void cleanup() {
            System.out.println("cleaning up " + bundleDef.symbolicName());
            try {
                FileUtils.deleteDirectory(cacheDirectory);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.println("cached directory cleared : " + cacheDirectory.getPath());
        }

        @Override
        public void commit() throws DeploymentException {
            System.out.println("commiting " + bundleDef.name());
            Resource result = null;
            try {
                // prepare deployment directory
                File deploymentFile = null;
                if (this.cachedBundle == null && !this.cachedBundle.exists()) { // if we don't have the bundle in cache we fail
                    throw new DeploymentException(""); //TODO
                } else {
                    deploymentFile = this.cachedBundle;
                }
                // here on we should have gotten the file to be deployed, or failed miserably
                // try to find the bundle
                Resource bundle = findBundle();
                //TODO should relook here !!!!!!!!!!!!!

                boolean update = false;
                if (bundle != null) { // found at least one installed bundle that corresponds to the declaration
                    // take the first and look at its manifest
                    Resource headersResource = bundle.getResource(bundle.getPath().addElements("headers").toString());
                    ResourceMetadata headers = headersResource.getMetadata();
                    Map<Object, Object> map = (Map) headers;
                    for (Map.Entry entry : map.entrySet()) {
                        String keyString = entry.getKey().toString();
                        if (headers.containsKey(keyString)) {
                            if (!headers.get(keyString).equals(entry.getValue())) {
                                throw new DeploymentException("Bundle property : " + keyString + " found in the manifest of the bundle "
                                        + bundleDef.source() + " doesn't match given value :" + headers.get(keyString) + ", expected :" + entry.getValue());
                            }
                        }
                    }
                    result = bundle;
                    // update the bundle with inputStream
                    update = true;

                } else { // Bundle does not exist, should install it

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("location", bundleDef.source());
                    params.put("input", new ByteArrayInputStream(FileUtils.readFileToByteArray(deploymentFile)));
                    result = m_everest.process(new DefaultRequest(Action.CREATE, Path.from("/osgi/bundles"), params));
                    if (result == null) {
                        throw new DeploymentException("Error on resource creating from source : " + bundleDef.source());
                    }

                }
                this.store(bundleDef.name(), result);
                // had or created, try to update to given state
                Map<String, Object> updateParams = new HashMap<String, Object>();
                if (update) {
                    updateParams.put("input", new ByteArrayInputStream(FileUtils.readFileToByteArray(deploymentFile)));
                    updateParams.put("update", true);
                }
                updateParams.put("newState", bundleDef.state());
                result = m_everest.process(new DefaultRequest(Action.UPDATE, result.getCanonicalPath(), updateParams));
                if (result == null) {
                    throw new DeploymentException("Error on resource updating on path : " + bundle.getCanonicalPath());
                }
                // Last exit before hell!
                //TODO can check bundle properties
                if (!result.equals(checkBundle())) {
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
            System.out.println("rolling back " + bundleDef.name());
            Resource resource = (Resource) this.get(bundleDef.name());
            try {
                if (initialState == null) {
                    if (resource != null) {
                        m_everest.process(new DefaultRequest(Action.DELETE, resource.getCanonicalPath(), null));
                    }
                } else {
                    System.out.println(initialState);
                    Map<String, Object> updateParams = new HashMap<String, Object>();
                    updateParams.put("newState", initialState.get("bundle-state"));
                    m_everest.process(new DefaultRequest(Action.UPDATE, resource.getCanonicalPath(), updateParams));
                }
            } catch (IllegalActionOnResourceException e) {
                //TODO log
                System.out.println("failed to roll back");
            } catch (ResourceNotFoundException e) {
                System.out.println("failed to roll back");
            }

        }

        private File downloadAndVerifyBundle(File directory) throws DeploymentException {
            try {
                // create file for cache and save url contents
                URL url = new URL(bundleDef.source());
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
                System.out.println(jarCache.getCanonicalPath());
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
                                        + bundleDef.source() + " doesn't match given value :" + mainAttributes.getValue(keyString) + ", expected :" + entry.getValue());
                            }
                        }
                    }
                }

                return jarCache;
            } catch (MalformedURLException e) {
                throw new DeploymentException(e.getMessage() + " from " + bundleDef.source());
            } catch (IOException e) {
                throw new DeploymentException(e.getMessage() + " from " + bundleDef.source());
            }
        }

        private Resource findBundle() throws ResourceNotFoundException, IllegalActionOnResourceException {
            Resource bundles = m_everest.process(new DefaultRequest(Action.READ, Path.from("/osgi/bundles"), null));
            Resource bundle = null;
            Iterator<Resource> iterator = bundles.getResources().iterator();
            while (iterator.hasNext() && bundle == null) {
                Resource next = iterator.next();
                if (new ResourceFilter() {
                    @Override
                    public boolean accept(Resource resource) {
                        String symbolicName = resource.getMetadata().get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class);
                        Version version = resource.getMetadata().get(Constants.BUNDLE_VERSION_ATTRIBUTE, Version.class);
                        String bundleLocation = resource.getMetadata().get("bundle-location", String.class);
                        String state = resource.getMetadata().get("bundle-state", String.class);
                        return (!("UNINSTALLED".equals(state))) &&
                                bundleLocation.equals(bundleDef.source()) &&
                                (bundleDef.symbolicName() == null ? true : symbolicName.equals(bundleDef.symbolicName())) &&
                                (bundleDef.version() == null ? true : version.equals(new Version(bundleDef.version())))
                                ;
                    }
                }.accept(next)) {
                    bundle = next;
                }
            }
            return bundle;
        }

        private Resource checkBundle() throws ResourceNotFoundException, IllegalActionOnResourceException {
            Resource bundles = m_everest.process(new DefaultRequest(Action.READ, Path.from("/osgi/bundles"), null));
            Resource bundle = null;
            Iterator<Resource> iterator = bundles.getResources().iterator();
            while (iterator.hasNext() && bundle == null) {
                Resource next = iterator.next();
                if (new ResourceFilter() {
                    @Override
                    public boolean accept(Resource resource) {
                        String symbolicName = resource.getMetadata().get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class);
                        Version version = resource.getMetadata().get(Constants.BUNDLE_VERSION_ATTRIBUTE, Version.class);
                        String bundleLocation = resource.getMetadata().get("bundle-location", String.class);
                        String state = resource.getMetadata().get("bundle-state", String.class);
                        return (bundleDef.state().equals(state)) &&
                                bundleLocation.equals(bundleDef.source()) &&
                                (bundleDef.symbolicName() == null ? true : symbolicName.equals(bundleDef.symbolicName())) &&
                                (bundleDef.version() == null ? true : version.equals(new Version(bundleDef.version())))
                                ;
                    }
                }.accept(next)) {
                    bundle = next;
                }
            }
            return bundle;
        }

        private String calculateFileName(URL url) {
            String urlString = url.toString();
            // lets look if url finishes with a filename
            String fileName = urlString.substring(urlString.lastIndexOf('/') + 1, urlString.length());
            if (!fileName.endsWith(".jar")) {
                //else we create our file name
                fileName = bundleDef.symbolicName() + "-" + bundleDef.version() + ".jar";
            }
            return fileName;
        }

    }

}
