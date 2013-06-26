package fr.liglab.adele.rondo.infra.deployment.processor.impl;

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

import java.util.Iterator;
import java.util.List;

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

    @Override
    public boolean check(ResourceDeclaration resource) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private class ComponentParticipant extends DefaultDeploymentParticipant {

        fr.liglab.adele.rondo.infra.model.Component m_componentDef;

        ResourceState m_initialResource;

        public ComponentParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(resource,transaction);
            try {
                m_componentDef = (fr.liglab.adele.rondo.infra.model.Component) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void commit() throws DeploymentException {
            Resource component = null;
            if(m_componentDef.version()!=null){ // version specified
                try {
                    component = m_everest.process(new DefaultRequest(Action.READ, Path.from("/ipojo/factory").addElements(m_componentDef.name(), m_componentDef.version()), null));
                } catch (IllegalActionOnResourceException e) {
                    // should never happen
                } catch (ResourceNotFoundException e) {
                    // this can happen, then cannot find the component
                }

                if (component == null) {
                    throw new DeploymentException("Component resource not found " + m_componentDef.name());
                } else {
                    m_initialResource = new ResourceState(null,component);
                    if(m_componentDef.state()!=null){
                        String state = component.getMetadata().get("state",String.class);
                        if(!m_componentDef.state().equalsIgnoreCase(state)){
                            throw new DeploymentException("Component state does not match, found: "+state+" expected: "+m_componentDef.state());
                        }
                    }
                }
            } else { // version non-specified
                try {
                    component = m_everest.process(new DefaultRequest(Action.READ, Path.from("/ipojo/factory").addElements(m_componentDef.name(), "null"), null));
                } catch (IllegalActionOnResourceException e) {
                    // should never happen
                } catch (ResourceNotFoundException e) {
                    // this can happen, then cannot find the component
                }
                if(component==null){
                    try {
                        Resource components = m_everest.process(new DefaultRequest(Action.READ, Path.from("/ipojo/factory").addElements(m_componentDef.name()), null));
                        Iterator<Resource> resourceIterator = components.getResources().iterator();
                        while(resourceIterator.hasNext() && component==null){
                            Resource resource = resourceIterator.next();
                            if(m_componentDef.state()!=null){
                                String state = resource.getMetadata().get("state",String.class);
                                if(m_componentDef.state().equalsIgnoreCase(state)){
                                    component = resource;
                                }
                            } else {
                                component = resource;
                            }
                        }
                        if(component==null){
                            throw new DeploymentException("Any component found with id: "+m_componentDef.name()+"matching state "+m_componentDef.state());
                        }
                    } catch (IllegalActionOnResourceException e) {
                        //should never happen
                    } catch (ResourceNotFoundException e) {
                        // then we should fail
                        throw new DeploymentException("Any component found with id: "+m_componentDef.name());
                    }

                } else {
                    m_initialResource = new ResourceState(null,component);
                    if(m_componentDef.state()!=null){
                        String state = component.getMetadata().get("state",String.class);
                        if(!m_componentDef.state().equalsIgnoreCase(state)){
                            throw new DeploymentException("Component state does not match, found: "+state+" expected: "+m_componentDef.state());
                        }
                    }
                }
            }
        }
    }
}
