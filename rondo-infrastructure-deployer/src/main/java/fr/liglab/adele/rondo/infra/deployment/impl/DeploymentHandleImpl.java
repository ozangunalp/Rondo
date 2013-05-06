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

    private static final int DEPLOYMENT_TIMEOUT = 5 * 60 * 1000;

    ManagedInfrastructure m_infrastructure;

    DeploymentState m_currentState;

    List<DeploymentListener> m_listeners;

    DeploymentPlan m_plan;

    public DeploymentHandleImpl(DeploymentPlan plan, ManagedInfrastructure infra) {
        this.m_currentState = DeploymentState.CREATED;
        this.m_listeners = new ArrayList<DeploymentListener>();
        this.m_plan = plan;
        this.m_infrastructure = infra;
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
        DeploymentTransaction transaction = m_infrastructure.getCoordinator().create("infrastructure", DEPLOYMENT_TIMEOUT);
        // set up a working directory for the deployment
        File workingDir = new File("deployment", "cache");
        File deploymentDir = new File("deployment", "artifacts");
        workingDir.mkdirs();
        transaction.store("working.dir", workingDir);
        transaction.store("deployment.dir", deploymentDir);
        // start transaction
        try {
            this.callProcessors(transaction);
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

            }
        }
    }

    @Override
    public void dryRun() {
        setState(DeploymentState.DRYRUNNING);
        DeploymentTransaction transaction = m_infrastructure.getCoordinator().create("infrastructure", DEPLOYMENT_TIMEOUT);
        try {
            this.callProcessors(transaction);
        } catch (Throwable t) {
            transaction.fail(t);
            t.printStackTrace();
        } // don't do commit this is a dry run!!!
        transaction.fail(new DeploymentException("This is was a dry run! need to clean up"));
    }


    private void callProcessors(DeploymentTransaction transaction) throws DeploymentException {
        for (ResourceReference resourceReference : this.m_plan) {
            Class type = resourceReference.type();
            ResourceProcessor processor = m_infrastructure.getResourceProcessor(type.getName());
            if (processor != null) {
                ResourceDeclaration resource = m_infrastructure.getInfrastructureModel().getResource(resourceReference);
                // create participant
                DeploymentParticipant participant = processor.process(resource, transaction);
                transaction.addParticipant(participant);
                // hurray if we come here!
            } else {
                throw new DeploymentException("Resource processor for not found for resource type" + type.getName());
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

    private void setState(DeploymentState newState) {
        this.m_currentState = newState;
        DeploymentEvent.Type type = (newState == DeploymentState.RUNNING) ? DeploymentEvent.Type.INSTALLING : DeploymentEvent.Type.COMPLETED;
        synchronized (m_listeners) {
            for (DeploymentListener listener : m_listeners) {
                listener.handleEvent(new DeploymentEvent(this, type));
            }
        }
    }

}
