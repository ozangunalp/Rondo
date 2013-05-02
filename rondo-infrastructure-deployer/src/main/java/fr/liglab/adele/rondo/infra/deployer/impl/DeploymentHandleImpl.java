package fr.liglab.adele.rondo.infra.deployer.impl;

import fr.liglab.adele.rondo.infra.deployer.DeploymentHandle;
import fr.liglab.adele.rondo.infra.deployer.DeploymentListener;
import fr.liglab.adele.rondo.infra.deployer.DeploymentPlan;
import fr.liglab.adele.rondo.infra.deployer.ManagedInfrastructure;
import fr.liglab.adele.rondo.infra.deployer.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployer.processor.ResourceProcessorException;
import fr.liglab.adele.rondo.infra.model.Bundle;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import fr.liglab.adele.rondo.infra.model.ResourceReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/30/13
 * Time: 10:03 AM
 */
public class DeploymentHandleImpl implements DeploymentHandle {

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
        int deploymentStage;
        try {
            for (Map.Entry<Integer, List<ResourceReference>> e : m_plan.entrySet()) {
                deploymentStage = e.getKey();
                for (ResourceReference resRef : e.getValue()) {
                    Class type = resRef.type();
                    // find the resource processor for this type of resource
                    ResourceProcessor resourceProcessor = m_infrastructure.getResourceProcessor(type.getName());
                    if (resourceProcessor != null) {
                        Infrastructure inf = m_infrastructure.getInfrastructureModel();
                        ResourceReference<Bundle> bundlRef = resRef.adapt(Bundle.class);
                        Bundle bundle = inf.getResource(bundlRef);
                        if (resourceProcessor != null) {
                            resourceProcessor.begin(bundle);
                        } else {
                            throw new Exception("");// Deployment Exception
                        }
                    } else {
                        throw new ResourceProcessorException("Resource processor not founf for resource type " + type.getName());
                    }
                }
            }
        } catch (Exception e) {
            //deploymentStage
            // do rollback
            //TODO should rollback
            e.printStackTrace();
        } finally {

        }
    }

    @Override
    public void dryRun() {

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
