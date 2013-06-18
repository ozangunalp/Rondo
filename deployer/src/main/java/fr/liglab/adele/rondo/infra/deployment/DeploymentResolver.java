package fr.liglab.adele.rondo.infra.deployment;

import fr.liglab.adele.rondo.infra.model.Infrastructure;

/**
 * Interface for resolvers that create a {@code DeploymentPlan} for infrastructure deployment
 */
public interface DeploymentResolver {

    /**
     * Resolves the deployment steps of new infrastructure from old infrastructure
     * Old infrastructure may be {@literal null} depending on the installment
     * @param newInfrastructure new infrastructure model
     * @param oldInfrastructure old infrastructure model
     * @return the deployment plan for the transition from old to new infrastructure
     * @throws DependencyResolutionException if cannot resolve dependencies between resource declarations in the infrastructure
     */
    DeploymentPlan resolve(Infrastructure newInfrastructure, Infrastructure oldInfrastructure) throws DependencyResolutionException;

}
