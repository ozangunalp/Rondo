package fr.liglab.adele.rondo.infra.deployer;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 10:34 AM
 */
public interface DeploymentListener {

    void handleEvent(DeploymentEvent event);

}
