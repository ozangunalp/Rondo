package fr.liglab.adele.rondo.resolver;

import fr.liglab.adele.rondo.infra.deployment.DependencyResolutionException;
import fr.liglab.adele.rondo.infra.deployment.DeploymentPlan;
import fr.liglab.adele.rondo.infra.deployment.DeploymentResolver;
import fr.liglab.adele.rondo.infra.model.*;
import fr.liglab.adele.rondo.infra.model.ResourceReference;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import org.jgrapht.*;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/26/13
 * Time: 10:48 PM
 */
@Component
@Instantiate
@Provides( specifications = DeploymentResolver.class )
public class GraphtResolver implements DeploymentResolver {

    @Override
    public DeploymentPlan resolve(Infrastructure newInfrastructure, Infrastructure oldInfrastructure) throws DependencyResolutionException {
        DefaultDirectedGraph<ResourceReference, Dependency> directedGraph = new DefaultDirectedGraph<ResourceReference, Dependency>(Dependency.class);
        List<ResourceReference> resourceReferences = newInfrastructure.getResourceReferences();
        List<Dependency> dependencies = newInfrastructure.getDependencies();
        for (ResourceReference resourceReference : resourceReferences) {
            directedGraph.addVertex(resourceReference);
        }
        for (Dependency dependency : dependencies) {
            directedGraph.addEdge(dependency.requirer(), dependency.provider(), dependency);
        }
        // Detect cycles
        CycleDetector<ResourceReference, Dependency> cycleDetector = new CycleDetector<ResourceReference, Dependency>(directedGraph);
        Set<ResourceReference> cycles = cycleDetector.findCycles();
        if(!cycles.isEmpty()){
            throw new DependencyResolutionException("Detected "+cycles.size()+" cyclic dependencies:" + cycles);
        }
        // create deployment plan
        DeploymentPlan plan = new DeploymentPlan();
        TopologicalOrderIterator<ResourceReference, Dependency> topOpIt = new TopologicalOrderIterator<ResourceReference, Dependency>(directedGraph);
        while(topOpIt.hasNext()) {
            ResourceReference next = topOpIt.next();
            plan.addFirst(next);
        }
//        for (ResourceReference resourceReference : plan) {
//            System.out.println(resourceReference);
//            for (Dependency dependency : resourceReference.dependencies()) {
//                System.out.println("\t-> "+dependency.requirer());
//            }
//        }
        return plan;
    }
}
