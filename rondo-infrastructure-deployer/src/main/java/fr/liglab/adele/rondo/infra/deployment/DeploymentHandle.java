package fr.liglab.adele.rondo.infra.deployment;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 10:26 AM
 */
public interface DeploymentHandle {

    /**
     *
     */
    public enum DeploymentState {
        CREATED, DRYRUNNING, RUNNING, UNSUCCESSFUL, SUCCESSFUL
    }

    /**
     *
     * @return
     */
    public DeploymentState getState();

    /**
     *
     * @return
     */
    public DeploymentPlan getPlan();

    /**
     *
     */
    public void apply();

    /**
     *
     *
     */
    public void dryRun();

    /**
     *
     */
    public void cancel();

    /**
     *
     * @param listener
     */
    public void registerListener(DeploymentListener listener);

    /**
     *
     * @param listener
     */
    public void unregisterListener(DeploymentListener listener);

}
