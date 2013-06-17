package fr.liglab.adele.rondo.infra.deployment;

/**
 * Represents the process for a deployment.
 */
public interface DeploymentHandle {

    /**
     * Enum for deployment states
     * {@value this.DeploymentState.CREATED} : Deployment created, not yet started.
     *
     * {@value this.DeploymentState.DRYRUNNING} : Deployment running on dry mode.
     * Execution won't effect the platform and prepared resources will be cleaned up.
     *
     * {@value this.DeploymentState.RUNNING} : Deployment created and started running.
     *
     * {@value this.DeploymentState.UNSUCCESSFUL} : Deployment finished but was unsuccessful.
     * A unsuccessful deployment should have restored the initial state in which it has started.
     *
     * {@value this.DeploymentState.SUCCESSFUL} : Deployment finished and was successful.
     * A Successful deployment should have attained the desired state described in the infrastructure model.
     *
     */
    public enum DeploymentState {
        CREATED, DRYRUNNING, RUNNING, UNSUCCESSFUL, SUCCESSFUL
    }

    /**
     * @return the current state of this deployment process
     */
    public DeploymentState getState();

    /**
     * @return the plan for this deployment process
     */
    public DeploymentPlan getPlan();

    /**
     * Apply this deployment according to the deployment plan.
     * Associated deployment plan can be altered by a {@code fr.liglab.adele.rondo.infra.deployment.DeploymentCustomizer} before deployment.
     * Execution can result successfully or unsuccessfully.
     */
    public void apply();

    /**
     * Test this deployment by only preparing resources
     * This operation must not have any effect on the platform.
     */
    public void dryRun();

    /**
     * Cancel the execution of this deployment and rollback to the inital state if this deployment is running or dryrunning.
     */
    public void cancel();

    /**
     * @param listener register this listener
     */
    public void registerListener(DeploymentListener listener);

    /**
     * @param listener unregister this listener
     */
    public void unregisterListener(DeploymentListener listener);

}
