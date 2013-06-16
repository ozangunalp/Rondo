package fr.liglab.adele.rondo.infra.deployment;

import fr.liglab.adele.rondo.infra.model.Infrastructure;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/11/13
 * Time: 9:10 AM
 */
public interface DeploymentResolver {

    /**
     *
     * @param newInfrastructure
     * @param oldInfrastructure
     * @return
     * @throws DependencyResolutionException
     */
    DeploymentPlan resolve(Infrastructure newInfrastructure, Infrastructure oldInfrastructure) throws DependencyResolutionException;

}
