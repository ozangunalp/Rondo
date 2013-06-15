package fr.liglab.adele.rondo.infra.deployment.impl;

import fr.liglab.adele.rondo.infra.deployment.*;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentCoordinator;
import fr.liglab.adele.rondo.infra.deployment.transaction.impl.DeploymentCoordinatorImpl;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.util.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 12:23 AM
 */
@Component
@Instantiate
@Provides(specifications = ManagedInfrastructure.class)
public class InfrastructureDeployer implements ManagedInfrastructure {

    /**
     *
     */
    @ServiceProperty(name="deployment.timeout", mandatory = false)
    public int m_timeout = 1*10*1000;
    /**
     *
     */
    @Requires(id="deployment.customizer", optional = true, proxy = false, nullable = false)
    public DeploymentCustomizer m_customizer;

    /**
     *
     */
    @Requires(id="deployment.resolver", optional = false)
    public DeploymentResolver m_resolver;

    /**
     *
     */
    Logger m_logger;

    /**
     *
     */
    private ExecutorService m_executorService;

    /**
     *
     */
    private BundleContext m_context;

    /**
     *
     */
    private BundleTracker m_tracker;
    /**
     *
     */
    private InfrastructureProcessor m_processor;

    /**
     *
     */
    private Map<String, ResourceProcessor> processors = new HashMap<String, ResourceProcessor>();

    /**
     *
     */
    private Infrastructure m_currentInfrastructure;

    /**
     *
     */
    private DeploymentCoordinatorImpl m_coordinator;

    /**
     *
     */
    private DeploymentHandleImpl m_handle;

    /**
     *
     */
    private DeploymentPlan m_plan;

    /**
     *
     * @param context
     */
    public InfrastructureDeployer(BundleContext context) {
        this.m_context = context;
        this.m_logger = new Logger(m_context,"Infrastructure-Deployer");
    }

    @Validate
    public void start(){
        m_executorService = Executors.newSingleThreadExecutor();

        m_processor = new InfrastructureProcessor(m_context,m_logger);
        m_processor.start();

        m_tracker = new BundleTracker(m_context, Bundle.ACTIVE, new BundleTrackerCustomizer() {
            public Object addingBundle(final Bundle bundle, final BundleEvent event) {
                if (bundle.getBundleId() == m_context.getBundle().getBundleId()) {
                    // Not interested in our own bundle
                    return null;
                }
                System.out.println(bundle.getBundleId() + " " + bundle.getSymbolicName() + " " + bundle.getState());
                m_processor.activate(bundle);
                return bundle;
            }

            public void modifiedBundle(final Bundle bundle, final BundleEvent event, final Object object) {/* can do something maybe */}

            public void removedBundle(final Bundle bundle, final BundleEvent event, final Object object) {
                m_processor.deactivate(bundle);
            }
        });
        m_tracker.open();
    }

    @Invalidate
    public void stop(){
        m_executorService.shutdown();
        m_processor.stop();
        m_tracker.close();
    }

    @Bind(id = "infrastructure", specification = "fr.liglab.adele.rondo.infra.model.Infrastructure", aggregate = false, optional = true)
    public void bindInfrastructure(ServiceReference<Infrastructure> reference) {
        boolean immediate = reference.getProperty("deployment.immediate")==null ? false : (Boolean) reference.getProperty("deployment.immediate");
        Infrastructure newInfrastructure = m_context.getService(reference);
        try {
            m_plan = m_resolver.resolve(newInfrastructure,m_currentInfrastructure);
            m_currentInfrastructure = newInfrastructure;
            if(m_handle!=null){
                m_handle.cancel();
                m_handle = null;
            }
            if(immediate){
                this.apply(this.getDeploymentHandle());
            }
        } catch (DependencyResolutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            // TODO log
        }
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

    public Infrastructure getInfrastructureModel() {
        return m_currentInfrastructure;
    }

    public DeploymentHandle getDeploymentHandle() {
        if(m_handle!=null){
            return m_handle;
        }
        if(m_plan!=null){
            m_handle = new DeploymentHandleImpl(m_plan, this, m_customizer, m_timeout);
            return m_handle;
        }
        return null;
    }

    public void apply(DeploymentHandle handle, DeploymentListener... listeners){
        final DeploymentHandle deploymentHandle = handle;
        if(deploymentHandle!=null){
            for (DeploymentListener listener : listeners) {
                deploymentHandle.registerListener(listener);
            }
            // add it to deployerThread for deployment
            m_executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        deploymentHandle.apply();
                    } catch (Throwable t) {
                        //log caught error
                    }
                }
            });
        }
    }

    public ResourceProcessor getResourceProcessor(String type) {
        return processors.get(type);
    }

    public DeploymentCoordinator getCoordinator() {
        if (m_coordinator == null) {
            m_coordinator = new DeploymentCoordinatorImpl();
        }
        return m_coordinator;
    }

}
