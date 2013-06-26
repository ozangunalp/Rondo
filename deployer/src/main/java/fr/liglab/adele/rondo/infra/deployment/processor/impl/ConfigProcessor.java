package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceState;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.Configuration;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.osgi.framework.BundleContext;

import java.util.*;

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

    @ServiceProperty(name = "resource.type", value = "fr.liglab.adele.rondo.infra.model.Configuration")
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

    @Override
    public boolean check(ResourceDeclaration resource) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private class ConfigurationParticipant extends DefaultDeploymentParticipant {

        Configuration m_configDef;

        ResourceState m_initialConfig;

        private ConfigurationParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(resource, transaction);
            try {
                this.m_configDef = (Configuration) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void prepare() throws DeploymentException {
            String pid = m_configDef.pid();
            if(pid!=null){
                try {
                    Resource configResource = m_everest.process(new DefaultRequest(Action.READ, Path.from("/osgi/configurations").addElements(pid), null));
                    if(configResource!=null){
                        m_initialConfig = new ResourceState(null,configResource);
                    }
                } catch (IllegalActionOnResourceException e) {
                    // should never happen
                } catch (ResourceNotFoundException e) {
                    // this can happen, then there is no initial state to save
                }
            }
        }

        @Override
        public void commit() throws DeploymentException {
            Resource resource=null;
            if(m_initialConfig==null){ // create configuration
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("pid",m_configDef.pid());
                params.put("location",m_configDef.location());
                params.put("factoryPid",m_configDef.factoryPid());
                try {
                    resource = m_everest.process(new DefaultRequest(Action.CREATE, Path.from("/osgi/configurations"), params));
                } catch (IllegalActionOnResourceException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ResourceNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            } else { // get existing configuration
                try {
                    resource = m_everest.process(new DefaultRequest(Action.READ, m_initialConfig.getPath(),null));
                } catch (IllegalActionOnResourceException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ResourceNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            // update the configuration
            //m_configDef.
            Map<String, Object> params = new HashMap<String, Object>();
            Dictionary properties = new Hashtable();
            Set<String> propertyKeys = m_configDef.extraProperties().keySet();
            for(String key :propertyKeys){
                properties.put(key,m_configDef.extraProperties().get(key));
            }
            params.put("properties",properties);
            params.put("location",m_configDef.location());
            try {
                resource = m_everest.process(new DefaultRequest(Action.UPDATE, resource.getCanonicalPath(), params));
            } catch (IllegalActionOnResourceException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            // check resource lastly

        }

        @Override
        public void rollback() {
            if(m_initialConfig!=null){

            } else {
                try {
                    m_everest.process(new DefaultRequest(Action.DELETE,m_initialConfig.getPath(),null));
                } catch (IllegalActionOnResourceException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (ResourceNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }
}
