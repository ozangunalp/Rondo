package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Dependency;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import fr.liglab.adele.rondo.infra.model.Resource;
import fr.liglab.adele.rondo.infra.model.ResourceReference;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 5:26 PM
 */
public class InfrastructureImpl implements Infrastructure {

    private Map<Class, LinkedHashMap<String, AbstractResource>> resources = new HashMap<Class, LinkedHashMap<String, AbstractResource>>();
    private LinkedList<DependencyImpl> dependencies = new LinkedList<DependencyImpl>();
    private List<ResourceReference> exports = new ArrayList<ResourceReference>();

    // Static

    public static InfrastructureImpl infrastructure() {
        return new InfrastructureImpl();
    }

    // Builder methods

    public InfrastructureImpl dependency(DependencyImpl dependency) {
        this.dependencies.add(dependency);
        return this;
    }

    public InfrastructureImpl dependsOn(Class resourceType, String resourceName) {
        DependencyImpl d = this.dependencies.getLast();
        ResourceReferenceImpl res = d.provider;
        return res.dependsOn(resourceType, resourceName);
    }

    public InfrastructureImpl exports(ResourceReferenceImpl... resourceReferences) {
        this.exports.addAll(Arrays.asList(resourceReferences));
        return this;
    }

    public <T extends AbstractResource<T>> InfrastructureImpl resource(T resource) {
        // Get the first interface of the resource, it must be the type
        Class interFace = null;
        Class<?>[] interfaces = resource.getClass().getInterfaces();
        if (interfaces.length > 0) {
            interFace = interfaces[0];
        } else {
            //TODO throw some exception
        }
        LinkedHashMap<String, AbstractResource> map = resources.get(interFace);
        if (map == null) {
            map = new LinkedHashMap<String, AbstractResource>();
            resources.put(interFace, map);
        }
        String resourceName = resource.name();
        if (map.containsKey(resourceName)) {
            // TODO should throw some exception
        } else {
            map.put(resource.name(), resource);
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Resource> ResourceReferenceImpl<T> resource(Class<T> type, String name) {
        return new ResourceReferenceImpl(type, name);
    }

    // Getters

    @SuppressWarnings("unchecked")
    public <T extends Resource> T getResource(Class<T> resourceType, String resourceName) {
        T resource = null;
        LinkedHashMap<String, AbstractResource> res = this.resources.get(resourceType);
        if (res != null) {
            Resource entity = res.get(resourceName);
            resource = (T) entity;
        }
        return resource;
    }

    @SuppressWarnings("unchecked")
    public List<Resource> getResources() {
        List<Resource> allResources = new ArrayList<Resource>();
        for (LinkedHashMap<String, AbstractResource> resource : this.resources.values()) {
            allResources.addAll(resource.values());
        }
        return allResources;
    }

    @SuppressWarnings("unchecked")
    public <T extends Resource> Map<String, T> getResources(Class<T> resourceType) {
        Map<String, T> res = new HashMap<String, T>();
        LinkedHashMap<String, AbstractResource> resType = this.resources.get(resourceType);
        if (resType != null) {
            for (Map.Entry<String, AbstractResource> e : resType.entrySet()) {
                res.put(e.getKey(), (T) e.getValue().self());
            }
        }
        return Collections.unmodifiableMap(res);
    }

    @SuppressWarnings("unchecked")
    public List<ResourceReference> getResourceReferences() {
        List<ResourceReference> references = new ArrayList<ResourceReference>();
        for (Map.Entry<Class, LinkedHashMap<String, AbstractResource>> resource : resources.entrySet()) {
            Class clazz = resource.getKey();
            for (String key : resource.getValue().keySet()) {
                references.add(new ResourceReferenceImpl(clazz, key));
            }
        }
        return references;
    }

    public <T extends Resource> T getResource(ResourceReference<T> reference) {
        return this.getResource(reference.type(), reference.name());
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

    public class ResourceReferenceImpl<T extends Resource> implements ResourceReference {

        private final Class<T> resourceType;
        private final String resourceName;


        public ResourceReferenceImpl(Class<T> resourceType, String resourceName) {
            this.resourceType = resourceType;
            this.resourceName = resourceName;
        }

        @Override
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

        public String name() {
            return this.resourceName;
        }

        public <Y extends Resource> InfrastructureImpl dependsOn(Class<Y> resourceType, String resourceName) {
            return new DependencyImpl(this).dependsOn(new ResourceReferenceImpl<Y>(resourceType, resourceName));
        }

        @Override
        public String toString() {
            return resourceType.getSimpleName() + ":" + resourceName;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (o == this) return true;
            if (!(o instanceof ResourceReferenceImpl)) return false;
            ResourceReference oRef = (ResourceReference) o;
            return (oRef.type().equals(this.type()) && oRef.name().equals(this.name()));
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
