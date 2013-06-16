package fr.liglab.adele.rondo.infra.deployment;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/9/13
 * Time: 2:31 PM
 */
public interface DeploymentCustomizer {

    /**
     *
     * @param deploymentHandle
     * @return
     */
    DeploymentPlan preDeployment(DeploymentHandle deploymentHandle);

    /**
     *
     * @param deploymentHandle
     */
    void postDeployment(DeploymentHandle deploymentHandle);

    /**
     *
     * @param deploymentHandle
     * @return
     */
    DeploymentPlan onRollback(DeploymentHandle deploymentHandle);

}
