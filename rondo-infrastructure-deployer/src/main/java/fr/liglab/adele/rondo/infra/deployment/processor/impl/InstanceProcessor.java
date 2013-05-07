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
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.osgi.framework.BundleContext;

import java.util.HashMap;
import java.util.Iterator;
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
            // find something to do here

            Resource instance = null;
            try {
                instance = m_everest.process(new DefaultRequest(Action.READ, Path.from("/ipojo/instance").addElements(instanceDef.name()), null));
                if (instance != null) {
                    // look if instance properties match given properties
                    Iterator<String> iterator = instanceDef.properties().keySet().iterator();
                    boolean propertyCheck = true;
                    while (propertyCheck && iterator.hasNext()) {
                        String key = iterator.next();
                        Object o = instanceDef.properties().get(key);
                        if (!o.equals(instance.getMetadata().get(key))) {
                            propertyCheck = false;
                        }
                    }
                    if (propertyCheck) {
                        this.store(instanceDef.name(), instance.getCanonicalPath());
                        System.out.println("Instance found: " + instanceDef.name());
                    }
                }
            } catch (IllegalActionOnResourceException e) {
                throw new DeploymentException("Error on finding Everest packages resource");
            } catch (ResourceNotFoundException e) {
                throw new DeploymentException("Error on finding Everest packages resource");
            }
        }

        @Override
        public void commit() throws DeploymentException {
            System.out.println("committing resource " + instanceDef.name());

            Resource instance = null;
            if (this.get(instanceDef.name()) == null) {
                try {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.putAll(instanceDef.properties());
                    instance = m_everest.process(new DefaultRequest(Action.CREATE, Path.from("/ipojo/instance").addElements(instanceDef.name()), null));
                    if (instance == null) {
                        throw new DeploymentException("Instance resource cannot be created " + instanceDef.name());
                    } else {
                        //TODO log
                        this.store(instanceDef.name(), instance.getCanonicalPath());
                        System.out.println("Instance found: " + instanceDef.name());
                        // nothing else to do here ..
                    }
                } catch (IllegalActionOnResourceException e) {
                    throw new DeploymentException("Error on finding Everest packages resource");
                } catch (ResourceNotFoundException e) {
                    throw new DeploymentException("Error on finding Everest packages resource");
                }
            } else {
                // Nothing to do..
            }
        }

        @Override
        public void rollback() {
            Path instancePath = (Path) this.get(instanceDef.name());
            try {
                m_everest.process(new DefaultRequest(Action.DELETE, instancePath, null));
            } catch (IllegalActionOnResourceException e) {
                // TODO log
                e.printStackTrace();
            } catch (ResourceNotFoundException e) {
                // TODO log
                e.printStackTrace();
            }
        }
    }
}
