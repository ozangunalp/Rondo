package fr.liglab.adele.rondo.infra.deployment;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 10:46 AM
 */
public class DeploymentEvent {

    public enum Type {
        INSTALLING, COMPLETED
    }

    private DeploymentHandle m_handle;

    private Type m_type;

    public DeploymentEvent(DeploymentHandle m_handle, Type m_type) {
        this.m_handle = m_handle;
        this.m_type = m_type;
    }

    public DeploymentHandle getDeploymentHandle() {
        return m_handle;
    }

    public Type getType() {
        return m_type;
    }
}
