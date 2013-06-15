package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Dependency;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import fr.liglab.adele.rondo.infra.model.ResourceReference;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 5:26 PM
 */
public class InfrastructureImpl implements Infrastructure {

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Map<Class, LinkedHashMap<String, AbstractResourceDeclaration>> resources = new HashMap<Class, LinkedHashMap<String, AbstractResourceDeclaration>>();

    /**
     *
     */
    private LinkedList<DependencyImpl> dependencies = new LinkedList<DependencyImpl>();

    /**
     *
     */
    private List<ResourceReference> exports = new ArrayList<ResourceReference>();

    // Static

    public static InfrastructureImpl infrastructure(String name) {
        return new InfrastructureImpl(name);
    }

    public static InfrastructureImpl infrastructure() {
        return new InfrastructureImpl(null);
    }

    public InfrastructureImpl(String name) {
        this.name = name;
    }

    // Builder methods

    public InfrastructureImpl dependency(DependencyImpl dependency) {
        this.dependencies.add(dependency);
        return this;
    }

    public InfrastructureImpl dependsOn(Class resourceType, String resourceId) {
        DependencyImpl d = this.dependencies.getLast();
        ResourceReferenceImpl res = d.provider;
        return res.dependsOn(resourceType, resourceId);
    }

    public InfrastructureImpl exports(ResourceReferenceImpl... resourceReferences) {
        this.exports.addAll(Arrays.asList(resourceReferences));
        return this;
    }

    public <T extends AbstractResourceDeclaration<T>> InfrastructureImpl resource(T resource) {
        // Get the first interface of the resource, it must be the type
        Class interFace = null;
        Class<?>[] interfaces = resource.getClass().getInterfaces();
        if (interfaces.length > 0) {
            interFace = interfaces[0];
        } else {
            //TODO throw some exception
        }
        LinkedHashMap<String, AbstractResourceDeclaration> map = resources.get(interFace);
        if (map == null) {
            map = new LinkedHashMap<String, AbstractResourceDeclaration>();
            resources.put(interFace, map);
        }
        String resourceId = resource.id();
        if (map.containsKey(resourceId)) {
            // TODO should throw some exception
        } else {
            map.put(resource.id(), resource);
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ResourceDeclaration> ResourceReferenceImpl<T> resource(Class<T> type, String id) {
        return new ResourceReferenceImpl(type, id);
    }

    public void nameIfUnnamed(String name){
        if(this.name==null){
            this.name = name;
        }
    }

    // Getters

    public String getName() {
        return name;
    }

    @SuppressWarnings("unchecked")
    public <T extends ResourceDeclaration> T getResource(Class<T> resourceType, String resourceId) {
        T resource = null;
        LinkedHashMap<String, AbstractResourceDeclaration> res = this.resources.get(resourceType);
        if (res != null) {
            ResourceDeclaration entity = res.get(resourceId);
            resource = (T) entity;
        }
        return resource;
    }

    @SuppressWarnings("unchecked")
    public List<ResourceDeclaration> getResources() {
        List<ResourceDeclaration> allResources = new ArrayList<ResourceDeclaration>();
        for (LinkedHashMap<String, AbstractResourceDeclaration> resource : this.resources.values()) {
            allResources.addAll(resource.values());
        }
        return allResources;
    }

    @SuppressWarnings("unchecked")
    public <T extends ResourceDeclaration> Map<String, T> getResources(Class<T> resourceType) {
        Map<String, T> res = new HashMap<String, T>();
        LinkedHashMap<String, AbstractResourceDeclaration> resType = this.resources.get(resourceType);
        if (resType != null) {
            for (Map.Entry<String, AbstractResourceDeclaration> e : resType.entrySet()) {
                res.put(e.getKey(), (T) e.getValue().self());
            }
        }
        return Collections.unmodifiableMap(res);
    }

    @SuppressWarnings("unchecked")
    public List<ResourceReference> getResourceReferences() {
        List<ResourceReference> references = new ArrayList<ResourceReference>();
        for (Map.Entry<Class, LinkedHashMap<String, AbstractResourceDeclaration>> resource : resources.entrySet()) {
            Class clazz = resource.getKey();
            for (String key : resource.getValue().keySet()) {
                references.add(new ResourceReferenceImpl(clazz, key));
            }
        }
        return references;
    }

    public <T extends ResourceDeclaration> T getResource(ResourceReference<T> reference) {
        return this.getResource(reference.type(), reference.id());
    }

    public List<Dependency> getDependencies() {
        ArrayList<Dependency> dependencies = new ArrayList<Dependency>();
        dependencies.addAll(this.dependencies);
        return dependencies;
    }

    public List<ResourceReference> getExports() {
        List<ResourceReference> references = new ArrayList<ResourceReference>();
        references.addAll(this.exports);
        return references;
    }

    // Inner classes

    public class ResourceReferenceImpl<T extends ResourceDeclaration> implements ResourceReference {

        private final Class<T> resourceType;
        private final String resourceId;


        public ResourceReferenceImpl(Class<T> resourceType, String resourceId) {
            this.resourceType = resourceType;
            this.resourceId = resourceId;
        }

        public ResourceReference adapt(Class clazz) {
            System.out.println(resourceType.getName() + clazz.getName());
            if (clazz.equals(resourceType)) {
                return this;
            } else
                return null;
        }

        public Class<T> type() {
            return this.resourceType;
        }

        public String id() {
            return this.resourceId;
        }

        public <Y extends ResourceDeclaration> InfrastructureImpl dependsOn(Class<Y> resourceType, String resourceId) {
            return new DependencyImpl(this).dependsOn(new ResourceReferenceImpl<Y>(resourceType, resourceId));
        }

        @Override
        public String toString() {
            return resourceType.getSimpleName() + ":" + resourceId;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (o == this) return true;
            if (!(o instanceof ResourceReferenceImpl)) return false;
            ResourceReference oRef = (ResourceReference) o;
            return (oRef.type().equals(this.type()) && oRef.id().equals(this.id()));
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }
    }

    public class DependencyImpl implements Dependency {

        private final ResourceReferenceImpl requirer;
        private ResourceReferenceImpl provider;

        DependencyImpl(ResourceReferenceImpl from) {
            this.requirer = from;
        }

        public InfrastructureImpl dependsOn(ResourceReferenceImpl to) {
            this.provider = to;
            return InfrastructureImpl.this.dependency(this);
        }

        public ResourceReference requirer() {
            return this.requirer;
        }

        public ResourceReference provider() {
            return this.provider;
        }

        @Override
        public String toString() {
            return requirer + " -> " + provider;
        }
    }

}
