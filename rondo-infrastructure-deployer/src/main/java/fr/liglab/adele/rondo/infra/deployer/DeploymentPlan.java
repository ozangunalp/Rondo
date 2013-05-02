package fr.liglab.adele.rondo.infra.deployer;

import fr.liglab.adele.rondo.infra.model.ResourceReference;

import java.util.List;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 11:42 AM
 */
public class DeploymentPlan extends TreeMap<Integer, List<ResourceReference>> {

    public List<ResourceReference> put(List<ResourceReference> resourceReferences) {
        return super.put(super.size(), resourceReferences);
    }
}
