package fr.liglab.adele.rondo.infra.deployment;

/**
 * Event that is sent to listeners
 */
public class DeploymentEvent {

    /**
     * Event type
     */
    public enum Type {
        STARTING, COMPLETED
    }

    /**
     * Handle for this
     */
    private DeploymentHandle m_handle;

    /**
     * Type of this event
     */
    private Type m_type;

    /**
     * Constructor
     * @param m_handle
     * @param m_type
     */
    public DeploymentEvent(DeploymentHandle m_handle, Type m_type) {
        this.m_handle = m_handle;
        this.m_type = m_type;
    }

    /**
     * @return handle of this event
     */
    public DeploymentHandle getDeploymentHandle() {
        return m_handle;
    }

    /**
     * @return type of this event
     */
    public Type getType() {
        return m_type;
    }
}
