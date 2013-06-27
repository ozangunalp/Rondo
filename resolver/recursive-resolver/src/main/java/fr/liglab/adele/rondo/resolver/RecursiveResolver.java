package fr.liglab.adele.rondo.resolver;

import fr.liglab.adele.rondo.infra.deployment.DependencyResolutionException;
import fr.liglab.adele.rondo.infra.deployment.DeploymentPlan;
import fr.liglab.adele.rondo.infra.deployment.DeploymentResolver;
import fr.liglab.adele.rondo.infra.model.*;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/25/13
 * Time: 8:29 PM
 */
@Component
@Instantiate
@Provides( specifications = DeploymentResolver.class )
public class RecursiveResolver implements DeploymentResolver {

    @Override
    public DeploymentPlan resolve(Infrastructure infrastructure, Infrastructure infrastructure2) throws DependencyResolutionException {

        Set<ResourceReference> resolvedSet = new LinkedHashSet<ResourceReference>();
        List<ResourceReference> unresolvedList = new ArrayList<ResourceReference>();

        List<ResourceReference> resourceReferences = infrastructure.getResourceReferences();
        Iterator<ResourceReference> iterator = resourceReferences.iterator();
        ResourceReference resourceReference = iterator.next();
        while(!resolvedSet.containsAll(resourceReferences)){
            System.out.println("Resolving: " + resourceReference);
            resolve(resourceReference, resolvedSet, unresolvedList);
            while(iterator.hasNext() && resolvedSet.contains(resourceReference)){
                resourceReference = iterator.next();
            }
        }
        System.out.println("\t"+resolvedSet);
        DeploymentPlan deploymentPlan = new DeploymentPlan();
        deploymentPlan.addAll(resolvedSet);
        return deploymentPlan;
    }

    public void resolve(ResourceReference resourceReference, Set<ResourceReference> resolved, List<ResourceReference> unresolved) throws DependencyResolutionException {
        System.out.println("\t"+resourceReference);
        unresolved.add(resourceReference);
        for (Dependency dependency : resourceReference.dependencies()) {
            ResourceReference provider = dependency.provider();
            if(!resolved.contains(provider)){
                if(unresolved.contains(provider)){
                        throw new DependencyResolutionException("Detected circular dependency between "+resourceReference+" -> "+ provider + " : " + unresolved);
                }
                resolve(provider, resolved, unresolved);
            }
        }
        resolved.add(resourceReference);
        unresolved.remove(resourceReference);
    }
}
