package fr.liglab.adele.rondo.infra.deployment.processor;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/30/13
 * Time: 9:40 AM
 */
public interface ResourceProcessor {

    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException;

}
