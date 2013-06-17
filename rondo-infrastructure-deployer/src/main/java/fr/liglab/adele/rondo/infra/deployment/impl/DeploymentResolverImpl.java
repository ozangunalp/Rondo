package fr.liglab.adele.rondo.infra.deployment.impl;

import fr.liglab.adele.rondo.infra.deployment.DependencyResolutionException;
import fr.liglab.adele.rondo.infra.deployment.DeploymentPlan;
import fr.liglab.adele.rondo.infra.deployment.DeploymentResolver;
import fr.liglab.adele.rondo.infra.model.Dependency;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import fr.liglab.adele.rondo.infra.model.ResourceReference;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import java.util.*;

/**
 * Simple Resolver
 */
@Component
@Instantiate
@Provides( specifications = DeploymentResolver.class )
public class DeploymentResolverImpl implements DeploymentResolver {

    @Override
    public DeploymentPlan resolve(Infrastructure newInfrastructure, Infrastructure oldInfrastructure) throws DependencyResolutionException {
        DeploymentPlan deploymentPlan = new DeploymentPlan();
        List<Dependency> dependencies = newInfrastructure.getDependencies();
        Set<ResourceReference> providers = new HashSet<ResourceReference>();
        Set<ResourceReference> requirers = new HashSet<ResourceReference>();
        for (Dependency dep : dependencies) {
            providers.add(dep.provider());
            requirers.add((dep.requirer()));
        }
        // Level 0
        // find single resources
        LinkedList<ResourceReference> resourceReferences = new LinkedList<ResourceReference>(newInfrastructure.getResourceReferences());
        resourceReferences.removeAll(providers);
        resourceReferences.removeAll(requirers);
        // add single providers to the deployment plan
        deploymentPlan.addAll(resourceReferences);
        // Level 1
        // find providers without dependency
        providers.removeAll(requirers);
        List<ResourceReference> satisfiedProviders = new ArrayList<ResourceReference>();
        satisfiedProviders.addAll(providers);
        // add providers without dependency to the deployment plan
        deploymentPlan.addAll(new LinkedList<ResourceReference>(providers));
        // Level 2 +
        while (!requirers.isEmpty()) {
            LinkedList<ResourceReference> nextProviders = new LinkedList<ResourceReference>();
            Set<ResourceReference> requirersToRemove = new HashSet<ResourceReference>();
            // iterate over requirer
            for (ResourceReference requirer : requirers) {
                Set<ResourceReference> requirerProviders = new HashSet<ResourceReference>();
                // collect requirers providers
                for (Dependency dep : dependencies) {
                    if (dep.requirer().equals(requirer)) {
                        requirerProviders.add(dep.provider());
                    }
                }
                // if all its providers are satisfied then add requirer into next deployment plan stage and enlist for removal
                if (satisfiedProviders.containsAll(requirerProviders)) {
                    nextProviders.add(requirer);
                    requirersToRemove.add(requirer);
                }

            }
            if (requirersToRemove.isEmpty()) {
                throw new DependencyResolutionException("Dependency cannot be resolved");
            }
            requirers.removeAll(requirersToRemove);
            deploymentPlan.addAll(nextProviders);
            satisfiedProviders.addAll(nextProviders);
        }
        return deploymentPlan;
    }
}
