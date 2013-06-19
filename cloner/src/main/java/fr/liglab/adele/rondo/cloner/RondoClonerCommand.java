package fr.liglab.adele.rondo.cloner;

import fr.liglab.adele.rondo.infra.impl.*;
import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.ResourceReferenceImpl;
import fr.liglab.adele.rondo.infra.model.Dependency;
import fr.liglab.adele.rondo.infra.model.Instance;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import fr.liglab.adele.rondo.infra.model.Service;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.apache.felix.service.command.Descriptor;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;
import org.osgi.framework.wiring.FrameworkWiring;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.ServiceImpl.service;
import static fr.liglab.adele.rondo.infra.impl.ComponentImpl.component;
import static fr.liglab.adele.rondo.infra.impl.InstanceImpl.instance;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/18/13
 * Time: 10:37 PM
 */
@Component(immediate = true)
@Instantiate
@Provides(specifications = RondoClonerCommand.class)
public class RondoClonerCommand {

    /**
     *
     */
    public static final String RANGE_SPLITTER = "\\.\\.";

    /**
     * Defines the command scope (ipojo).
     */
    @ServiceProperty(name = "osgi.command.scope", value = "rondo")
    String m_scope;

    /**
     * Defines the functions (commands).
     */
    @ServiceProperty(name = "osgi.command.function", value = "{}")
    String[] m_function = new String[]{"clone"};


    @Requires(optional = false)
    EverestService m_everest;

    /**
     *
     */
    BundleContext m_context;


    Pattern singleId = Pattern.compile("^\\d+$");

    Pattern rangeIds = Pattern.compile("^(\\d+)"+RANGE_SPLITTER+"(\\d+)$");

    public RondoClonerCommand(BundleContext m_context) {
        this.m_context = m_context;
    }

    @Descriptor("")
    public void clone(@Descriptor("bundle ids") String... ids) {
        Set<Long> initialBundleIds = new HashSet<Long>();
        if(ids.length==0){
            Bundle[] bundles = m_context.getBundles();
            for (Bundle bundle : bundles) {
                initialBundleIds.add(bundle.getBundleId());
            }
        }
        for (String bundleId : ids) {
            System.out.println(bundleId);
            Matcher rangeMatcher = rangeIds.matcher(bundleId);
            if(rangeMatcher.matches()){
                String[] split = bundleId.split(RANGE_SPLITTER);
                if(split.length>1){
                    List<String> strings = Arrays.asList(split);
                    long lowerBound = Long.parseLong(strings.get(0));
                    long upperBound = Long.parseLong(strings.get(strings.size()-1));
                    for(long i = lowerBound;i<=upperBound;i++){
                        initialBundleIds.add(i);
                    }
                }
            } else {
                Matcher singleMatcher = singleId.matcher(bundleId);
                if(singleMatcher.matches()){
                    long l = Long.parseLong(bundleId);
                    initialBundleIds.add(l);
                }
            }
        }
        System.out.println("handling these bundles"+ initialBundleIds);
        InfrastructureImpl infrastructure = new InfrastructureImpl("infra");
        HashSet<Long> bundles = new HashSet<Long>();
        Collection<Long> dependencies = new ArrayList<Long>();
        bundles.addAll(initialBundleIds);
        dependencies.addAll(initialBundleIds);

        while(!dependencies.isEmpty()){
            dependencies = handleBundles(bundles, infrastructure);
            bundles.addAll(dependencies);
            System.out.println("Dependencies " + dependencies);
            System.out.println("Bundles "+bundles);
        }

        System.out.println("Bundles "+bundles);
        System.out.println("FINISHED with "+infrastructure.getResources().size()+" resources and "+infrastructure.getDependencies().size()+" depedencies.");

        FrameworkWiring frameworkWiring = m_context.getBundle(0).adapt(FrameworkWiring.class);
        Set<Bundle> bbs = new HashSet<Bundle>();
        for (Long initialBundleId : initialBundleIds) {
            Bundle bundle = m_context.getBundle(initialBundleId);
            bbs.add(bundle);
        }
        System.out.println("Dependency Closure for "+bbs);
        Collection<Bundle> dependencyClosure = frameworkWiring.getDependencyClosure(bbs);
        for (Bundle bundle : dependencyClosure) {
            System.out.println(bundle);
        }
//        for (ResourceDeclaration resourceDeclaration : infrastructure.getResources()) {
//            System.out.println(resourceDeclaration);
//        }

        for (Dependency dependency : infrastructure.getDependencies()) {
            System.out.println(dependency.toString());
        }

    }

