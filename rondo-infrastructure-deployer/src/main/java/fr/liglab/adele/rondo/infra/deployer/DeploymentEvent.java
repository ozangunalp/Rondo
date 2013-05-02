package fr.liglab.adele.rondo.infra.deployer;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 10:46 AM
 */
public interface DeploymentEvent {

    public enum Type {
        INSTALLING, COMPLETED, UNINSTALLING
    }

}
