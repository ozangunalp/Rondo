package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Dependency;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import fr.liglab.adele.rondo.infra.model.ResourceReference;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/23/13
 * Time: 8:47 PM
 */
public class InfrastructureImpl implements Infrastructure {

    /**
     *
     */
    private String name;

    /**
     *
     */
    //private Map<Class, LinkedHashMap<String, AbstractResourceDeclaration>> resources = new HashMap<Class, LinkedHashMap<String, AbstractResourceDeclaration>>();

    /**
     *
     */
    private Map<Class, LinkedHashMap<String, ResourceReferenceImpl>> resourceReferences = new HashMap<Class, LinkedHashMap<String, ResourceReferenceImpl>>();

    /**
     *
     */
    private Map<ResourceReference, AbstractResourceDeclaration> resourceDeclarationMap = new HashMap<ResourceReference, AbstractResourceDeclaration>();

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

    public <T extends AbstractResourceDeclaration<T>> InfrastructureImpl resource(T resource) {
        // Get the first interface of the resource, it must be the type
        Class interFace = null;
        Class<?>[] interfaces = resource.getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            if(ResourceDeclaration.class.isAssignableFrom(anInterface)){
                interFace = anInterface;
            }
        }
        if(interFace==null) {
            //TODO throw some exception
            return this;
        }
        LinkedHashMap<String, ResourceReferenceImpl> map = resourceReferences.get(interFace);
        if (map == null) {
            map = new LinkedHashMap<String, ResourceReferenceImpl>();
            resourceReferences.put(interFace, map);
        }
        String resourceId = resource.id();
        if (map.containsKey(resourceId)) {
            // TODO should throw some exception
            return this;
        } else {
            ResourceReferenceImpl<T> reference = new ResourceReferenceImpl<T>(interFace, resource.id());
            map.put(resource.id(), reference);
            resourceDeclarationMap.put(reference,resource);
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ResourceDeclaration> ResourceReferenceImpl<T> resource(Class<T> resourceType, String resourceId) {
        ResourceReferenceImpl<T> resourceReference = null;
        if(resourceReferences.containsKey(resourceType)){
            LinkedHashMap<String, ResourceReferenceImpl> typedResourceRefs = resourceReferences.get(resourceType);
            resourceReference = typedResourceRefs.get(resourceId);
        } else {
            LinkedHashMap<String, ResourceReferenceImpl> linkedHashMap = new LinkedHashMap<String, ResourceReferenceImpl>();
            resourceReferences.put(resourceType,linkedHashMap);
        }
        if(resourceReference==null){
            ResourceReferenceImpl<T> newResourceRef = new ResourceReferenceImpl<T>(resourceType, resourceId);
            resourceReferences.get(resourceType).put(resourceId,newResourceRef);
        }
        return resourceReference;
    }

    public void nameIfUnnamed(String name){
        if(this.name==null){
            this.name = name;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Dependency> getDependencies() {
        ArrayList<Dependency> allDependencies = new ArrayList<Dependency>();
        for (LinkedHashMap<String, ResourceReferenceImpl> referenceLinkedHashMap : resourceReferences.values()) {
            for (ResourceReferenceImpl resourceReference : referenceLinkedHashMap.values()) {
                allDependencies.addAll(resourceReference.dependencies());
            }
        }
        return allDependencies;
    }

    @Override
    public List<ResourceReference> getResourceReferences() {
        List<ResourceReference> references = new ArrayList<ResourceReference>();
        for (Class type : resourceReferences.keySet()) {
            LinkedHashMap<String, ResourceReferenceImpl> linkedHashMap = resourceReferences.get(type);
            references.addAll(linkedHashMap.values());
        }
        return references;
    }

    @Override
    public <T extends ResourceDeclaration> Map<String, ResourceReference<T>> getResourceReferences(Class<T> resourceType) {
        Map<String, ResourceReference<T>> resourceMap = new HashMap<String, ResourceReference<T>>();
        LinkedHashMap<String, ResourceReferenceImpl> referenceLinkedHashMap = resourceReferences.get(resourceType);
        if (referenceLinkedHashMap != null) {
            for (String resourceId : referenceLinkedHashMap.keySet()) {
                resourceMap.put(resourceId, referenceLinkedHashMap.get(resourceId).adapt(resourceType));
            }
            //resourceMap.putAll(referenceLinkedHashMap);
        }
        return resourceMap;

    }

    @Override
    public <T extends ResourceDeclaration> ResourceReference<T> getResourceReference(Class<T> resourceType, String resourceId) {
        ResourceReference<T> resourceReference = null;
        if(resourceReferences.containsKey(resourceType)){
            LinkedHashMap<String, ResourceReferenceImpl> typedResourceRefs = resourceReferences.get(resourceType);
            resourceReference = typedResourceRefs.get(resourceId);
        } else {
            LinkedHashMap<String, ResourceReferenceImpl> linkedHashMap = new LinkedHashMap<String, ResourceReferenceImpl>();
            resourceReferences.put(resourceType,linkedHashMap);
        }
        if(resourceReference==null){
            ResourceReferenceImpl<T> newResourceRef = new ResourceReferenceImpl<T>(resourceType, resourceId);
            resourceReferences.get(resourceType).put(resourceId,newResourceRef);
        }
        return resourceReference;
    }

    @Override
    public List<ResourceDeclaration> getResources() {
        List<ResourceDeclaration> allResources = new ArrayList<ResourceDeclaration>();
        allResources.addAll(resourceDeclarationMap.values());
        return allResources;
    }

    @Override
    public <T extends ResourceDeclaration> T getResource(ResourceReference<T> reference) {
        AbstractResourceDeclaration declaration = resourceDeclarationMap.get(reference);
        if(declaration!=null){
            return (T) declaration.self();
        }
        return null;
    }

    // Inner Classes ResourceReference and Dependency
    /////////////////////////////////////////////////////////////////////////////

    public class ResourceReferenceImpl<T extends ResourceDeclaration> implements ResourceReference {

        private final Class<T> resourceType;

        private final String resourceId;

        private final List<Dependency> requiredDependency;

        private final List<Dependency> providedDependency;

        public ResourceReferenceImpl(Class<T> resourceType, String resourceId) {
            this.resourceType = resourceType;
            this.resourceId = resourceId;
            this.requiredDependency = new ArrayList<Dependency>();
            this.providedDependency = new ArrayList<Dependency>();
        }

        @Override
        public ResourceReference<T> adapt(Class clazz) {
            System.out.println(resourceType.getName() + clazz.getName());
            if (clazz.equals(resourceType)) {
                return this;
            } else
                return null;
        }

        @Override
        public Class<T> type() {
            return this.resourceType;
        }

        @Override
        public String id() {
            return this.resourceId;
        }

        @Override
        public List<Dependency> dependencies() {
            ArrayList<Dependency> dependencies = new ArrayList<Dependency>();
            dependencies.addAll(this.requiredDependency);
            return dependencies;
        }

        @Override
        public List<Dependency> providings() {
            ArrayList<Dependency> providings = new ArrayList<Dependency>();
            providings.addAll(this.providedDependency);
            return providings;
        }

        public void addRequired(Dependency dependency){
            requiredDependency.add(dependency);
        }

        public void addProvided(Dependency dependency){
            providedDependency.add(dependency);
        }

        public <Y extends ResourceDeclaration> InfrastructureImpl dependsOn(Class<Y> resourceType, String... resourceIds) {
            for (String resourceId : resourceIds) {
                DependencyImpl dependency = new DependencyImpl(this);
                ResourceReferenceImpl<Y> provider = InfrastructureImpl.this.resource(resourceType, resourceId);
                provider.addProvided(dependency);
                dependency.dependsOn(provider);
            }
            return InfrastructureImpl.this;
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

        private final ResourceReference requirer;
        private ResourceReference provider;

        DependencyImpl(ResourceReferenceImpl from) {
            from.addRequired(this);
            this.requirer = from;
        }

        public InfrastructureImpl dependsOn(ResourceReference to) {
            this.provider = to;
            return InfrastructureImpl.this;
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
