package fr.liglab.adele.rondo.infra.deployment;

import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentCoordinator;
import fr.liglab.adele.rondo.infra.model.Infrastructure;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 10:02 AM
 */
public interface ManagedInfrastructure {

    public Infrastructure getInfrastructureModel();

    public DeploymentHandle getDeploymentHandle();

    public void apply(DeploymentHandle handle, DeploymentListener... listeners);

    public ResourceProcessor getResourceProcessor(String type);

    public DeploymentCoordinator getCoordinator();

}
