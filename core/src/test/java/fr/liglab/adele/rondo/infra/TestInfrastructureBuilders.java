package fr.liglab.adele.rondo.infra;

import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;
import fr.liglab.adele.rondo.infra.model.*;
import fr.liglab.adele.rondo.infra.model.Package;
import org.junit.Test;

import java.util.Map;

import static fr.liglab.adele.rondo.infra.impl.AbstractResourceDeclaration.*;
import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.ConfigurationImpl.configuration;
import static fr.liglab.adele.rondo.infra.impl.FileImpl.file;
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
    public void testBuilder() {
        InfrastructureImpl inf = infrastructure()
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
                        configuration().name("conf id"))
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
                .resource(service("service").objectClass(list("asdgadsg", "asdgadsgsdg")));

        System.out.println(inf.getResourceReferences());
        System.out.println(inf.getResources());

        inf.resource(Bundle.class, "bundle1").dependsOn(Package.class, "package1")
                .resource(Configuration.class, "conf id").dependsOn(File.class, "file")
                .resource(File.class, "file").dependsOn(Bundle.class, "bundle2")
                .resource(Bundle.class, "bundle2").dependsOn(Bundle.class, "bundle1");

        Map<String, ResourceReference<Bundle>> map = inf.getResourceReferences(Bundle.class);
        ResourceReference<Bundle> resource = inf.getResourceReference(Bundle.class, "bundle1");
        System.out.println(map);
        System.out.println(resource);
        ResourceReference<File> fileReference = inf.getResourceReference(File.class, "file");
        System.out.println(inf.getResource(fileReference));
        for (Dependency dependency : inf.getDependencies()) {
            System.out.println(dependency);
        }
    }

}
