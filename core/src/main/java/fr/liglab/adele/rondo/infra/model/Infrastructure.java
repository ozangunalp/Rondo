package fr.liglab.adele.rondo.infra.model;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 3:55 PM
 */
public interface Infrastructure {

    public String getName();

    public List<Dependency> getDependencies();

    public List<ResourceReference> getResourceReferences();

    public <T extends ResourceDeclaration> Map<String, ResourceReference<T>> getResourceReferences(Class<T> resourceType);

    <T extends ResourceDeclaration> ResourceReference<T> getResourceReference(Class<T> resourceType, String resourceId);

    public List<ResourceDeclaration> getResources();

    public <T extends ResourceDeclaration> T getResource(ResourceReference<T> reference);


}
