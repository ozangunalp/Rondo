package fr.liglab.adele.rondo.infra;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 5:26 PM
 */
public class Infrastructure {

    private Map<Class, LinkedHashMap<String, AbstractResource>> resources = new HashMap<Class, LinkedHashMap<String, AbstractResource>>();
    private LinkedList<Dependency> dependencies = new LinkedList<Dependency>();
    private List<ResourceReference> exports = new ArrayList<ResourceReference>();

    // Static

    public static Infrastructure infrastructure() {
        return new Infrastructure();
    }

    // Builder methods

    public Infrastructure dependency(Dependency dependency) {
        this.dependencies.add(dependency);
        return this;
    }

    public <Y extends AbstractResource<Y>> Infrastructure dependsOn(Class<Y> resourceType, String resourceName) {
        Dependency d = this.dependencies.getLast();
        ResourceReference res = d.provider;
        return res.dependsOn(resourceType, resourceName);
    }

    public <T extends AbstractResource<T>> Infrastructure exports(ResourceReference<T>... resourceReferences) {
        for (ResourceReference ref : resourceReferences) {
            this.exports.add(ref);
        }
        return this;
    }

    public <T extends AbstractResource<T>> Infrastructure resource(T resource) {
        LinkedHashMap<String, AbstractResource> map = resources.get(resource.getClass());
        if (map == null) {
            map = new LinkedHashMap<String, AbstractResource>();
            resources.put(resource.getClass(), map);
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
    public <T extends AbstractResource<T>> ResourceReference<T> resource(Class<T> type, String name) {
        return new ResourceReference(type, name);
    }

    // Getters

    @SuppressWarnings("unchecked")
    public <T extends AbstractResource<T>> T getResource(Class<T> resourceType, String resourceName) {
        T resource = null;
        LinkedHashMap<String, AbstractResource> res = resources.get(resourceType);
        if (res != null) {
            AbstractResource<T> entity = res.get(resourceName);
            resource = entity.self();
        }
        return resource;
    }


    @SuppressWarnings("unchecked")
    public <T extends AbstractResource<T>> Map<String, T> getResources(Class<T> resourceType) {
        Map<String, T> res = new HashMap<String, T>();
        LinkedHashMap<String, AbstractResource> resType = resources.get(resourceType);
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
                references.add(new ResourceReference(clazz, key));
            }
        }
        return references;
    }

    public <T extends AbstractResource<T>> T getResource(ResourceReference<T> reference) {
        return getResource(reference.type(), reference.name());
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    // Inner classes

    public class ResourceReference<T extends AbstractResource<T>> {

        private final Class<T> resourceType;
        private final String resourceName;

        public ResourceReference(Class<T> resourceType, String resourceName) {
            this.resourceType = resourceType;
            this.resourceName = resourceName;
        }

        public ResourceReference<T> adapt(Class<T> clazz) {
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

        public <Y extends AbstractResource<Y>> Infrastructure dependsOn(Class<Y> resourceType, String resourceName) {
            return new Dependency(this).dependsOn(new ResourceReference<Y>(resourceType, resourceName));
        }

        @Override
        public String toString() {
            return resourceType.getSimpleName() + ":" + resourceName;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (o == this) return true;
            if (!(o instanceof ResourceReference)) return false;
            ResourceReference oRef = (ResourceReference) o;
            if (oRef.type().equals(this.type()) && oRef.name().equals(this.name())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public class Dependency {

        private final ResourceReference requirer;
        private ResourceReference provider;

        Dependency(ResourceReference from) {
            this.requirer = from;
        }

        public Infrastructure dependsOn(ResourceReference to) {
            this.provider = to;
            return Infrastructure.this.dependency(this);
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
