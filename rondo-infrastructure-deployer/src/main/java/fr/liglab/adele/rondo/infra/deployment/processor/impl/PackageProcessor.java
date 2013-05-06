package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.Package;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Version;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/3/13
 * Time: 2:49 AM
 */
@Component
@Instantiate
@Provides(specifications = {ResourceProcessor.class})
public class PackageProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    public EverestService m_everest;

    @ServiceProperty(name = "resource.type")
    public final String m_resourceType = "fr.liglab.adele.rondo.infra.model.Package";

    BundleContext m_context;

    public PackageProcessor(BundleContext context) {
        super();
        this.m_context = context;
    }

    @Override
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
        PackageDeploymentParticipant participant = new PackageDeploymentParticipant(resource, transaction);
        addParticipant(participant);
        return participant;
    }

    private class PackageDeploymentParticipant extends DefaultDeploymentParticipant {

        Package packageDef;

        public PackageDeploymentParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(transaction);
            try {
                this.packageDef = (Package) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void commit() throws DeploymentException {
            System.out.println("Committing package" + packageDef.name());
            Resource packages = null;
            try {
                packages = m_everest.process(new DefaultRequest(Action.READ, Path.from("/osgi/packages"), null));
                List<Resource> resources = packages.getResources(new ResourceFilter() {
                    @Override
                    public boolean accept(Resource resource) {
                        String packageName = (String) resource.getMetadata().get("osgi.wiring.package");
                        Version version = (Version) resource.getMetadata().get("version");
                        return packageName.equals(packageDef.name()) && version.equals(new Version(packageDef.version()));
                    }
                });
                if (resources.isEmpty()) {
                    throw new DeploymentException("Package resource not found " + packageDef.name());
                } else {
                    //TODO log
                    // we save the first corresponding package path
                    this.store(packageDef.name(), resources.get(0).getCanonicalPath());
                    System.out.println("Package found: " + packageDef.name());
                    // nothing else to do here ..
                }
            } catch (IllegalActionOnResourceException e) {
                throw new DeploymentException("Error on finding Everest packages resource");
            } catch (ResourceNotFoundException e) {
                throw new DeploymentException("Error on finding Everest packages resource");
            }
        }

        @Override
        public void cleanup() {
            System.out.println("Nothing to clean up here " + packageDef.name());
        }

    }

}
