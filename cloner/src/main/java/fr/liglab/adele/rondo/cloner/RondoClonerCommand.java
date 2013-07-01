package fr.liglab.adele.rondo.cloner;

import fr.liglab.adele.rondo.infra.impl.*;
import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.ResourceReferenceImpl;
import fr.liglab.adele.rondo.infra.model.*;
import fr.liglab.adele.rondo.infra.model.Package;
import org.apache.commons.io.FileUtils;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.apache.felix.service.command.Descriptor;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.ComponentImpl.component;
import static fr.liglab.adele.rondo.infra.impl.InstanceImpl.instance;
import static fr.liglab.adele.rondo.infra.impl.ServiceImpl.service;

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
     * Defines the command scope (rondo).
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
        try{
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

            File file = printInfra(infrastructure, "./","ClonedInfrastructure");
            System.out.println(file.getCanonicalPath());
        } catch (Throwable t){
            t.printStackTrace();
        }
    }

    public Collection<Long> handleBundles(Collection<Long> bundleIds,InfrastructureImpl infrastructure) {

        Set<Long> nextBundleIds = new HashSet<Long>();

        //Resource packages = this.get("/osgi/packages");

        for (Long bundleId : bundleIds) {

            Resource bundleResource = this.get("/osgi/bundles/" + bundleId);
            BundleImpl bundle = bundleResource(bundleResource);

            ResourceReferenceImpl bundleResourceReference = infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id());
            //if(infrastructure.getResource(bundleResourceReference)==null){
            // infrastructure doesn't contain bundle

            // add bundle
            infrastructure.resource(bundle);
            System.out.println("BUNDLE "+bundle.id());
            addFragmentFrom(infrastructure,bundleResource);
            addPackagesFrom(infrastructure,bundleResource);
            Set<Long> bundles = addRequirementsFrom(infrastructure, bundleResource);
            //addComponentsFrom(infrastructure,bundleResource);
            //addServicesFrom(infrastructure,bundleResource);
            bundles.removeAll(bundleIds);
            nextBundleIds.addAll(bundles);
            //}

        }
        return nextBundleIds;
    }

    private void addFragmentFrom(InfrastructureImpl infrastructure, Resource bundleResource) {
        Boolean isFragment = bundleResource.getMetadata().get("bundle-fragment", Boolean.class);
        if(isFragment){
            BundleImpl bundle = bundleResource(bundleResource);
            // look at its requirements
            Resource requirements = getChild(bundleResource, "requirements");
            if(requirements!=null){
                for (Resource requirement : requirements.getResources()) {
                    Relation fragment = getRelation(requirement, "fragment-host");
                    if(fragment!=null){
                        // if fragment requirement
                        for (Relation relation : requirement.getRelations()) {
                            if(relation.getName().startsWith("wire")){
                                Resource wire = this.get(relation.getHref().toString());
                                Relation wireRel = getRelation(wire, "capability");
                                if(wireRel!=null){
                                    Resource hostBundleResource = this.get(wireRel.getHref().getParent().getParent().toString());
                                    BundleImpl hostBundleInstalled = fragmentHostResource(hostBundleResource);
                                    BundleImpl hostBundleNormal = bundleResource(hostBundleResource);
                                    //System.out.println("HOST "+hostBundleInstalled);
                                    infrastructure.resource(hostBundleInstalled);
                                    // host bundle depends on installed host bundle
                                    //System.out.println(hostBundleNormal.id()+" -> "+hostBundleInstalled.id());
                                    infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class, hostBundleNormal.id())
                                            .dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class, hostBundleInstalled.id());
                                    // fragment depends on installed host bundle
                                    //System.out.println(bundle.id()+" -> "+hostBundleInstalled.id());
                                    infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id())
                                            .dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class, hostBundleInstalled.id());
                                    // host bundle depends on fragment
                                    //System.out.println(hostBundleNormal.id()+" -> "+bundle.id());
                                    infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class, hostBundleNormal.id())
                                            .dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class,bundle.id());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void addPackagesFrom(InfrastructureImpl infrastructure, Resource bundleResource){
        // BundleImpl bundle = bundleResource(bundleResource);
        // look at its capabilities
        Resource capabilities = getChild(bundleResource, "capabilities");
        if(capabilities!=null){
            for (Resource capability : capabilities.getResources()) {
                Relation relation = getRelation(capability, "package");
                if(relation!=null){
                    Resource pkg = this.get(relation.getHref().toString());
                    Relation providerBundleRelation = getRelation(pkg, "provider-bundle");
                    Resource providerBundleResource = this.get(providerBundleRelation.getHref().toString());
                    BundleImpl bundle = bundleResource(providerBundleResource);
                    PackageImpl aPackage = packageResource(pkg);
                    // add package
                    //System.out.println("PACKAGE "+aPackage);
                    infrastructure.resource(bundle);
                    infrastructure.resource(aPackage);
                    // add dependency to the bundle
                    //System.out.println(aPackage.id()+" -> "+bundle.id());
                    infrastructure.resource(fr.liglab.adele.rondo.infra.model.Package.class, aPackage.id())
                            .dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id());
                }
            }
        }
    }

    public Set<Long> addRequirementsFrom(InfrastructureImpl infrastructure, Resource bundleResource){
        Set<Long> nextBundleIds = new HashSet<Long>();
        BundleImpl bundle = bundleResource(bundleResource);
        // look at its requirements
        Resource requirements = getChild(bundleResource, "requirements");
        if(requirements!=null){
            for (Resource requirement : requirements.getResources()) {
                Relation requireBundle = getRelation(requirement, "require-bundle");
                if(requireBundle!=null){
                    // if bundle requirement
                    Resource requiredBundleResource = get(requireBundle.getHref().toString());
                    if(requiredBundleResource!=null){
                        BundleImpl requiredBundle = bundleResource(requiredBundleResource);
                        //System.out.println("\t"+bundle.id()+" ->"+requiredBundle.id());
                        infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class,bundle.id())
                                .dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class, requiredBundle.id());
                        Long nextBundleId = requiredBundleResource.getMetadata().get("bundle-id", Long.class);
                        nextBundleIds.add(nextBundleId);
                    }
                } else {
                    Relation importPackage = getRelation(requirement, "import-package");
                    if(importPackage!=null){
                        // if package requirement
                        ResourceMetadata directives = requirement.getMetadata().get("directives", ResourceMetadata.class);
                        String resolution = directives.get("resolution", String.class);
                        if(resolution==null || !"optional".equals(resolution)){
                            for (Relation relation : requirement.getRelations()) {
                                if(relation.getName().startsWith("wire")){
                                    Resource wire = this.get(relation.getHref().toString());
                                    Relation wireRel = getRelation(wire, "capability");
                                    if(wireRel!=null){
                                        Resource capability = this.get(wireRel.getHref().toString());
                                        if(capability!=null){
                                            Relation pkgRel = getRelation(capability, "package");
                                            if(pkgRel!=null){
                                                //System.out.println(importPackage.getHref().toString());
                                                Resource pkg = this.get(pkgRel.getHref().toString());
                                                PackageImpl aPackage = packageResource(pkg);
                                                // add package
                                                infrastructure.resource(aPackage);
                                                // add dependency to the bundle
                                                Resource importerBundleResource = this.get(importPackage.getHref().getParent().getParent().getParent().toString());
                                                BundleImpl importerBundle = bundleResource(importerBundleResource);
                                                infrastructure.resource(importerBundle);
                                                //System.out.println("\t"+importerBundle.id()+" -> "+aPackage.id());
                                                infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class, importerBundle.id())
                                                        .dependsOn(fr.liglab.adele.rondo.infra.model.Package.class, aPackage.id());

                                                Relation providerBundleRelation = getRelation(pkg, "provider-bundle");
                                                if(providerBundleRelation!=null){
                                                    Resource providerBundle = this.get(providerBundleRelation.getHref().toString());
                                                    Long nextBundleId = providerBundle.getMetadata().get("bundle-id", Long.class);
                                                    nextBundleIds.add(nextBundleId);
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

    public void addComponentsFrom(InfrastructureImpl infrastructure, Resource bundleResource){
        BundleImpl bundle = bundleResource(bundleResource);
        Resource factories = this.get("/ipojo/factory");
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
                        //System.out.println("\t"+component.id()+" -> "+bundle.id());
                        infrastructure.resource(fr.liglab.adele.rondo.infra.model.Component.class, component.id())
                                .dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id());

                        // add instances
                        for (Relation relation : factory.getRelations()) {
                            if(relation.getName().startsWith("instance")){
                                Resource instanceResource = this.get(relation.getHref().toString());
                                InstanceImpl instance = instanceResource(instanceResource);

//                                        // add instance
//                                        System.out.println("INSTANCE "+instance);
//                                        infrastructure.resource(instance);
//                                        // add dependency to component
//                                        System.out.println("\t"+instance.id()+" -> "+component.id());
//                                        infrastructure.resource(Instance.class,instance.id())
//                                                .dependsOn(fr.liglab.adele.rondo.infra.model.Component.class,component.id());
//
//                                        // find provided services
//                                        Relation providings = getRelation(instanceResource, "providings");
//                                        if(providings!=null){
//                                            Resource providing = this.get(providings.getHref().toString());
//                                            for (Relation providingRel : providing.getRelations()) {
//                                                Resource providingResource = this.get(providingRel.getHref().toString());
//                                                Relation service = getRelation(providingResource, "service");
//                                                if(service!=null){
//                                                    Resource serviceResource = this.get(service.getHref().toString());
//                                                    ServiceImpl providedService = serviceResource(serviceResource);
//                                                    //System.out.println("\tPROVIDED SERVICE "+providedService);
//                                                    // add dependency to instance
//                                                    infrastructure.resource(Service.class,providedService.id())
//                                                            .dependsOn(Instance.class,instance.id());
//                                                }
//                                            }
//                                        }
//
//                                        // find depending services
//                                        Relation dependencies = getRelation(instanceResource, "dependencies");
//                                        if(dependencies!=null){
//                                            Resource dependency = this.get(dependencies.getHref().toString());
//                                            for (Relation dependencyRel : dependency.getRelations()) {
//                                                Resource dependencyResource = this.get(dependencyRel.getHref().toString());
//                                                for (Relation dependencyRelation : dependencyResource.getRelations()) {
//                                                    if(dependencyRelation.getName().startsWith("usedService")){
//                                                        Resource serviceResource = this.get(dependencyRelation.getHref().toString());
//                                                        if(serviceResource!=null){
//                                                            ServiceImpl requiredService = serviceResource(serviceResource);
//                                                            //System.out.println("\tREQUIRED SERVICE "+requiredService);
//                                                            // add to infrastructure
//                                                            infrastructure.resource(requiredService);
//                                                            // add dependency to service
//                                                            infrastructure
//                                                                    .resource(Instance.class,instance.id())
//                                                                    .dependsOn(Service.class,requiredService.id());
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
                            }
                        }
                    }
                }

            }

        }

    }

    public void addServicesFrom(InfrastructureImpl infrastructure, Resource bundleResource){
        BundleImpl bundle = bundleResource(bundleResource);
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
        String idString = Long.toString(metadata.get("bundle-id", Long.class));
        bundle = bundle(idString + "-" + metadata.get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class));
        bundle.symbolicName(metadata.get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class));
        bundle.version(metadata.get(Constants.BUNDLE_VERSION_ATTRIBUTE, Version.class).toString());
        if(!idString.equals("0")){
            bundle.source(metadata.get("bundle-location", String.class));
        }
        bundle.state(metadata.get("bundle-state", String.class));
        // add headers
        Resource headers = getChild(bundleResource, "headers");
        //bundle.with("headers").setto(headers.getMetadata());

        return bundle;
    }
    public static BundleImpl fragmentHostResource(Resource bundleResource){
        BundleImpl bundle= null;
        ResourceMetadata metadata = bundleResource.getMetadata();
        bundle = bundle(Long.toString(metadata.get("bundle-id", Long.class)) + "-" +
                metadata.get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class) + "-installed");
        bundle.symbolicName(metadata.get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class));
        bundle.version(metadata.get(Constants.BUNDLE_VERSION_ATTRIBUTE, Version.class).toString());
        bundle.source(metadata.get("bundle-location", String.class));
        bundle.state("INSTALLED");
        // add headers
        Resource headers = getChild(bundleResource, "headers");
        //bundle.with("headers").setto(headers.getMetadata());

        return bundle;
    }

    public static PackageImpl packageResource(Resource packageResource){
        ResourceMetadata metadata = packageResource.getMetadata().get("attributes",ResourceMetadata.class);
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
            // no need for fuss
        } catch (ResourceNotFoundException e) {
            // ok, then go on...
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

    public static File printInfra(InfrastructureImpl infrastructure, String path, String name) {

        String fileName = name+".java";

        File dumpFile = new File(path+fileName);

        StringBuffer sb = new StringBuffer();

        sb.append("\n");
        sb.append("import fr.liglab.adele.rondo.infra.annotations.Infrastructure;\n");
        sb.append("import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;\n");
        sb.append("import fr.liglab.adele.rondo.infra.model.Package;\n");
        sb.append("import fr.liglab.adele.rondo.infra.model.*;\n");
        sb.append("\n");
        sb.append("import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;\n");
        sb.append("import static fr.liglab.adele.rondo.infra.impl.PackageImpl.aPackage;\n");
        sb.append("import static fr.liglab.adele.rondo.infra.impl.ComponentImpl.component;\n");
        sb.append("import static fr.liglab.adele.rondo.infra.impl.InstanceImpl.instance;\n");
        sb.append("import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;\n");
        sb.append("\n");
        sb.append("\n");


        sb.append("@Infrastructure(immediate = true)\n");
        sb.append("public class ").append(name).append(" {\n\n");
        sb.append("InfrastructureImpl ").append(infrastructure.getName()).append(";");
        sb.append("\n");
        sb.append("public ").append(name).append("() {");
        sb.append("\n");
        sb.append(infrastructure.getName()).append(" = infrastructure();");
        sb.append("\n");
        Map<String, ResourceReference<fr.liglab.adele.rondo.infra.model.Bundle>> bundleMap = infrastructure.getResourceReferences(fr.liglab.adele.rondo.infra.model.Bundle.class);

        for (String bundleId : bundleMap.keySet()) {
            ResourceReference<fr.liglab.adele.rondo.infra.model.Bundle> bundleReference = bundleMap.get(bundleId);
            fr.liglab.adele.rondo.infra.model.Bundle bundle = infrastructure.getResource(bundleReference);
            sb.append("\n");
            sb.append(infrastructure.getName())
                    .append(".resource(");
            sb.append("bundle(\"")
                    .append(bundleId)
                    .append("\")");
            sb.append("\n");
            if(bundle.source()!=null){
                sb.append("\t");
                sb.append(".source(\"")
                        .append(bundle.source())
                        .append("\")");
                sb.append("\n");
            }
            sb.append("\t");
            sb.append(".symbolicName(\"")
                    .append(bundle.symbolicName())
                    .append("\")");
            sb.append("\n");
            sb.append("\t");
            sb.append(".version(\"")
                    .append(bundle.version())
                    .append("\")");
            sb.append("\n");
            sb.append("\t");
            sb.append(".state(\"")
                    .append(bundle.state())
                    .append("\")");
            sb.append(");");
            sb.append("\n");
        }

        sb.append("\n");

        Map<String, ResourceReference<Package>> packageMap = infrastructure.getResourceReferences(Package.class);

        for (String pkgId : packageMap.keySet()) {
            ResourceReference<Package> packageReference = packageMap.get(pkgId);
            Package pkg = infrastructure.getResource(packageReference);
            sb.append("\n");
            sb.append(infrastructure.getName()).append(".resource(");
            sb.append("aPackage(\"").append(pkgId).append("\")");
            sb.append("\n");
            sb.append("\t");
            sb.append(".name(\"").append(pkg.name()).append("\")");
            sb.append("\n");
            sb.append("\t");
            sb.append(".version(\"").append(pkg.version()).append("\")");
            sb.append(");");
            sb.append("\n");
        }
        sb.append("\n");

        Map<String, ResourceReference<fr.liglab.adele.rondo.infra.model.Component>> componentMap = infrastructure.getResourceReferences(fr.liglab.adele.rondo.infra.model.Component.class);

        for (String componentId : componentMap.keySet()) {
            ResourceReference<fr.liglab.adele.rondo.infra.model.Component> componentResourceReference = componentMap.get(componentId);
            fr.liglab.adele.rondo.infra.model.Component component = infrastructure.getResource(componentResourceReference);
            sb.append("\n");
            sb.append(infrastructure.getName())
                    .append(".resource(");
            sb.append("component(\"")
                    .append(componentId)
                    .append("\")");
            sb.append("\n");
            sb.append("\t");
            sb.append(".name(\"")
                    .append(component.name())
                    .append("\")");
            sb.append("\n");
            sb.append("\t");
            sb.append(".version(\"")
                    .append(component.version())
                    .append("\")");
            sb.append(");");
            sb.append("\n");
        }
        sb.append("\n");


        for (Dependency dependency : infrastructure.getDependencies()) {

            sb.append(infrastructure.getName())
                    .append(".resource(")
                    .append(dependency.requirer().type().getSimpleName())
                    .append(".class,\"")
                    .append(dependency.requirer().id())
                    .append("\")");
            sb.append("\n\t");
            sb.append(".dependsOn(")
                    .append(dependency.provider().type().getSimpleName())
                    .append(".class,\"")
                    .append(dependency.provider().id())
                    .append("\");");
            sb.append("\n\n");
        }

        sb.append("}\n");
        sb.append("}");

        try {
            FileUtils.writeStringToFile(dumpFile, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        return dumpFile;
    }


}
