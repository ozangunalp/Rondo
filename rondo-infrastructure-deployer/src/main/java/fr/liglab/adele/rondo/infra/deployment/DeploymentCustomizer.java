package fr.liglab.adele.rondo.infra.deployment;

/**
 * The customizer of a deployment is called in different stages of a deployment process for customizing the process.
 * A deployment handle has ony one customizer.
 */
public interface DeploymentCustomizer {

    /**
     * Called before deployment. The customizer can modify the deployment plan.
     * @param deploymentHandle handle
     * @return modified deployment plan
     */
    DeploymentPlan preDeployment(DeploymentHandle deploymentHandle);

    /**
     * Called after deployment.
     * @param deploymentHandle handle
     */
    void postDeployment(DeploymentHandle deploymentHandle);

    /**
     * Called after an error has occurred during the deployment process, before rolling back to initial state.
     * Customizer can survive the deployment by returning a new {@code DeploymentPlan}, or
     * lets the deployment continue with a rollback to initial state by sending {@literal null}.
     * @param deploymentHandle handle
     * @return survival deployment plan for keep alive the deployment or {@literal null} for continuing with rollback.
     */
    DeploymentPlan onRollback(DeploymentHandle deploymentHandle);

}
