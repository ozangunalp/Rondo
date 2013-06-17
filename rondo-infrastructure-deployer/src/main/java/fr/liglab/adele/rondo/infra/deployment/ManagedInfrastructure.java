package fr.liglab.adele.rondo.infra.deployment;

import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentCoordinator;
import fr.liglab.adele.rondo.infra.model.Infrastructure;

/**
 * Entry point for an infrastructure deployment.
 */
public interface ManagedInfrastructure {

    /**
     * @return the infrastructure model.
     */
    public Infrastructure getInfrastructureModel();

    /**
     * Retrieves the {@code DeploymentHandle} of this infrastructure.
     * If no handle is previously created, creates one and returns it.
     * @return the current deployment handle of this infrastructure.
     */
    public DeploymentHandle getDeploymentHandle();

    /**
     * Starts the deployment of the managed infrastructure registering given listeners.
     * Delegates the application of the deployment to the {@code DeploymentHandle.apply} method
     * @param listeners listeners registered for the {@code DeploymentHandle}.
     */
    public void apply(DeploymentListener... listeners);

    /**
     * Retrieve the discovered {@code ResourceProcessor} for the corresponding resource type.
     * Resource processors are dynamic entities that may appear and disappear; and may not be the same for every deployment
     * @param type resource type
     * @return resource processor associated the resource type
     */
    public ResourceProcessor getResourceProcessor(String type);

    /**
     * @return the deployment coordinator of this managed infrastructure
     */
    public DeploymentCoordinator getCoordinator();

}
