package fr.liglab.adele.rondo.infra.deployer;

import fr.liglab.adele.rondo.infra.deployer.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.model.Infrastructure;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 10:02 AM
 */
public interface ManagedInfrastructure {

    public Infrastructure getInfrastructureModel();

    public DeploymentHandle getDeploymentHandle();

    public ResourceProcessor getResourceProcessor(String type);

}
