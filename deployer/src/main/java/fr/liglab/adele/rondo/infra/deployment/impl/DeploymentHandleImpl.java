package fr.liglab.adele.rondo.infra.deployment.impl;

import fr.liglab.adele.rondo.infra.deployment.*;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import fr.liglab.adele.rondo.infra.model.ResourceReference;
import org.osgi.service.log.LogService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Deployment Handle implementation
 */
public class DeploymentHandleImpl implements DeploymentHandle {

    /**
     * Default deployment timeout is 0 (unlimited)
     */
    private static final int DEFAULT_DEPLOYMENT_TIMEOUT = 0; // zero means unlimited power

    /**
     * Deployment Timeout
     */
    private final int m_timeout;

    /**
     * Deployment Customizer
     */
    private final DeploymentCustomizer m_customizer;

    /**
     * Deployer
     */
    private InfrastructureDeployer m_deployer;

    /**
     * Deployment plan
     */
    private DeploymentPlan m_plan;

    /**
     * Current state of the deployment
     */
    private DeploymentState m_currentState;

    /**
     * Listeners
     */
    private final List<DeploymentListener> m_listeners;

    /**
     * Constructor
     * @param plan
     * @param deployer
     * @param customizer
     * @param deploymentTimeout
     */
    public DeploymentHandleImpl(DeploymentPlan plan, InfrastructureDeployer deployer, DeploymentCustomizer customizer, int deploymentTimeout) {
        this.m_currentState = DeploymentState.CREATED;
        this.m_listeners = new ArrayList<DeploymentListener>();
        this.m_plan = plan;
        this.m_deployer = deployer;
        this.m_customizer = customizer;
        this.m_timeout = (deploymentTimeout > 0) ? deploymentTimeout : DEFAULT_DEPLOYMENT_TIMEOUT;
    }

    // DeploymentHandle methods
    // =================================================================================================================

    @Override
    public DeploymentState getState() {
        return this.m_currentState;
    }

    @Override
    public DeploymentPlan getPlan() {
        return this.m_plan;
    }

    @Override
    public void apply() {
        m_deployer.log(LogService.LOG_INFO, "Starting to run with timeout: " + m_timeout);
        setState(DeploymentState.RUNNING);

        // call customizer predeployment
        DeploymentPlan deploymentPlan = this.m_plan;
        if(m_customizer!=null){
            m_deployer.log(LogService.LOG_DEBUG, "Calling customizer preDeployment");
            deploymentPlan = m_customizer.preDeployment(this);
        }

        DeploymentTransaction transaction = m_deployer.getCoordinator().create("infrastructure", m_timeout,false);
        // set up a working directory for the deployment
        File workingDir = new File("deployment", "cache");
        workingDir.mkdirs();
        transaction.store("working.dir", workingDir);
        m_deployer.log(LogService.LOG_DEBUG, "Setting up deployment directory: " + workingDir.getPath());

        // start transaction
        try {
            this.callProcessors(transaction, deploymentPlan);
        } catch (Throwable t) { // prepared processors are notified: they are supposed to clean up their mess...
            transaction.fail(t);
            m_deployer.log(LogService.LOG_WARNING,"Failed at prepare, reason:",transaction.getFailure());
        } finally {
            try { // if there is to catch errors on commit
                m_deployer.log(LogService.LOG_INFO, "Prepared with success, proceeding with commit");
                transaction.end();
            } catch (Throwable t) {
                m_deployer.log(LogService.LOG_WARNING,"Failed at commit, reason:",t);
            } finally {
                if(transaction.getFailure()==null){
                    setState(DeploymentState.SUCCESSFUL);
                } else {
                    setState(DeploymentState.UNSUCCESSFUL);
                }
                // call customizer post deployment
                if(m_customizer!=null){
                    m_deployer.log(LogService.LOG_DEBUG, "Calling customizer postDeployment");
                    m_customizer.postDeployment(this);
                }
            }
        }
        m_deployer.log(LogService.LOG_INFO, "Finished with " + m_currentState);
    }

    @Override
    public void dryRun() {
        m_deployer.log(LogService.LOG_INFO, "Starting to dry-run (prepare but, won't commit) with timeout: " + m_timeout);
        setState(DeploymentState.DRYRUNNING);

        DeploymentPlan deploymentPlan = m_plan;
        if(m_customizer!=null){
            m_deployer.log(LogService.LOG_DEBUG, "Calling customizer preDeployment");
            deploymentPlan = m_customizer.preDeployment(this);
        }
        long l = System.currentTimeMillis();
        DeploymentTransaction transaction = m_deployer.getCoordinator().create("infrastructure", m_timeout,false);
        // start transaction
        try {
            this.callProcessors(transaction,deploymentPlan);
        } catch (Throwable t) {
            transaction.fail(t);
            m_deployer.log(LogService.LOG_WARNING,"Failed at prepare, reason:",transaction.getFailure());
        } // don't do commit this is a dry run!!!
        //transaction.fail(new DeploymentException("This is was a dry run! Cleaned up preparing mess.."));
        // call customizer post deployment
        if(m_customizer!=null){
            m_deployer.log(LogService.LOG_DEBUG, "Calling customizer postDeployment");
            m_customizer.postDeployment(this);
        }
        long time = System.currentTimeMillis() - l;
        m_deployer.log(LogService.LOG_INFO, "Dry-run finished in: "+time);
        setState(DeploymentState.CREATED);
    }

    @Override
    public void cancel() {
        if(m_currentState.equals(DeploymentState.RUNNING) || m_currentState.equals(DeploymentState.DRYRUNNING)){
            DeploymentTransaction transaction = m_deployer.getCoordinator().getTransaction();
            if(!transaction.isTerminated()){
                transaction.fail(new DeploymentException("Deployment Canceled"));
                try {
                    transaction.end();
                } catch (DeploymentException e) {
                    // TODO
                }
            }
        }

    }

    // Util methods
    // =================================================================================================================

    /**
     *
     * @param newState
     */
    private void setState(DeploymentState newState) {
        this.m_currentState = newState;
        DeploymentEvent.Type type = (newState == DeploymentState.RUNNING) ? DeploymentEvent.Type.STARTING : DeploymentEvent.Type.COMPLETED;
        for (DeploymentListener listener : m_listeners) {
            listener.handleEvent(new DeploymentEvent(this, type));
        }
    }

    /**
     * Find resource processors corresponding to the resource declaration.
     * Ask processor to give a participant and add the participant to the transaction
     * @param transaction
     * @throws DeploymentException
     */
    private void callProcessors(DeploymentTransaction transaction, DeploymentPlan deploymentPlan) throws DeploymentException {
        // add participants to the transaction
        for (ResourceReference resourceReference : deploymentPlan) {
            Class type = resourceReference.type();
            ResourceProcessor processor = m_deployer.getResourceProcessor(type.getName());
            if (processor != null) {
                ResourceDeclaration resource = m_deployer.getInfrastructureModel().getResource(resourceReference);
                // create participant
                DeploymentParticipant participant = processor.process(resource, transaction);
                transaction.addParticipant(participant);
                // hurray if we come here!
            } else {
                throw new DeploymentException("Resource processor not found for resource type " + type.getName());
            }
        }
        transaction.prepare();
    }

    // Listener methods
    // =================================================================================================================

    @Override
    public void registerListener(DeploymentListener listener) {
        synchronized (m_listeners) {
            m_listeners.add(listener);
        }
    }

    @Override
    public void unregisterListener(DeploymentListener listener) {
        synchronized (m_listeners) {
            m_listeners.remove(listener);
        }
    }

}
