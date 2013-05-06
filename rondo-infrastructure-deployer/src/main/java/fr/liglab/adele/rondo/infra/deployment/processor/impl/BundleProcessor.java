package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.Bundle;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
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
@Provides(specifications = {ResourceProcessor.class})
public class BundleProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    public EverestService m_everest;

    @ServiceProperty(name = "resource.type")
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
            cacheDirectory = new File(directory, bundleDef.name());
            cacheDirectory.mkdirs();
            this.cachedBundle = downloadAndVerifyBundle(cacheDirectory);
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
                File directory = (File) this.get("deployment.dir");
                File deploymentDir = new File(directory, bundleDef.name());
                deploymentDir.mkdirs();
                File deploymentFile = null;
                if (this.cachedBundle != null || !this.cachedBundle.exists()) { // if we have the bundle jar in cache we copy it to deployment directory
                    // get file name
                    URL url = new URL(bundleDef.source());
                    String fileName = calculateFileName(url);
                    deploymentFile = new File(deploymentDir, fileName);
                    // copy cached jars contents to the deployment bundle
                    IOUtils.copy(new FileInputStream(this.cachedBundle), new FileOutputStream(deploymentFile));
                } else { // else it should be re-verified
                    deploymentFile = downloadAndVerifyBundle(deploymentDir);
                }
                // here on we should have gotten the file to be deployed, or failed miserably
                Resource bundles = m_everest.process(new DefaultRequest(Action.READ, Path.from("/osgi/bundles"), null));
                List<Resource> resources = bundles.getResources(new ResourceFilter() {
                    @Override
                    public boolean accept(Resource resource) {
                        String symbolicName = resource.getMetadata().get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class);
                        Version version = resource.getMetadata().get(Constants.BUNDLE_VERSION_ATTRIBUTE, Version.class);
                        String bundleLocation = resource.getMetadata().get("bundle-location", String.class);
                        String state = resource.getMetadata().get("bundle-state", String.class);
                        return bundleDef.symbolicName().endsWith(symbolicName) &&
                                new Version(bundleDef.version()).equals(version) &&
                                (!"UNINSTALLED".equals(state)) &&
                                bundleDef.source().equals(bundleLocation);
                    }
                });
                if (!resources.isEmpty()) { // found at least one installed bundle that corresponds to the declaration
                    // take the first and look at its manifest
                    Resource bundle = resources.get(0);

                    ResourceMetadata headers = bundle.getResource("headers").getMetadata();
                    for (String key : bundleDef.properties().keySet()) {
                        if (headers.containsKey(key)) {
                            if (!headers.get(key).equals(bundleDef.properties().get(key))) {
                                throw new DeploymentException("For bundle property : " + key + " manifest of bundle found at "
                                        + bundleDef.source() + " doesn't match given value :" + headers.get(key) + ", expected :" + bundleDef.properties().get(key));
                            }
                        }
                    }
                    // update the bundle with inputStream
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("input", new FileInputStream(deploymentFile));
                    params.put("newState", bundleDef.state());
                    result = m_everest.process(new DefaultRequest(Action.UPDATE, bundle.getCanonicalPath(), params));
                    if (result == null) {
                        throw new DeploymentException("Error on resource updating on path : " + bundle.getCanonicalPath());
                    }
                    //TODO can check bundle properties
                } else { // Bundle does not exist should install it
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("location", bundleDef.source());
                    params.put("input", new FileInputStream(deploymentFile));
                    params.put("newState", bundleDef.state());
                    result = m_everest.process(new DefaultRequest(Action.CREATE, Path.from("/osgi/bundles"), params));
                    if (result == null) {
                        throw new DeploymentException("Error on resource creating from source : " + bundleDef.source());
                    }
                    //TODO can check bundle properties
                }
                //
                this.store(bundleDef.name(), result);
            } catch (ResourceNotFoundException e) {
                throw new DeploymentException("Error on finding Everest bundle resource");
            } catch (IllegalActionOnResourceException e) {
                throw new DeploymentException("Error on finding Everest bundle resource");
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
            if (resource != null) {
                try {
                    m_everest.process(new DefaultRequest(Action.DELETE, resource.getCanonicalPath(), null));
                } catch (IllegalActionOnResourceException e) {
                    //TODO log
                    System.out.println("failed to roll back");
                } catch (ResourceNotFoundException e) {
                    System.out.println("failed to roll back");
                }
            }
        }

        private File downloadAndVerifyBundle(File directory) throws DeploymentException {
            try {
                // create file for cache and save url contents
                URL url = new URL(bundleDef.source());
                String fileName = calculateFileName(url);
                File jarCache = new File(directory, fileName);
                IOUtils.copy(url.openStream(), new FileOutputStream(jarCache));
                // check if the file contains the bundle with correct manifest
                JarFile jarFile = new JarFile(jarCache);
                Manifest manifest = jarFile.getManifest();
                System.out.println(jarCache.getCanonicalPath());
                Attributes mainAttributes = manifest.getMainAttributes();
                // TODO Some manifest parsing
                for (String key : bundleDef.properties().keySet()) {
                    if (mainAttributes.getValue(key) != null) {
                        if (!mainAttributes.getValue(key).equals(bundleDef.properties().get(key))) {
                            FileUtils.deleteDirectory(directory);
                            throw new DeploymentException("For bundle property : " + key + " manifest of bundle gathered from url doesn't match given value :"
                                    + mainAttributes.getValue(key) + ", expected :" + bundleDef.properties().get(key));
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
