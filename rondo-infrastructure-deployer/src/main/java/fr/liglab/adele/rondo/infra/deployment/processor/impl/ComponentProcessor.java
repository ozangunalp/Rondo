package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.osgi.framework.BundleContext;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/6/13
 * Time: 9:45 AM
 */
@Component
@Instantiate
@Provides(specifications = {ResourceProcessor.class})
public class ComponentProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    private EverestService m_everest;

    @ServiceProperty(name = "resource.type", value = "fr.liglab.adele.rondo.infra.model.Component")
    public final String m_resourceType = "fr.liglab.adele.rondo.infra.model.Component";

    BundleContext m_context;

    public ComponentProcessor(BundleContext context) {
        super();
        this.m_context = context;
    }

    @Override
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
        return new ComponentParticipant(resource, transaction);
    }

    private class ComponentParticipant extends DefaultDeploymentParticipant {

        fr.liglab.adele.rondo.infra.model.Component m_componentDef;

        public ComponentParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(transaction);
            try {
                m_componentDef = (fr.liglab.adele.rondo.infra.model.Component) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void commit() throws DeploymentException {
            System.out.println("Committing component " + m_componentDef.name());

            Resource component = null;
            try {
                component = m_everest.process(new DefaultRequest(Action.READ, Path.from("/ipojo/factory/").addElements(m_componentDef.name(), m_componentDef.version()), null));
                if (component == null) {
                    throw new DeploymentException("Component resource not found " + m_componentDef.name());
                } else {
                    //TODO log
                    this.store(m_componentDef.name(), component.getCanonicalPath());
                    System.out.println("Component found: " + m_componentDef.name());
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
            System.out.println("Nothing to clean up here " + m_componentDef.name());
        }
    }
}
