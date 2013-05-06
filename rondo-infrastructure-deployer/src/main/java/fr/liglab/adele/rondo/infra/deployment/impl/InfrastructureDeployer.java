package fr.liglab.adele.rondo.infra.deployment.impl;

import fr.liglab.adele.rondo.infra.deployment.DependencyResolutionException;
import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.deployment.DeploymentPlan;
import fr.liglab.adele.rondo.infra.deployment.ManagedInfrastructure;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentCoordinator;
import fr.liglab.adele.rondo.infra.deployment.transaction.impl.DeploymentCoordinatorImpl;
import fr.liglab.adele.rondo.infra.model.Dependency;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import fr.liglab.adele.rondo.infra.model.ResourceReference;
import org.apache.felix.ipojo.annotations.*;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 12:23 AM
 */
@Component
@Instantiate
@Provides(specifications = {ManagedInfrastructure.class})
public class InfrastructureDeployer implements ManagedInfrastructure {

    private BundleContext m_context;

    private Map<String, ResourceProcessor> processors = new HashMap<String, ResourceProcessor>();

    private Infrastructure m_currentInfrastructure;

    private DeploymentHandle m_handle;

    private ServiceRegistration m_handleRegisteration;

    private DeploymentCoordinatorImpl m_coordinator;

    public InfrastructureDeployer(BundleContext context) {
        this.m_context = context;
        this.m_handle = null;
        this.m_currentInfrastructure = null;
    }

    @Validate
    public void start() {

    }

    @Invalidate
    public void stop() {

    }

    @Bind(id = "infrastructure", specification = "fr.liglab.adele.rondo.infra.model.Infrastructure", aggregate = false, optional = true)
    public void bindInfrastructure(ServiceReference<Infrastructure> reference) {
        m_currentInfrastructure = m_context.getService(reference);
        DeploymentPlan plan = null;
        try {
            plan = calculateDeploymentPlan(m_currentInfrastructure);
            m_handle = new DeploymentHandleImpl(plan, this);
            m_handleRegisteration = m_context.registerService(DeploymentHandle.class, m_handle, null);
        } catch (DependencyResolutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        // TODO check deployment plan against constraints ???
        // and start to handle
    }

    @Unbind(id = "infrastructure")
    public void unbindInfrastructure(ServiceReference<Infrastructure> reference) {
        // won't uninstall all the infrastructure
//        if(m_handleRegisteration!=null){
//            m_handleRegisteration.unregister();
//        }
    }

    @Bind(id = "processors", specification = "fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor", aggregate = true, optional = true)
    public void bindResourceProcessor(ServiceReference<ResourceProcessor> reference) {
        ResourceProcessor processor = m_context.getService(reference);
        String resourceType = (String) reference.getProperty("resource.type");
        processors.put(resourceType, processor);
    }

    @Unbind(id = "processors")
    public void unbindResourceProcessor(ServiceReference<ResourceProcessor> reference) {
        ResourceProcessor processor = m_context.getService(reference);
        processors.remove(processor);
    }

    @Override
    public Infrastructure getInfrastructureModel() {
        return this.m_currentInfrastructure;
    }

    @Override
    public DeploymentHandle getDeploymentHandle() {
        return this.m_handle;
    }

    @Override
    public ResourceProcessor getResourceProcessor(String type) {
        return processors.get(type);
    }

    @Override
    public DeploymentCoordinator getCoordinator() {
        if (this.m_coordinator == null) {
            this.m_coordinator = new DeploymentCoordinatorImpl(30 * 10 * 1000L, null);
        }
        return this.m_coordinator;
    }

    public DeploymentPlan calculateDeploymentPlan(Infrastructure newInfrastructure) throws DependencyResolutionException {
        DeploymentPlan deploymentPlan = new DeploymentPlan();
        List<Dependency> dependencies = newInfrastructure.getDependencies();
        Set<ResourceReference> providers = new HashSet<ResourceReference>();
        Set<ResourceReference> requirers = new HashSet<ResourceReference>();
        for (Dependency dep : dependencies) {
            providers.add(dep.provider());
            requirers.add((dep.requirer()));
        }
        // Level 0
        // find single resources
        LinkedList<ResourceReference> resourceReferences = new LinkedList<ResourceReference>(newInfrastructure.getResourceReferences());
        resourceReferences.removeAll(providers);
        resourceReferences.removeAll(requirers);
        // add single providers to the deployment plan
        deploymentPlan.addAll(resourceReferences);
        // Level 1
        // find providers without dependency
        providers.removeAll(requirers);
        List<ResourceReference> satisfiedProviders = new ArrayList<ResourceReference>();
        satisfiedProviders.addAll(providers);
        // add providers without dependency to the deployment plan
        deploymentPlan.addAll(new LinkedList<ResourceReference>(providers));
        // Level 2 +
        while (!requirers.isEmpty()) {
            LinkedList<ResourceReference> nextProviders = new LinkedList<ResourceReference>();
            Set<ResourceReference> requirersToRemove = new HashSet<ResourceReference>();
            // iterate over requirer
            for (ResourceReference requirer : requirers) {
                Set<ResourceReference> requirerProviders = new HashSet<ResourceReference>();
                // collect requirers providers
                for (Dependency dep : dependencies) {
                    if (dep.requirer().equals(requirer)) {
                        requirerProviders.add(dep.provider());
                    }
                }
                // if all its providers are satisfied then add requirer into next deployment plan stage and enlist for removal
                if (satisfiedProviders.containsAll(requirerProviders)) {
                    nextProviders.add(requirer);
                    requirersToRemove.add(requirer);
                }

            }
            if (requirersToRemove.isEmpty()) {
                throw new DependencyResolutionException("Dependency cannot be resolved");
            }
            requirers.removeAll(requirersToRemove);
            deploymentPlan.addAll(nextProviders);
            satisfiedProviders.addAll(nextProviders);
        }
        return deploymentPlan;
    }

}
