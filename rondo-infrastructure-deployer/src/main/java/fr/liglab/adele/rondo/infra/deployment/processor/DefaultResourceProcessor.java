package fr.liglab.adele.rondo.infra.deployment.processor;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/3/13
 * Time: 1:02 AM
 */
public abstract class DefaultResourceProcessor implements ResourceProcessor {

    List<DeploymentParticipant> deploymentParticipants;

    public DefaultResourceProcessor() {
        this.deploymentParticipants = new ArrayList<DeploymentParticipant>();
    }

    @Override
    public abstract DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException;

    protected void addParticipant(DeploymentParticipant participant) {
        this.deploymentParticipants.add(participant);
    }

    public List<DeploymentParticipant> getDeploymentParticipants() {
        return this.deploymentParticipants;
    }
}
