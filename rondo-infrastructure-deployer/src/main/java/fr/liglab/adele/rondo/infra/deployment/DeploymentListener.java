package fr.liglab.adele.rondo.infra.deployment;

/**
 * Interface for deployment listeners
 */
public interface DeploymentListener {

    /**
     * Called when there is a new event (state change, etc.) for a deployment
     * @param event deployment event containing event type and referencing deployment handle
     */
    void handleEvent(DeploymentEvent event);

}
