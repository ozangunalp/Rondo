package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import fr.liglab.adele.rondo.infra.model.Service;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/3/13
 * Time: 2:49 AM
 */
@Component
@Instantiate
@Provides(specifications = {ResourceProcessor.class})
public class ServiceProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    public EverestService m_everest;

    @ServiceProperty(name = "resource.type")
    public final String m_resourceType = "fr.liglab.adele.rondo.infra.model.Service";

    BundleContext m_context;

    public ServiceProcessor(BundleContext context) {
        super();
        this.m_context = context;
    }

    @Override
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
        ServiceDeploymentParticipant participant = new ServiceDeploymentParticipant(resource, transaction);
        addParticipant(participant);
        return participant;
    }

    private class ServiceDeploymentParticipant extends DefaultDeploymentParticipant {

        Service serviceDef;

        public ServiceDeploymentParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(transaction);
            try {
                this.serviceDef = (Service) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void commit() throws DeploymentException {
            System.out.println("Committing service " + serviceDef.name());
            Resource services = null;
            try {
                services = m_everest.process(new DefaultRequest(Action.READ, Path.from("/osgi/services"), null));

                List<Resource> resources = services.getResources(new ResourceFilter() {
                    @Override
                    public boolean accept(Resource resource) {
                        String[] objectClass = (String[]) resource.getMetadata().get(Constants.OBJECTCLASS);
                        List<String> objectClasses = serviceDef.objectClass();
                        boolean pidCheck = true;
                        if (serviceDef.pid() != null) {
                            pidCheck = serviceDef.pid().equals(resource.getMetadata().get(Constants.SERVICE_PID, String.class));
                        }
                        boolean propertyCheck = true;
                        for (Map.Entry<String, Object> property : serviceDef.properties().entrySet()) {
                            if (resource.getMetadata().containsKey(property.getKey())) {
                                Object o = resource.getMetadata().get(property.getKey());
                                if (o.equals(property.getValue())) {
                                    propertyCheck = false;
                                }
                            } else {
                                propertyCheck = false;
                            }
                        }
                        return objectClasses.containsAll(Arrays.asList(objectClass)) && pidCheck && propertyCheck;
                    }
                });
                if (resources.isEmpty()) {
                    throw new DeploymentException("Service resource not found " + serviceDef.name());
                } else {
                    // log
                    // we save the first corresponding service path
                    this.store(serviceDef.name(), resources.get(0).getCanonicalPath());
                    System.out.println("Service found: " + serviceDef.name());
                    // nothing else to do here ..
                }
            } catch (IllegalActionOnResourceException e) {
                throw new DeploymentException("Error on finding everest services resource");
            } catch (ResourceNotFoundException e) {
                throw new DeploymentException("Error on finding everest services resource");
            }
        }

        @Override
        public void cleanup() {
            System.out.println("cleaning up service");
        }

    }
}
