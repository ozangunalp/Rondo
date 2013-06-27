package fr.liglab.adele.rondo.infra;

import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;
import fr.liglab.adele.rondo.infra.model.*;
import fr.liglab.adele.rondo.infra.model.Package;
import org.junit.Test;

import java.util.*;

import static fr.liglab.adele.rondo.infra.impl.AbstractResourceDeclaration.*;
import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.ConfigurationImpl.configuration;
import static fr.liglab.adele.rondo.infra.impl.FileImpl.file;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.condition;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static fr.liglab.adele.rondo.infra.impl.PackageImpl.aPackage;
import static fr.liglab.adele.rondo.infra.impl.ServiceImpl.service;


/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/26/13
 * Time: 8:30 AM
 */

public class TestInfrastructureBuilders {

    @Test
    public void testBuilder() throws Exception {
        InfrastructureImpl inf = infrastructure()
                .validWhen(
                        condition(file("file")
                                .source("")
                                .path("aaa")
                                .executable(true)).isFalse(),
                        condition(configuration()
                                .pid("pid")
                                .location("asdgsadgasd")).isTrue())

                .resource(
                        bundle().name("bundle1")
                                .source("some url")
                                .state("resolved")
                                .with("symbolic-id").setto("org.core.osgi")
                                .with("version").setto("4.3.1"))
                .resource(
                        file().name("file")
                                .source("some other url")
                                .state("present")
                                .template("template url"))

                .resource(
                        configuration().name("conf id")
                            .pid("jander")
                            .with("name").setto("bocqueterio"))
                .resource(
                        bundle().name("bundle2")
                                .source("some url2")
                                .state("active")
                                .with("symbolic-id").setto("asdgasdg")
                                .with("version").setto("1.2.4"))
                .resource(
                        aPackage("package1")
                                .with("agas").setto(list(1, 3, 4))
                                .with("adsf").setto(list().with(1).with(3))
                                .with("adgfadg")
                                .setto(map()
                                        .with(
                                                pair("osman", "value"),
                                                pair("adsgsadg", 3)
                                        )))
                .resource(service("service").objectClass(list("asdgadsg", "asdgadsgsdg")))

                .when(condition(bundle()
                        .symbolicName("adgag")
                        .state("valid")).isTrue())
                .then("infra", infrastructure()
                        .resource(aPackage("config admin")
                                .name("cm")
                                .version("version")))

                .with("http service", http(8080));

        inf.resource(Bundle.class, "bundle1").dependsOn(Package.class, "package1")
                .resource(Configuration.class, "conf id").dependsOn(File.class, "file")
                .resource(File.class, "file").dependsOn(Bundle.class, "bundle2")
                .resource(Bundle.class, "bundle2").dependsOn(Bundle.class, "bundle1")
        // circular dependency
        //.resource(Package.class,"package1").dependsOn(Configuration.class,"conf id");
        ;
        Map<String, ResourceReference<Bundle>> map = inf.getResourceReferences(Bundle.class);
        ResourceReference<Bundle> resource = inf.getResourceReference(Bundle.class, "bundle1");
        System.out.println(map);
        System.out.println(resource);
        ResourceReference<File> fileReference = inf.getResourceReference(File.class, "file");
        System.out.println(inf.getResource(fileReference));
        System.out.println("DEPENDENCIES");
        for (Dependency dependency : inf.getDependencies()) {
            System.out.println(dependency);
        }
        System.out.println("PRECONDITIONS");
        for (Condition condition : inf.getPreConditions()) {
            System.out.println(condition);
        }
        System.out.println("CONTAINED INFRAS");
        for (ContainedInfrastructure containedInfrastructure : inf.getContainedInfrastructures()) {
            System.out.println(containedInfrastructure);
        }

        Set<ResourceReference> resolvedSet = new LinkedHashSet<ResourceReference>();
        List<ResourceReference> unresolvedList = new ArrayList<ResourceReference>();

        List<ResourceReference> resourceReferences = inf.getResourceReferences();
        Iterator<ResourceReference> iterator = resourceReferences.iterator();
        ResourceReference resourceReference = iterator.next();

        while(!resolvedSet.containsAll(resourceReferences)){
            System.out.println("Resolving: "+resourceReference);
            resolve(resourceReference,resolvedSet,unresolvedList);
            System.out.println("\t"+resolvedSet);
            while(iterator.hasNext() && resolvedSet.contains(resourceReference)){
                resourceReference = iterator.next();
            }
        }
    }

    public Infrastructure http(int port){
        return infrastructure().resource(bundle("http service")
                .symbolicName("agasasg"));
    }

    public void resolve(ResourceReference resourceReference, Set<ResourceReference> resolved, List<ResourceReference> unresolved) throws Exception {
        System.out.println("\t"+resourceReference);
        unresolved.add(resourceReference);
        for (Dependency dependency : resourceReference.dependencies()) {
            ResourceReference provider = dependency.provider();
            if(!resolved.contains(provider)){
                if(unresolved.contains(provider)){
                    throw new Exception("Detected circular dependency between "+resourceReference+" -> "+ provider + " : " + unresolved);
                }
                resolve(provider, resolved, unresolved);
            }
        }
        resolved.add(resourceReference);
        unresolved.remove(resourceReference);
    }

}
