package fr.liglab.adele.rondo.infra.system;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceState;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.osgi.framework.BundleContext;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/13/13
 * Time: 4:31 PM
 */

@Component
@Instantiate
@Provides(specifications = ResourceProcessor.class)
public class SystemProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    public EverestService m_everest;

    @ServiceProperty(name = "resource.type", value = "fr.liglab.adele.rondo.infra.system.System")
    public final String m_resourceType = "fr.liglab.adele.rondo.infra.system.System";

    BundleContext m_context;

    public SystemProcessor(BundleContext context) {
        super();
        this.m_context = context;
    }

    @Override
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
        return new SystemDeploymentParticipant(resource,transaction);
    }

    public class SystemDeploymentParticipant extends DefaultDeploymentParticipant {

        System m_systemDef;

        ResourceState m_initialSystemProps;

        public SystemDeploymentParticipant(ResourceDeclaration resource, DeploymentTransaction m_coordination) throws DeploymentException {
            super(resource, m_coordination);
            try {
                m_systemDef = (System) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void prepare() throws DeploymentException {
            try {
                Resource process = m_everest.process(new DefaultRequest(Action.READ, Path.from("/system/properties"), null));
                m_initialSystemProps = new ResourceState(null,process);
            } catch (IllegalActionOnResourceException e) {
                // should never happen
                throw new DeploymentException("");
            } catch (ResourceNotFoundException e) {
                throw new DeploymentException("");
            }
        }

        @Override
        public void commit() throws DeploymentException {
            try{
                HashMap<String,Object> params = new HashMap<String,Object>();
                Dictionary props = new Hashtable();
                for (String key : m_systemDef.extraProperties().keySet()) {
                    props.put(key,m_systemDef.extraProperties().get(key));
                }
                params.put("properties",props);
                Resource process = m_everest.process(new DefaultRequest(Action.UPDATE, Path.from("/system/properties"), params));
            } catch (IllegalActionOnResourceException e) {
                // should never happen
            } catch (ResourceNotFoundException e) {
                // this neither

            }
        }

        @Override
        public void rollback() {
            try {
                HashMap<String,Object> params = new HashMap<String,Object>();
                Dictionary props = new Hashtable();
                for (String key : m_initialSystemProps.getResourceState().keySet()) {
                    props.put(key,m_initialSystemProps.getResourceState().get(key));
                }
                params.put("properties",props);
                Resource process = m_everest.process(new DefaultRequest(Action.UPDATE, Path.from("/system/properties"), params));
            } catch (IllegalActionOnResourceException e) {
                // should never happen
            } catch (ResourceNotFoundException e) {
                // this neither

            }
        }

    }

}
