package fr.liglab.adele.rondo.infra.example;

import fr.liglab.adele.rondo.infra.annotations.Infrastructure;
import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;
import fr.liglab.adele.rondo.infra.model.Bundle;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static fr.liglab.adele.rondo.infra.impl.PackageImpl.aPackage;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/20/13
 * Time: 2:15 PM
 */

@Infrastructure(immediate = true)
public class IcasaPlatform {

    InfrastructureImpl inf ;

    public IcasaPlatform() {
        inf = infrastructure()
                // bundles
                .resource(bundle("commons-io")
                        .source("mvn:commons-io/commons-io/2.4")
                        .symbolicName("org.apache.commons.io")
                        .version("2.4.0")
                        .state("ACTIVE"))
                .resource(bundle("icasa common")
                        .source("mvn:fr.liglab.adele.icasa/common/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))

                .resource(bundle("icasa app api")
                        .source("mvn:fr.liglab.adele.icasa/application.api/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa app impl")
                        .source("mvn:fr.liglab.adele.icasa/application.impl/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa context api")
                        .source("mvn:fr.liglab.adele.icasa/context.api/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa context impl")
                        .source("mvn:fr.liglab.adele.icasa/context.impl/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa context remote")
                        .source("mvn:fr.liglab.adele.icasa/context.remote/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa device manager api")
                        .source("mvn:fr.liglab.adele.icasa/device.manager.api/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa gogo adapter")
                        .source("mvn:fr.liglab.adele.icasa/gogo.adapter/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa technical services api")
                        .source("mvn:fr.liglab.adele.icasa/technical.services.api/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa technical services impl")
                        .source("mvn:fr.liglab.adele.icasa/technical.services.impl/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa clock system")
                        .source("mvn:fr.liglab.adele.icasa/clock.system.impl/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
// simulator

                .resource(bundle("icasa simulator impl")
                        .source("mvn:fr.liglab.adele.icasa/simulator.api/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa simulator api")
                        .source("mvn:fr.liglab.adele.icasa/simulator.impl/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa simulator remote")
                        .source("mvn:fr.liglab.adele.icasa/simulator.remote/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa device light")
                        .source("mvn:fr.liglab.adele.icasa/device.light/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa device sound")
                        .source("mvn:fr.liglab.adele.icasa/device.sound/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa device temperature")
                        .source("mvn:fr.liglab.adele.icasa/device.temperature/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa device gas sensor")
                        .source("mvn:fr.liglab.adele.icasa/device.gasSensor/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa device presence")
                        .source("mvn:fr.liglab.adele.icasa/device.presence/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa device power")
                        .source("mvn:fr.liglab.adele.icasa/device.power/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa device personal")
                        .source("mvn:fr.liglab.adele.icasa/device.personal/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("icasa device bathroom scale")
                        .source("mvn:fr.liglab.adele.icasa/device.bathroom.scale/1.0.1-SNAPSHOT")
                        .state("ACTIVE"))
                .resource(bundle("json")
                        .source("mvn:org.ow2.chameleon.json/json-service-json.org")
                        .state("ACTIVE"))
                .resource(bundle("jaxb")
                        .source("mvn:javax.xml.bind/jaxb-api-osgi")
                        .state("ACTIVE"))
                .resource(bundle("jersey client")
                        .source("mvn:com.sun.jersey/jersey-client")
                        .state("ACTIVE"))
                .resource(bundle("jersey server")
                        .source("mvn:com.sun.jersey/jersey-server")
                        .state("ACTIVE"))
                .resource(bundle("jersey core")
                        .source("mvn:com.sun.jersey/jersey-core")
                        .state("ACTIVE"))
                .resource(bundle("jersey exporter")
                        .source("mvn:org.ow2.chameleon.rose.rest/jersey-exporter")
                        .state("ACTIVE"))
                .resource(bundle("rose atmosphere service")
                        .source("mvn:org.ow2.chameleon.rose.atmosphere/atmosgi-service")
                        .state("ACTIVE"))
                .resource(bundle("rose atmosphere component")
                        .source("mvn:org.ow2.chameleon.rose.atmosphere/atmosgi-component")
                        .state("ACTIVE"))

                .resource(bundle("file install")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))

                        //packages
                .resource(aPackage("cm")
                        .name("org.osgi.service.cm")
                        .version("1.3.0"))

                .resource(aPackage("log")
                        .name("org.osgi.service.log")
                        .version("1.3.0"))

                .resource(aPackage("dp")
                        .name("org.osgi.service.deploymentadmin")
                        .version("1.1.0"))

                .resource(Bundle.class, "icasa context api").dependsOn(Bundle.class,"json")
                .resource(Bundle.class, "icasa app api").dependsOn(Bundle.class,"icasa common")

                .resource(Bundle.class, "icasa app impl").dependsOn(Bundle.class,"icasa app api")
                .resource(Bundle.class, "icasa app impl").dependsOn(Bundle.class,"icasa common")
                .resource(Bundle.class, "icasa app impl").dependsOn(fr.liglab.adele.rondo.infra.model.Package.class,"log")
                .resource(Bundle.class, "icasa app impl").dependsOn(fr.liglab.adele.rondo.infra.model.Package.class,"cm")
                .resource(Bundle.class, "icasa app impl").dependsOn(fr.liglab.adele.rondo.infra.model.Package.class,"dp")

                .resource(Bundle.class, "icasa context impl").dependsOn(fr.liglab.adele.rondo.infra.model.Package.class,"log")
                .resource(Bundle.class, "icasa context impl").dependsOn(fr.liglab.adele.rondo.infra.model.Package.class,"cm")
                .resource(Bundle.class, "icasa context impl").dependsOn(Bundle.class,"icasa context api")

                .resource(Bundle.class, "icasa context remote").dependsOn(Bundle.class,"icasa context api")
                //.resource(Bundle.class, "icasa context remote").dependsOn(Bundle.class,"rose atmosphere service")

                .resource(Bundle.class, "icasa device manager api").dependsOn(Bundle.class,"icasa app api")
                .resource(Bundle.class, "icasa gogo adapter").dependsOn(Bundle.class,"file install")
                .resource(Bundle.class, "icasa technical services api").dependsOn(Bundle.class,"icasa context api")
                .resource(Bundle.class, "icasa technical services impl").dependsOn(Bundle.class,"icasa technical services api")

        ;
    }

}
