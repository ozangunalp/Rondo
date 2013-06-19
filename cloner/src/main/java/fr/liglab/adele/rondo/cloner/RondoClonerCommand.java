package fr.liglab.adele.rondo.cloner;

import fr.liglab.adele.rondo.infra.impl.BundleImpl;
import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;
import fr.liglab.adele.rondo.infra.impl.PackageImpl;
import fr.liglab.adele.rondo.infra.impl.ServiceImpl;
import fr.liglab.adele.rondo.infra.model.Service;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.services.*;
import org.apache.felix.service.command.Descriptor;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
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
    public static final String RANGE_SPLITTER = "...";

    /**
     * Defines the command scope (ipojo).
     */
    @ServiceProperty(name = "osgi.command.scope", value = "deployment")
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
        Set<Long> bundleIds = new HashSet<Long>();
        if(ids.length==0){
            Bundle[] bundles = m_context.getBundles();
            for (Bundle bundle : bundles) {
                bundleIds.add(bundle.getBundleId());
            }
        }
        for (String bundleId : ids) {
            Matcher singleMatcher = singleId.matcher(bundleId);
            if(singleMatcher.matches()){
                long l = Long.parseLong(bundleId);
                bundleIds.add(l);
            } else {
                Matcher rangeMatcher = rangeIds.matcher(bundleId);
                if(rangeMatcher.matches()){
                    String[] split = bundleId.split(RANGE_SPLITTER);
                    for (String s : split) {
                        long l = Long.parseLong(s);
                        bundleIds.add(l);
                    }
                }
            }
        }
        System.out.println("handling these bundles"+ bundleIds);
        handleBundles(bundleIds);
    }

    public InfrastructureImpl handleBundles(Collection<Long> bundleIds) {

        InfrastructureImpl infrastructure = new InfrastructureImpl("newInfra");
        StringBuffer buffer = new StringBuffer();

        Resource packages = null;
        packages = this.get("/osgi/packages");

        for (Long bundleId : bundleIds) {

            Resource bundleResource = this.get("/osgi/bundles/" + bundleId);
            BundleImpl bundle = bundleResource(bundleResource);
            buffer.append("adding bundle: "+bundle.toString());
            // add bundle
            infrastructure.resource(bundle);
            // look at its capabilities
            Resource capabilities = bundleResource.getResource(bundleResource.getPath() + "capabilities");
            for (Resource capability : capabilities.getResources()) {
                for (Relation relation : capability.getRelations()) {
                    if("package".equals(relation.getName())){
                        Resource pkg = this.get(relation.getHref().toString());
                        PackageImpl aPackage = packageResource(pkg);
                        buffer.append("adding package: "+aPackage.toString());
                        // add package
                        infrastructure.resource(aPackage);
                        // add dependency to the bundle
                        buffer.append("adding dependency from "+ aPackage.id()+" to "+bundle.id());
                        infrastructure.resource(fr.liglab.adele.rondo.infra.model.Package.class, aPackage.id())
                                .dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id());
                    }
                }
            }
            // look at its requirements
            Resource requirements = bundleResource.getResource(bundleResource.getPath() + "requirements");
            // requirements
            for (Resource requirement : requirements.getResources()) {
                for (Relation relation : requirement.getRelations()) {
                    if(relation.getName().startsWith("wire")){
                        Resource wire = this.get(relation.getHref().toString());
                        for (Relation wireRel : wire.getRelations()) {
                            if("capability".equals(wireRel.getName())){
                                Resource capability = this.get(wireRel.getHref().toString());
                                for (Relation capabilityRel : capability.getRelations()) {
                                    if("package".equals(relation.getName())){
                                        Resource pkg = this.get(capabilityRel.getHref().toString());
                                        PackageImpl aPackage = packageResource(pkg);
                                        buffer.append("adding package: "+aPackage.toString());
                                        // add package
                                        infrastructure.resource(aPackage);
                                        // add dependency to the bundle
                                        buffer.append("adding dependency from "+bundle.id()+" to "+aPackage.id());
                                        infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id())
                                                .dependsOn(fr.liglab.adele.rondo.infra.model.Package.class, aPackage.id());

                                    }
                                }
                            }
                        }

                    }
                }
            }
            // look at its services

            Resource services = bundleResource.getResource(bundleResource.getPath() + "services");
            for (Resource resource : services.getResources()) {
                if("registered".equals(resource.getPath().getLast())){
                    for (Relation regService : resource.getRelations()) {
                        Resource serviceResource = this.get(regService.toString());
                        ServiceImpl service = serviceResource(serviceResource);
                        buffer.append("adding service: "+service.id());
                        // add service
                        infrastructure.resource(service);
                        // add dependency to the bundle
                        buffer.append("adding: "+service.id()+ "requires to "+bundle.id());
                        infrastructure.resource(Service.class, service.id()).
                                dependsOn(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id());
                    }

                }
                if("uses".equals(resource.getPath().getLast())) {
                    for (Relation usedService : resource.getRelations()) {
                        Resource serviceResource = this.get(usedService.toString());
                        ServiceImpl service = serviceResource(serviceResource);
                        buffer.append("adding service: "+service.id());
                        // add service
                        infrastructure.resource(service);
                        // add dependency to the bundle
                        buffer.append("adding: "+bundle.id()+ " requires to "+service.id());
                        infrastructure.resource(fr.liglab.adele.rondo.infra.model.Bundle.class, bundle.id()).
                                dependsOn(Service.class, service.id());

                    }

                }
            }


        }

    return infrastructure;
}

    BundleImpl bundleResource(Resource bundleResource){
        ResourceMetadata metadata = bundleResource.getMetadata();
        BundleImpl bundle = bundle(
                metadata.get("bundle-id", String.class) + "-" +
                        metadata.get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class));
        bundle.symbolicName(metadata.get(Constants.BUNDLE_SYMBOLICNAME_ATTRIBUTE, String.class));
        bundle.version(metadata.get(Constants.BUNDLE_VERSION_ATTRIBUTE, String.class));
        bundle.source(metadata.get("bundle-location",String.class));
        bundle.state(metadata.get("bundle-state",String.class));
        // add headers
        Resource headers = bundleResource.getResource(bundleResource.getPath() + "headers");
        bundle.with("headers").setto(headers.getMetadata());
        return bundle;
    }

    PackageImpl packageResource(Resource packageResource){
        ResourceMetadata metadata = packageResource.getMetadata();
        String id = packageResource.getPath().getLast();
        PackageImpl pkg = new PackageImpl(id);
        pkg.name(metadata.get("osgi.wiring.package",String.class));
        pkg.version(metadata.get("version",String.class));
        return pkg;
    }

    ServiceImpl serviceResource(Resource serviceResource){
        ResourceMetadata metadata = serviceResource.getMetadata();

        ServiceImpl service = service("");
        return  service;
    }

    Resource get(String path){

        Resource resource = null;
        try {
            resource = m_everest.process(new DefaultRequest(Action.READ, Path.from(path), null));
        } catch (IllegalActionOnResourceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return resource;

    }


}
