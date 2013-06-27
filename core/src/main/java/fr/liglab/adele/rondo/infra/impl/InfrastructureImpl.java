package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.*;

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
    private List<Condition> m_preConditions = new ArrayList<Condition>();

    /**
     *
     */
    private LinkedHashMap<String, ContainedInfrastructureImpl> m_containedInfrastructures = new LinkedHashMap<String, ContainedInfrastructureImpl>();

    /**
     *
     */
    private Map<Class, LinkedHashMap<String, ResourceReferenceImpl>> resourceReferences = new HashMap<Class, LinkedHashMap<String, ResourceReferenceImpl>>();

    /**
     *
     */
    private Map<ResourceReference, ResourceDeclaration> resourceDeclarationMap = new HashMap<ResourceReference, ResourceDeclaration>();

    // Static Builder Methods
    /////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param name
     * @return
     */
    public static InfrastructureImpl infrastructure(String name) {
        return new InfrastructureImpl(name);
    }

    /**
     *
     * @return
     */
    public static InfrastructureImpl infrastructure() {
        return new InfrastructureImpl(null);
    }

    /**
     *
     * @param declaration
     * @return
     */
    public static ConditionImpl condition(ResourceDeclaration declaration){
        return new ConditionImpl(declaration);
    }

    /**
     *
     * @param name
     */
    public InfrastructureImpl(String name) {
        this.name = name;
    }


    public InfrastructureImpl validWhen(Condition... conditions) {
        if(conditions!=null){
            Collections.addAll(m_preConditions, conditions);
        }
        return this;
    }

    /**
     *
     * @param resource
     * @param <T>
     * @return
     */
    public <T extends AbstractResourceDeclaration<T>> InfrastructureImpl resource(T resource) {
        // Get the first interface of the resource, it must be the type
        Class interfaze = null;
        Class<?>[] interfaces = resource.getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            if(ResourceDeclaration.class.isAssignableFrom(anInterface)){
                interfaze = anInterface;
            }
        }
        if(interfaze==null) {
            //TODO throw some exception
            return this;
        }
        LinkedHashMap<String, ResourceReferenceImpl> map = resourceReferences.get(interfaze);
        if (map == null) {
            map = new LinkedHashMap<String, ResourceReferenceImpl>();
            resourceReferences.put(interfaze, map);
        }
        String resourceId = resource.id();
        if (map.containsKey(resourceId)) {
            // TODO should throw some exception
            return this;
        } else {
            ResourceReferenceImpl<T> reference = new ResourceReferenceImpl<T>(interfaze, resource.id());
            map.put(resource.id(), reference);
            resourceDeclarationMap.put(reference,resource);
        }
        return this;
    }

    /**
     *
     * @param resourceType
     * @param resourceId
     * @param <T>
     * @return
     */
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
            resourceReferences.get(resourceType).put(resourceId, newResourceRef);
        }
        return resourceReference;
    }

    /**
     *
     * @param subInfrastructureName
     * @param subInfrastructure
     * @return
     */
    public InfrastructureImpl with(String subInfrastructureName, Infrastructure subInfrastructure) {
        return new ContainedInfrastructureImpl().then(subInfrastructureName,subInfrastructure);
    }

    /**
     *
     * @param condition
     * @return
     */
    public ContainedInfrastructureImpl when(Condition... condition){
        return new ContainedInfrastructureImpl(condition);
    }

    /**
     *
     * @param name
     */
    public void nameIfUnnamed(String name){
        if(this.name==null){
            this.name = name;
        }
    }

    // Infrastructure Interface Methods
    /////////////////////////////////////////////////////////////////////////////

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Condition> getPreConditions() {
        ArrayList<Condition> preConditions = new ArrayList<Condition>();
        preConditions.addAll(m_preConditions);
        return preConditions;
    }

    @Override
    public List<Dependency> getDependencies() {
        ArrayList<Dependency> allDependencies = new ArrayList<Dependency>();
        for (LinkedHashMap<String, ResourceReferenceImpl> referenceLinkedHashMap : resourceReferences.values()) {
            for (ResourceReferenceImpl resourceReference : referenceLinkedHashMap.values()) {
                Collections.addAll(allDependencies,resourceReference.dependencies());
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
        ResourceDeclaration declaration = resourceDeclarationMap.get(reference);
        if(declaration!=null){
            return (T) declaration;
        }
        return null;
    }

    @Override
    public List<ContainedInfrastructure> getContainedInfrastructures() {
        ArrayList<ContainedInfrastructure> infrastructures = new ArrayList<ContainedInfrastructure>();
        infrastructures.addAll(m_containedInfrastructures.values());
        return infrastructures;
    }

    // Inner Classes ResourceReference and Dependency
    /////////////////////////////////////////////////////////////////////////////

    public static class ConditionImpl implements Condition {

        ResourceDeclaration m_declaration;

        boolean m_value;

        public ConditionImpl(ResourceDeclaration declaration){
            this.m_declaration = declaration;
            this.m_value = true;
        }

        @Override
        public ResourceDeclaration declaration() {
            return m_declaration;
        }

        @Override
        public boolean value() {
            return m_value;
        }

        public ConditionImpl isTrue(){
            this.m_value = true;
            return this;
        }

        public ConditionImpl isFalse(){
            this.m_value = false;
            return this;
        }

        @Override
        public String toString() {
            return declaration().toString() + ":" + value();
        }
    }


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
        public ResourceReference adapt(Class clazz) {
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
        public Dependency[] dependencies() {
            ArrayList<Dependency> dependencies = new ArrayList<Dependency>();
            dependencies.addAll(this.requiredDependency);
            return dependencies.toArray(new Dependency[dependencies.size()]);
        }

        @Override
        public Dependency[] providings() {
            ArrayList<Dependency> providings = new ArrayList<Dependency>();
            providings.addAll(this.providedDependency);
            return providings.toArray(new Dependency[providings.size()]);
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

        @Override
        public ResourceReference requirer() {
            return this.requirer;
        }

        @Override
        public ResourceReference provider() {
            return this.provider;
        }

        @Override
        public String toString() {
            return requirer + " -> " + provider;
        }
    }

    public class ContainedInfrastructureImpl implements ContainedInfrastructure {

        String m_name;

        Infrastructure m_containedInfrastructure;

        Set<Condition> m_conditions;

        public ContainedInfrastructureImpl(Condition... conditions) {
            this.m_conditions = new LinkedHashSet<Condition>();
            if(conditions!=null){
                Collections.addAll(m_conditions, conditions);
            }
        }

        @Override
        public String getName(){
            return m_name;
        }

        @Override
        public Infrastructure getInfrastructure() {
            return m_containedInfrastructure;
        }

        @Override
        public Set<Condition> getConditions() {
            return m_conditions;
        }

        public InfrastructureImpl then(String name, Infrastructure containedInfrastructure){
            m_containedInfrastructure = containedInfrastructure;
            this.m_name = name;
            InfrastructureImpl.this.m_containedInfrastructures.put(name, this);
            return InfrastructureImpl.this;
        }

        @Override
        public String toString() {
            return this.m_name + " : " + this.m_conditions + " - " + this.m_containedInfrastructure.getResourceReferences();
        }
    }
}
