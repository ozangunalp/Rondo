package fr.liglab.adele.rondo.infra.deployment.transaction;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/2/13
 * Time: 5:16 PM
 */
public interface DeploymentParticipant {

    public void prepare() throws DeploymentException;

    public void cleanup();

    public void commit() throws DeploymentException;

    public void rollback();

}
