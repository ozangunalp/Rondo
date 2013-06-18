package fr.liglab.adele.rondo.infra.deployment.impl;

import fr.liglab.adele.rondo.infra.deployment.*;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentCoordinator;
import fr.liglab.adele.rondo.infra.deployment.transaction.impl.DeploymentCoordinatorImpl;
import fr.liglab.adele.rondo.infra.deployment.util.DefaultLogService;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import org.apache.felix.ipojo.annotations.*;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.BundleTracker;
import org.osgi.util.tracker.BundleTrackerCustomizer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
@Component
@Instantiate
@Provides(specifications = ManagedInfrastructure.class)
public class InfrastructureDeployer implements ManagedInfrastructure {

    public final static String LOG_PREFIX = "Rondo-Deployer";

    /**
     * Timeout property on service
     */
    @ServiceProperty(name="deployment.timeout", mandatory = false)
    public int m_timeout = 1*10*1000;

    /**
     * Customizer dependency
     */
    @Requires(id="deployment.customizer", optional = true, proxy = false, nullable = false)
    public DeploymentCustomizer m_customizer;

    /**
     * Deployment resolver
     */
    @Requires(id="deployment.resolver", optional = false)
    public DeploymentResolver m_resolver;

    /**
     *  Log service
     */
    @Requires(optional = true, defaultimplementation = DefaultLogService.class, proxy = false)
    LogService m_logger;

    /**
     * Executor for async apply
     */
    private ExecutorService m_executorService;

    /**
     * Bundle context
     */
    private BundleContext m_context;

    /**
     * Bundle tracker
     */
    private BundleTracker m_tracker;
    /**
     * Infrastructure Processor
     */
    private InfrastructureProcessor m_processor;

    /**
     * Resource processors
     */
    private Map<String, ResourceProcessor> m_processors = new HashMap<String, ResourceProcessor>();

    /**
     * Current infrastructure
     */
    private Infrastructure m_currentInfrastructure;

    /**
     * Coordinator
     */
    private DeploymentCoordinatorImpl m_coordinator;

    /**
     * Deployment Handle
     */
    private DeploymentHandleImpl m_handle;

    /**
     *  Deployment Plan
     */
    private DeploymentPlan m_plan;

    /**
     * Constructor
     * @param context
     */
    public InfrastructureDeployer(BundleContext context) {
        this.m_context = context;

    }

    // POJO Lifecycle methods
    // =================================================================================================================

    @Validate
    public void start(){
        m_executorService = Executors.newSingleThreadExecutor();
        this.log(LogService.LOG_INFO, "Starting");
        m_processor = new InfrastructureProcessor(m_context,m_logger);
        m_processor.start();

        m_tracker = new BundleTracker(m_context, Bundle.ACTIVE, new BundleTrackerCustomizer() {
            public Object addingBundle(final Bundle bundle, final BundleEvent event) {
                if (bundle.getBundleId() == m_context.getBundle().getBundleId()) {
                    // Not interested in our own bundle
                    return null;
                }
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
            this.log(LogService.LOG_INFO,"Resolving new infrastructure: "+newInfrastructure.getName());
            m_plan = m_resolver.resolve(newInfrastructure,m_currentInfrastructure);
            m_currentInfrastructure = newInfrastructure;
            if(m_handle!=null){
                m_handle.cancel();
                m_handle = null;
            }
            this.log(LogService.LOG_INFO,"Handle created for "+m_currentInfrastructure.getName());
            if(immediate){
                this.log(LogService.LOG_DEBUG,"Immediate deployment of "+m_currentInfrastructure.getName());
                this.apply();
            }
        } catch (DependencyResolutionException e) {
            this.log(LogService.LOG_WARNING, "Dependencies can't be resolved: " + newInfrastructure.getName(), e);
        }
    }

    @Bind(id = "processors", specification = "fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor", aggregate = true, optional = true)
    public void bindResourceProcessor(ServiceReference<ResourceProcessor> reference) {
        ResourceProcessor processor = m_context.getService(reference);
        String resourceType = (String) reference.getProperty("resource.type");
        m_processors.put(resourceType, processor);
    }

    @Unbind(id = "processors")
    public void unbindResourceProcessor(ServiceReference<ResourceProcessor> reference) {
        ResourceProcessor processor = m_context.getService(reference);
        m_processors.remove(processor);
    }

    // ManagedInfrastructure methods
    // =================================================================================================================


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

    public void apply(DeploymentListener... listeners){
        final DeploymentHandle deploymentHandle = this.getDeploymentHandle();
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
        return m_processors.get(type);
    }

    public DeploymentCoordinator getCoordinator() {
        if (m_coordinator == null) {
            m_coordinator = new DeploymentCoordinatorImpl(m_logger);
        }
        return m_coordinator;
    }

    // Log helper methods
    // =================================================================================================================

    /**
     *
     * @param logLevel
     * @param message
     */
    void log(int logLevel, String message){
        m_logger.log(logLevel,LOG_PREFIX+" Deployment: "+message);
    }

    /**
     *
     * @param logLevel
     * @param message
     * @param exception
     */
    void log(int logLevel, String message, Throwable exception){
        m_logger.log(logLevel,LOG_PREFIX+" Deployment: "+message, exception);
    }

}
