package fr.liglab.adele.rondo.infra.deployment.transaction;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;

/**
 * Interface for deployment transaction participants.
 * Deployment participants are created by resource processors for each of their participation on a transactions.
 *
 */
public interface DeploymentParticipant {

    /**
     * @return participant id
     */
    public String getParticipantId();

    /**
     * Prepare the resource to be deployed to the commit.
     * @throws DeploymentException if participant can't prepare the resource
     */
    public void prepare() throws DeploymentException;

    /**
     * Clean up the preparations.
     */
    public void cleanup();

    /**
     * Commit the changes.
     * @throws DeploymentException if an error occurs in the commit phase
     */
    public void commit() throws DeploymentException;

    /**
     * Rollback to the initial state.
     */
    public void rollback();

}
