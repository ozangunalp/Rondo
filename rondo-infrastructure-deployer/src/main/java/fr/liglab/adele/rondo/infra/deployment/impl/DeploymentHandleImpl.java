package fr.liglab.adele.rondo.infra.deployment.impl;

import fr.liglab.adele.rondo.infra.deployment.*;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import fr.liglab.adele.rondo.infra.model.ResourceReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/30/13
 * Time: 10:03 AM
 */
public class DeploymentHandleImpl implements DeploymentHandle {

    /**
     *
     */
    private static final int DEFAULT_DEPLOYMENT_TIMEOUT = 0; // zero means unlimited power

    /**
     *
     */
    private final int m_timeout;

    /**
     *
     */
    private final DeploymentCustomizer m_customizer;

    /**
     *
     */
    private ManagedInfrastructure m_infrastructure;

    /**
     *
     */
    private DeploymentPlan m_plan;

    /**
     *
     */
    private DeploymentState m_currentState;

    /**
     *
     */
    private final List<DeploymentListener> m_listeners;

    /**
     *
     * @param plan
     * @param infra
     * @param customizer
     * @param deploymentTimeout
     */
    public DeploymentHandleImpl(DeploymentPlan plan, ManagedInfrastructure infra, DeploymentCustomizer customizer, int deploymentTimeout) {
        this.m_currentState = DeploymentState.CREATED;
        this.m_listeners = new ArrayList<DeploymentListener>();
        this.m_plan = plan;
        this.m_infrastructure = infra;
        this.m_customizer = customizer;
        this.m_timeout = deploymentTimeout>0 ? deploymentTimeout : DEFAULT_DEPLOYMENT_TIMEOUT;
    }

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
        setState(DeploymentState.RUNNING);
        DeploymentTransaction transaction = m_infrastructure.getCoordinator().create("infrastructure", m_timeout);

        // set up a working directory for the deployment
        File workingDir = new File("deployment", "cache");
        workingDir.mkdirs();
        transaction.store("working.dir", workingDir);

        // call customizer predeployment
        DeploymentPlan deploymentPlan = this.m_plan;
        if(m_customizer!=null){
            deploymentPlan = m_customizer.preDeployment(this);
        }

        // start transaction
        try {
            this.callProcessors(transaction, deploymentPlan);
        } catch (Throwable t) { // prepared processors are notified: they are supposed to clean up their mess...
            transaction.fail(t);
            //TODO log preparing error
            System.out.println("failed! cleaning up, reason: ");
            System.out.println(transaction.getFailure().toString());
            transaction.getFailure().printStackTrace();
        } finally {
            try { // if there is to catch errors on commit
                transaction.end();
            } catch (Throwable t) {
                //TODO log commit error...
                System.out.println("ended with ");
                System.out.println(t.toString());
            } finally {
                if(transaction.getFailure()==null){
                    setState(DeploymentState.SUCCESSFUL);
                } else {
                    setState(DeploymentState.UNSUCCESSFUL);
                }
                // call customizer post deployment
                if(m_customizer!=null){
                    m_customizer.postDeployment(this);
                }
            }
        }
    }

    @Override
    public void dryRun() {
        setState(DeploymentState.DRYRUNNING);
        DeploymentTransaction transaction = m_infrastructure.getCoordinator().create("infrastructure", m_timeout);
        DeploymentPlan deploymentPlan = m_plan;
        if(m_customizer!=null){
            deploymentPlan = m_customizer.preDeployment(this);
        }
        // start transaction
        try {
            this.callProcessors(transaction,deploymentPlan);
        } catch (Throwable t) {
            transaction.fail(t);
            t.printStackTrace();
        } // don't do commit this is a dry run!!!
        transaction.fail(new DeploymentException("This is was a dry run! Cleaned up preparing mess.."));
        // call customizer post deployment
        if(m_customizer!=null){
            m_customizer.postDeployment(this);
        }
        System.out.println(transaction.getFailure().toString());
        setState(DeploymentState.CREATED);
    }

    @Override
    public void cancel() {
        if(m_currentState.equals(DeploymentState.RUNNING) || m_currentState.equals(DeploymentState.DRYRUNNING)){
            // transaction.cancel;

        }
    }

    /**
     *
     * @param newState
     */
    private void setState(DeploymentState newState) {
        this.m_currentState = newState;
        DeploymentEvent.Type type = (newState == DeploymentState.RUNNING) ? DeploymentEvent.Type.INSTALLING : DeploymentEvent.Type.COMPLETED;
        for (DeploymentListener listener : m_listeners) {
            listener.handleEvent(new DeploymentEvent(this, type));
        }
    }

    /**
     *
     * @param transaction
     * @throws DeploymentException
     */
    private void callProcessors(DeploymentTransaction transaction, DeploymentPlan deploymentPlan) throws DeploymentException {
        // add participants to the transaction
        for (ResourceReference resourceReference : deploymentPlan) {
            Class type = resourceReference.type();
            System.out.println(type.getName());
            ResourceProcessor processor = m_infrastructure.getResourceProcessor(type.getName());
            if (processor != null) {
                ResourceDeclaration resource = m_infrastructure.getInfrastructureModel().getResource(resourceReference);
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
