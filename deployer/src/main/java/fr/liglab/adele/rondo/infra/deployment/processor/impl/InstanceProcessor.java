package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceState;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.Instance;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.osgi.framework.BundleContext;

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
public class InstanceProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    public EverestService m_everest;

    @ServiceProperty(name = "resource.type", value = "fr.liglab.adele.rondo.infra.model.Instance")
    public final String m_resourceType = "fr.liglab.adele.rondo.infra.model.Instance";

    BundleContext m_context;

    public InstanceProcessor(BundleContext context) {
        super();
        this.m_context = context;
    }

    @Override
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
        return new InstanceParticipant(resource, transaction);
    }

    private class InstanceParticipant extends DefaultDeploymentParticipant {

        Instance m_instanceDef;

        ResourceState m_initialInstance;

        Resource m_instance;

        public InstanceParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(resource,transaction);
            try {
                this.m_instanceDef = (Instance) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void prepare() throws DeploymentException {
            if(m_instanceDef.factory()==null){
                throw new DeploymentException("Factory name is not set for instance declaration");
            }

            if(m_instanceDef.name()!=null){
                Resource instance = findInstance(m_instanceDef);
                if(instance!=null){
                    m_initialInstance = new ResourceState(null,instance);
                }
            }
        }

        @Override
        public void commit() throws DeploymentException {
            Resource instance = null;
            if(m_initialInstance==null){ // there where no instance with the given name
                // find the factory
                Resource factory = findFactory(m_instanceDef);
                if(factory!=null){
                    try {
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.putAll(m_instanceDef.extraProperties());
                        if(m_instanceDef.name()!=null){
                            params.put("instance.name",m_instanceDef.name());
                        }
                        instance = m_everest.process(new DefaultRequest(Action.CREATE,factory.getPath(),params));
                    } catch (IllegalActionOnResourceException e) {
                        // can happen
                        e.printStackTrace();
                        throw new DeploymentException("Cannot create instance with given parameters "+ e.getMessage());
                    } catch (ResourceNotFoundException e) {
                        // could not find the factory
                        throw new DeploymentException("Cannot find a component factory with given name/version" +m_instanceDef.factory()+" "+m_instanceDef.factoryVersion());
                    }
                } else {
                    throw new DeploymentException("Cannot find a component factory with given name/version" +m_instanceDef.factory()+" "+m_instanceDef.factoryVersion());
                }

            } else { // there was an instance with given name, we reconfigure it
                try {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("configuration",m_instanceDef.extraProperties());
                    instance = m_everest.process(new DefaultRequest(Action.UPDATE,m_initialInstance.getPath(),params));
                } catch (IllegalActionOnResourceException e) {
                    // this can happen
                } catch (ResourceNotFoundException e) {
                    // if this happens, we could not find the instance
                }
            }

            if(instance!=null){ // here we should have gotten the instance

                // at the end we try to attain the given state
                if(m_instanceDef.state()!=null){
                    try {
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put("state",m_instanceDef.state());
                        //ignore other states
                        instance = m_everest.process(new DefaultRequest(Action.UPDATE,instance.getPath(),params));
                    } catch (IllegalActionOnResourceException e) {
                        // hope this does not happen
                        e.printStackTrace();
                    } catch (ResourceNotFoundException e) {
                        // well this neither..
                        e.printStackTrace();
                    }
                }
                m_instance = instance;

                // last check before commit
                if(!checkInstance(m_instance,m_instanceDef,true)){
                    throw new DeploymentException("Given state could not been attained for "+m_instanceDef.id());
                }

            } else { // we could not get to have an instance, fail miserably!
                throw new DeploymentException("Could not create or update an instance with given parameters "+m_instanceDef.id());
            }

        }

        @Override
        public void rollback() {
            if(m_initialInstance==null){ // there were no instances so delete it
                try {
                    m_everest.process(new DefaultRequest(Action.DELETE, m_instance.getPath(), null));
                } catch (IllegalActionOnResourceException e) {
                    System.out.println("failed to rollback" +m_instanceDef.id());
                    e.printStackTrace();
                } catch (ResourceNotFoundException e) {
                    System.out.println("failed to rollback" +m_instanceDef.id());
                    e.printStackTrace();
                }
            } else {
                try {
                    // TODO reconfigure and state rollback
                    Map<String, Object> updateParams = new HashMap<String, Object>();
                    ResourceMetadata resourceState = m_initialInstance.getResourceState();
                    updateParams.putAll(resourceState);
                    m_everest.process(new DefaultRequest(Action.UPDATE, m_initialInstance.getPath(), updateParams));

                } catch (IllegalActionOnResourceException e) {
                    System.out.println("failed to rollback" +m_instanceDef.id());
                    e.printStackTrace();
                } catch (ResourceNotFoundException e) {
                    System.out.println("failed to rollback" +m_instanceDef.id());
                    e.printStackTrace();
                }
            }
        }


        private Resource findFactory(Instance instanceDef) {
            if(instanceDef.factory()!=null){
                try {
                    Resource factory=null;
                    Resource factories = m_everest.process(new DefaultRequest(Action.READ, Path.from("/ipojo/factory").addElements(instanceDef.factory()), null));
                    Iterator<Resource> iterator = factories.getResources().iterator();
                    while(iterator.hasNext() && factory==null){
                        Resource nextFac = iterator.next();
                        if(instanceDef.factoryVersion()==null){ // factory version is not specified
                            if("null".equals(nextFac.getMetadata().get("version",String.class))){ // try "null" as factory version
                                factory = nextFac;
                            } else { // else take the first valid factory
                                if("valid".equalsIgnoreCase(nextFac.getMetadata().get("state", String.class))){
                                    factory = nextFac;
                                }
                            }
                        } else { // factory version is specified should find the factory with specific version
                            if(instanceDef.factoryVersion().equals(nextFac.getMetadata().get("version", String.class))){
                                factory = nextFac;
                            }
                        }
                    }
                    return factory;
                } catch (IllegalActionOnResourceException e) {
                    return null;
                } catch (ResourceNotFoundException e) {
                    return null;
                }
            }
            return null;
        }

        private Resource findInstance(Instance instanceDef) throws DeploymentException {
            if(instanceDef.factory()!=null){
                if(instanceDef.name()!=null){
                    try {
                        Resource instance = m_everest.process(new DefaultRequest(Action.READ, Path.from("/ipojo/instance").addElements(instanceDef.name()), null));
                        if(checkInstance(instance,instanceDef,false)){
                            return instance;
                        }
                    } catch (IllegalActionOnResourceException e) {
                        throw new DeploymentException("Error on reading instance with the given instance name"+ instanceDef.name() +" : "+e.getMessage());
                    } catch (ResourceNotFoundException e) {
                        return null;
                    }

                }
                return null;
            }
            return null;
        }


        private boolean checkInstance(Resource instance, Instance instanceDef, boolean extraProperties) {
            if (instance != null && instanceDef!=null) {
                boolean propertyCheck = true;
                Resource factory = null;
                for (Relation relation : instance.getRelations()) {
                    if(relation.getName().equals("factory")){
                        try {
                            factory = m_everest.process(new DefaultRequest(Action.READ,relation.getHref(),null));
                        } catch (IllegalActionOnResourceException e) {
                            // should never happen
                        } catch (ResourceNotFoundException e) {
                            // then factory is null
                        }
                    }
                }
                if(factory==null){
                    //System.out.println("factory null");
                    return false;
                }
                if(!factory.getMetadata().get("name",String.class).equals(instanceDef.factory())){
                    //System.out.println("cant verify factory name");
                    return false;
                }
                if(instanceDef.factoryVersion()!=null){
                    if(!factory.getMetadata().get("version",String.class).equals(instanceDef.factoryVersion())){
                        //System.out.println("cant verify factory version");
                        return false;
                    }
                }
                // if we need to check extra properties
                if(extraProperties){
                    // look if instance properties match given properties
                    Iterator<String> iterator = instanceDef.extraProperties().keySet().iterator();
                    ResourceMetadata configuration = instance.getMetadata().get("configuration", ResourceMetadata.class);
                    //System.out.println(configuration);
                    while (propertyCheck && iterator.hasNext()) {
                        String key = iterator.next();
                        Object o = instanceDef.properties().get(key);
                        if (!o.equals(configuration.get(key))) {
                            //System.out.println("Found "+configuration.get(key)+" expected "+o);
                            propertyCheck = false;
                        }
                    }
                }
                //System.out.println("propertyCheck"+propertyCheck);
                return propertyCheck;
            }
            return false;
        }
    }
}
