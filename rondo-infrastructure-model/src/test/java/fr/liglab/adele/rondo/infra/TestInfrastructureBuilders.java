package fr.liglab.adele.rondo.infra;

import org.junit.Test;

import java.util.Map;

import static fr.liglab.adele.rondo.infra.AbstractResource.*;
import static fr.liglab.adele.rondo.infra.Bundle.bundle;
import static fr.liglab.adele.rondo.infra.Configuration.configuration;
import static fr.liglab.adele.rondo.infra.File.file;
import static fr.liglab.adele.rondo.infra.Infrastructure.infrastructure;
import static fr.liglab.adele.rondo.infra.Package.aPackage;


/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/26/13
 * Time: 8:30 AM
 */

public class TestInfrastructureBuilders {

    @Test
    public void testBuilder() {
        Infrastructure inf = infrastructure()
                .resource(
                        bundle().name("bundle1")
                                .source("some url")
                                .state("resolved")
                                .with("symbolic-name").setto("org.core.osgi")
                                .with("version").setto("4.3.1"))
                .resource(
                        file().name("file")
                                .source("some other url")
                                .state("present")
                                .template("template url"))

                .resource(
                        configuration().name("conf name"))
                .resource(
                        bundle().name("bundle2")
                                .source("some url2")
                                .state("active")
                                .with("symbolic-name").setto("asdgasdg")
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
                                        ))
                );


        inf.resource(Bundle.class, "bundle1").dependsOn(Package.class, "package1")
                .resource(Configuration.class, "conf name").dependsOn(File.class, "file").dependsOn(Bundle.class, "bundle2").dependsOn(Bundle.class, "bundle1");

        Map<String, Bundle> map = inf.getResources(Bundle.class);
        Bundle resource = inf.getResource(Bundle.class, "bundle1");
        System.out.println(map);
        System.out.println(resource);
        System.out.println(inf.getResource(File.class, "file"));
        for (Infrastructure.Dependency dependency : inf.getDependencies()) {
            System.out.println(dependency);
        }

    }

}