    public Collection<Long> handleBundles(Collection<Long> bundleIds,InfrastructureImpl infrastructure) {

        Set<Long> nextBundleIds = new HashSet<Long>();

        //Resource packages = this.get("/osgi/packages");

        Resource factories = this.get("/ipojo/factory");

        for (Long bundleId : bundleIds) {

            Resource bundleResource = this.get("/osgi/bundles/" + bundleId);
            BundleImpl bundle = bundleResource(bundleResource);
            System.out.println("Bundle: "+bundle.id());
            ResourceReferenceImpl bundleResourceReference = infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id());
            if(infrastructure.getResource(bundleResourceReference)==null){
                // infrastructure doesn't contain bundle

                // add bundle
                infrastructure.resource(bundle);
                // look at its capabilities
                //Resource capabilities = bundleResource.getResource(bundleResource.getPath() + "/capabilities");
                Resource capabilities = getChild(bundleResource, "capabilities");
                if(capabilities!=null){
                    for (Resource capability : capabilities.getResources()) {
                        Relation relation = getRelation(capability, "package");
                        if(relation!=null){
                            Resource pkg = this.get(relation.getHref().toString());
                            PackageImpl aPackage = packageResource(pkg);
                            //System.out.println("Package: "+aPackage.toString());
                            // add package
                            infrastructure.resource(aPackage);
                            // add dependency to the bundle
                            //System.out.println("Adding package: "+ aPackage.id()+" requires: "+bundle.id());
                            infrastructure.resource(fr.liglab.adele.rondo.infra.model.Package.class, aPackage.id())
                                    .dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id());
                        }
                    }
                }
                // look at its requirements
                //Resource requirements = bundleResource.getResource(bundleResource.getPath() + "/requirements");
                Resource requirements = getChild(bundleResource, "requirements");
                if(requirements!=null){
                    for (Resource requirement : requirements.getResources()) {
                        for (Relation relation : requirement.getRelations()) {
                            if(relation.getName().startsWith("wire")){
                                Resource wire = this.get(relation.getHref().toString());
                                Relation wireRel = getRelation(wire, "capability");
                                Resource capability = this.get(wireRel.getHref().toString());
                                Relation pkgRel = getRelation(capability, "package");
                                if(pkgRel!=null){
                                    Resource pkg = this.get(pkgRel.getHref().toString());
                                    PackageImpl aPackage = packageResource(pkg);
                                    //System.out.println("Package: "+aPackage.toString());
                                    // add package
                                    infrastructure.resource(aPackage);
                                    // add dependency to the bundle
                                    //System.out.println("Adding: "+bundle.id()+" requires: "+aPackage.id());
                                    infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id())
                                            .dependsOn(fr.liglab.adele.rondo.infra.model.Package.class, aPackage.id());

                                    Relation providerBundleRelation = getRelation(pkg, "provider-bundle");
                                    if(providerBundleRelation!=null){
                                        Resource providerBundle = this.get(providerBundleRelation.getHref().toString());
                                        //BundleImpl providerBundleDeclaration = bundleResource(providerBundle);
                                        Long nextBundleId = providerBundle.getMetadata().get("bundle-id", Long.class);
                                        //System.out.println("PACKAGE PROVIDER: "+nextBundleId);
                                        if(!bundleIds.contains(nextBundleId)){
                                            nextBundleIds.add(nextBundleId);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // look at its services
                //Resource services = bundleResource.getResource(bundleResource.getPath() + "/services");
                Resource services = getChild(bundleResource, "services");
                Resource registeredServices = getChild(services, "registered");
//                System.out.println("REGISTERED");
                if(registeredServices!=null){
                    for (Relation regService : registeredServices.getRelations()) {
                        Resource serviceResource = this.get(regService.getHref().toString());
                        ServiceImpl service = serviceResource(serviceResource);
                        //System.out.println("Service: "+service.id());
                        // add service
                        infrastructure.resource(service);
                        // add dependency to the bundle
                        //System.out.println("\t Adding: "+service.id()+ " requires: "+bundle.id());
                        infrastructure.resource(Service.class, service.id()).
                                dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id());
                    }

                }
                Resource usedServices = getChild(services, "uses");
//                System.out.println("USES");
                if(usedServices!=null){
                    for (Relation usedService : usedServices.getRelations()) {
                        Resource serviceResource = this.get(usedService.getHref().toString());
                        ServiceImpl service = serviceResource(serviceResource);
                        //System.out.println("Service: "+service.id());
                        // add service
                        infrastructure.resource(service);
                        // add dependency to the bundle
                        //System.out.println("\t Adding: "+bundle.id()+ " requires: "+service.id());
                        infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id()).
                                dependsOn(Service.class, service.id());

//                      //don't follow services
//                        Relation fromBundleRelation = this.getRelation(serviceResource, "from-bundle");
//                        if(fromBundleRelation!=null){
//                            Resource fromBundle = this.get(fromBundleRelation.getHref().toString());
//                            Long nextBundleId = fromBundle.getMetadata().get("bundle-id", Long.class);
//                            System.out.println("SERVICE PROVIDER: "+nextBundleId);
//                            if(!bundleIds.contains(nextBundleId)){
//                                nextBundleIds.add(nextBundleId);
//                            }
//                        }

                    }
                }

                //look at the components
                for (Resource factoryByVersion : factories.getResources()) {
                    for (Resource factory : factoryByVersion.getResources()) {
                        Relation declaringBundleRel = getRelation(factory, "bundle");
                        if(declaringBundleRel!=null){
                            Resource declaringBundle = this.get(declaringBundleRel.getHref().toString());
                            if(bundleResource.equals(declaringBundle)){
                                ComponentImpl component = componentResource(factory);
                                // add component
                                //System.out.println("COMPONENT "+component);
                                infrastructure.resource(component);
                                // add dependency to bundle
                                infrastructure.resource(fr.liglab.adele.rondo.infra.model.Component.class, component.id())
                                        .dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id());

                                // add instances
                                for (Relation relation : factory.getRelations()) {
                                    if(relation.getName().startsWith("instance")){
                                        Resource instanceResource = this.get(relation.getHref().toString());
                                        InstanceImpl instance = instanceResource(instanceResource);

                                        //System.out.println("INSTANCE "+instance);
                                        // add instance
                                        infrastructure.resource(instance);
                                        // add dependency to component
                                        infrastructure.resource(Instance.class,instance.id())
                                                .dependsOn(fr.liglab.adele.rondo.infra.model.Component.class,component.id());

                                        // find provided services
                                        Relation providings = getRelation(instanceResource, "providings");
                                        if(providings!=null){
                                            Resource providing = this.get(providings.getHref().toString());
                                            for (Relation providingRel : providing.getRelations()) {
                                                Resource providingResource = this.get(providingRel.getHref().toString());
                                                Relation service = getRelation(providingResource, "service");
                                                if(service!=null){
                                                    Resource serviceResource = this.get(service.getHref().toString());
                                                    ServiceImpl providedService = serviceResource(serviceResource);
                                                    //System.out.println("\tPROVIDED SERVICE "+providedService);
                                                    // add dependency to instance
                                                    infrastructure.resource(Service.class,providedService.id())
                                                            .dependsOn(Instance.class,instance.id());
                                                }
                                            }
                                        }

                                        // find depending services
                                        Relation dependencies = getRelation(instanceResource, "dependencies");
                                        if(dependencies!=null){
                                            Resource dependency = this.get(dependencies.getHref().toString());
                                            for (Relation dependencyRel : dependency.getRelations()) {
                                                Resource dependencyResource = this.get(dependencyRel.getHref().toString());
                                                for (Relation dependencyRelation : dependencyResource.getRelations()) {
                                                    if(dependencyRelation.getName().startsWith("usedService")){
                                                        Resource serviceResource = this.get(dependencyRelation.getHref().toString());
                                                        if(serviceResource!=null){
                                                            ServiceImpl requiredService = serviceResource(serviceResource);
                                                            //System.out.println("\tREQUIRED SERVICE "+requiredService);
                                                            // add to infrastructure
                                                            infrastructure.resource(requiredService);
                                                            // add dependency to service
                                                            infrastructure
                                                                    .resource(Instance.class,instance.id())
                                                                    .dependsOn(Service.class,requiredService.id());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                }


            }

        }
        return nextBundleIds;
    }

    public static InstanceImpl instanceResource(Resource instanceResource) {
        InstanceImpl instance = null;
        ResourceMetadata metadata = instanceResource.getMetadata();
        String name = metadata.get("name", String.class);
        String factoryName = metadata.get("factory.name", String.class);
        String factoryVersion = metadata.get("factory.version", String.class);
        String state = metadata.get("state", String.class);
        instance = instance(name+"-"+factoryName);
        instance.name(name);
        instance.factory(factoryName);
        if(factoryVersion!=null){
            instance.factoryVersion(factoryVersion);
        }
        instance.state(state);
        Map configuration = metadata.get("configuration", Map.class);
        for (Object o : configuration.keySet()) {
            String key = (String) o;
            instance.with(key).setto(configuration.get(key));
        }
        return instance;
    }

    public static ComponentImpl componentResource(Resource factoryResource) {
        ComponentImpl component = null;
        ResourceMetadata metadata = factoryResource.getMetadata();
        String name = metadata.get("name", String.class);
        String version = metadata.get("version", String.class);
        String state = metadata.get("state", String.class);
        component = component(name+"-"+version);
        component.name(name);
        component.version(name);
        component.state(state);
        return component;

    }

    public static BundleImpl bundleResource(Resource bundleResource){
        BundleImpl bundle= null;
        ResourceMetadata metadata = bundleResource.getMetadata();
        bundle = bundle(
                Long.toString(metadata.get("bundle-id", Long.class)) + "-" +
                        metadata.get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class));
        bundle.symbolicName(metadata.get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class));
        bundle.version(metadata.get(Constants.BUNDLE_VERSION_ATTRIBUTE, Version.class).toString());
        bundle.source(metadata.get("bundle-location", String.class));
        bundle.state(metadata.get("bundle-state", String.class));
        // add headers
        Resource headers = getChild(bundleResource, "headers");
        //bundle.with("headers").setto(headers.getMetadata());

        return bundle;
    }

    public static PackageImpl packageResource(Resource packageResource){
        ResourceMetadata metadata = packageResource.getMetadata();
        String id = packageResource.getPath().getLast();
        PackageImpl pkg = new PackageImpl(id);
        pkg.name(metadata.get("osgi.wiring.package",String.class));
        pkg.version(metadata.get("version",Version.class).toString());
        return pkg;
    }

    public static ServiceImpl serviceResource(Resource serviceResource){
        ResourceMetadata metadata = serviceResource.getMetadata();
        ServiceImpl service;
        String pid = metadata.get(Constants.SERVICE_PID, String.class);
        if(pid!=null){
            service = service(pid);
        } else {
            String serviceId = "service-"+metadata.get(Constants.SERVICE_ID,Long.class);
            service = service(serviceId);
        }
        //properties
//        for (String key : metadata.keySet()) {
//            Object o = metadata.get(key);
//            service.with(key).setto(o);
//        }
        String[] objectClass = (String []) metadata.get(Constants.OBJECTCLASS);
        service.objectClass(Arrays.asList(objectClass));
        return  service;
    }

    public Resource get(String path){
        Resource resource = null;
        try {
            resource = m_everest.process(new DefaultRequest(Action.READ, Path.from(path), null));
        } catch (IllegalActionOnResourceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return resource;

    }

    public static Relation getRelation(Resource resource, String relationName){
        for (Relation relation : resource.getRelations()) {
            if(relation.getName().equals(relationName)){
                return relation;
            }
        }
        return null;
    }

    public static Resource getChild(Resource resource, String childName){
        return resource.getResource(resource.getPath().addElements(childName).toString());
    }


}
