package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.Instance;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.services.EverestService;
import org.osgi.framework.BundleContext;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/3/13
 * Time: 2:49 AM
 */
@Component
@Instantiate
@Provides(specifications = {ResourceProcessor.class})
public class InstanceProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    public EverestService m_everest;

    @ServiceProperty(name = "resource.type")
    public final String m_resourceType = "fr.liglab.adele.rondo.infra.model.Instance";

    BundleContext m_context;

    public InstanceProcessor(BundleContext context) {
        super();
        this.m_context = context;
    }

    @Invalidate
    public void stop() {
        getDeploymentParticipants().clear();
    }

    @Override
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
        InstanceDeploymentParticipant participant = new InstanceDeploymentParticipant(resource, transaction);
        addParticipant(participant);
        return participant;
    }

    private class InstanceDeploymentParticipant extends DefaultDeploymentParticipant {

        Instance instanceDef;

        public InstanceDeploymentParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(transaction);
            try {
                this.instanceDef = (Instance) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void prepare() throws DeploymentException {
            System.out.println("preparing resource " + instanceDef.name());
        }

        @Override
        public void commit() throws DeploymentException {
            System.out.println("committing resource " + instanceDef.name());

        }

        @Override
        public void rollback() {

        }
    }
}
