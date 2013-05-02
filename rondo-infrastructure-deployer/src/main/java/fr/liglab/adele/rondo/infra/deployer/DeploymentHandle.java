package fr.liglab.adele.rondo.infra.deployer;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 10:26 AM
 */
public interface DeploymentHandle {

    public enum DeploymentState {
        CREATED, RUNNING, UNSUCCESSFUL, SUCCESSFUL
    }

    public DeploymentState getState();

    public DeploymentPlan getPlan();

    public void apply();

    public void dryRun();

    public void registerListener(DeploymentListener listener);

    public void unregisterListener(DeploymentListener listener);


}
