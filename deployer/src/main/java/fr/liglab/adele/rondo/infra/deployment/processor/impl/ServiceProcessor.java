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
import org.osgi.framework.*;

import java.util.*;

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

    @ServiceProperty(name = "resource.type", value = "fr.liglab.adele.rondo.infra.model.Service")
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

    @Override
    public boolean check(ResourceDeclaration resource) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private class ServiceDeploymentParticipant extends DefaultDeploymentParticipant {

        Service m_serviceDef;

        public ServiceDeploymentParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(resource, transaction);
            try {
                this.m_serviceDef = (Service) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void commit() throws DeploymentException {
            Resource services = null;
            try {
                services = m_everest.process(new DefaultRequest(Action.READ, Path.from("/osgi/services"), null));
                ServiceResourceFilter serviceResourceFilter = new ServiceResourceFilter(services.getCanonicalPath(),m_context, m_serviceDef);
                List<Resource> resources = services.getResources(serviceResourceFilter);
                if (resources.isEmpty()) {
                    throw new DeploymentException("Service resource not found " + m_serviceDef.id());
                } else {

                    System.out.println("Service found: " + m_serviceDef.id());
                }
            } catch (IllegalActionOnResourceException e) {
                throw new DeploymentException("Error on finding everest services resource "+m_serviceDef.id());
            } catch (ResourceNotFoundException e) {
                throw new DeploymentException("Error on finding everest services resource "+m_serviceDef.id());
            }
        }

    }

    public class ServiceResourceFilter implements ResourceFilter{

        private final Path parentPath;
        BundleContext bundleContext;
        Service serviceDeclaration;
        Filter filter = null;

        public ServiceResourceFilter(Path parentPath,BundleContext context,Service serviceDeclaration) throws DeploymentException {
            this.parentPath = parentPath;
            this.bundleContext = context;
            this.serviceDeclaration = serviceDeclaration;
            if(serviceDeclaration.filter()!=null){
                try {
                    filter = bundleContext.createFilter(serviceDeclaration.filter());
                } catch (InvalidSyntaxException e) {
                    throw new DeploymentException(e.getMessage());
                }
            }
        }

        @Override
        public boolean accept(Resource resource) {
            if(!parentPath.equals(resource.getCanonicalPath().getParent())){
                return false;
            }
            String[] objectClass = (String[]) resource.getMetadata().get(Constants.OBJECTCLASS,Object.class);
            List<String> objectClasses = serviceDeclaration.objectClass();
            boolean pidCheck = true;
            if (serviceDeclaration.pid() != null) {
                pidCheck = serviceDeclaration.pid().equals(resource.getMetadata().get(Constants.SERVICE_PID, String.class));
            }
            boolean propertyCheck = true;
            for (Map.Entry<String, Object> property : serviceDeclaration.extraProperties().entrySet()) {
                if (resource.getMetadata().containsKey(property.getKey())) {
                    Object o = resource.getMetadata().get(property.getKey());
                    if (o.equals(property.getValue())) {
                        propertyCheck = false;
                    }
                } else {
                    propertyCheck = false;
                }
            }
            boolean filterCheck = true;
            if(filter!=null){
                filterCheck = filter.matches(resource.getMetadata());
            }
            return Arrays.asList(objectClass).containsAll(objectClasses) && pidCheck && propertyCheck && filterCheck;
        }
    }
}
