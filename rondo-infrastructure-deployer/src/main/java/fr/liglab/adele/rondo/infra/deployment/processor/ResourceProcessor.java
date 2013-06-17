package fr.liglab.adele.rondo.infra.deployment.processor;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;

/**
 *
 */
public interface ResourceProcessor {

    /**
     *
     * @param resource
     * @param transaction
     * @return
     * @throws DeploymentException
     */
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException;

}
