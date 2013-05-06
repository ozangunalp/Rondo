package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.Configuration;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.services.EverestService;
import org.osgi.framework.BundleContext;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/5/13
 * Time: 10:00 PM
 */
@Component
@Instantiate
@Provides(specifications = {ResourceProcessor.class})
public class ConfigProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    public EverestService m_everest;

    @ServiceProperty(name = "resource.type")
    public final String m_resourceType = "fr.liglab.adele.rondo.infra.model.Configuration";

    private final BundleContext m_context;

    public ConfigProcessor(BundleContext context) {
        super();
        this.m_context = context;
    }

    @Override
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
        return new ConfigurationParticipant(resource, transaction);
    }

    private class ConfigurationParticipant extends DefaultDeploymentParticipant {

        Configuration m_configDef;

        private ConfigurationParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(transaction);
            try {
                this.m_configDef = (Configuration) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void prepare() throws DeploymentException {
            throw new DeploymentException("could not find config " + m_configDef.name());
        }
    }
}
