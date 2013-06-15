package fr.liglab.adele.rondo.infra.test;

import fr.liglab.adele.rondo.infra.annotations.Infrastructure;
import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static fr.liglab.adele.rondo.infra.impl.PackageImpl.aPackage;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/7/13
 * Time: 1:34 AM
 */

@Infrastructure(immediate = true)
public class ExampleInfrastructure {

        InfrastructureImpl inf = infrastructure()
                .resource(bundle("config admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.configadmin-1.6.0.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.configadmin")
                        .version("1.6.0")
                        .with("checksum").setto("d632c7c4146d764954fdac7c41b597ffa78e3672"))   //d632c7c4146d764954fdac7c41b597ffa78e3672
                .resource(bundle("file install")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .state("INSTALLED")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))
                .resource(bundle("log")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.log-1.0.1.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.log")
                        .version("1.0.1"))

                .resource(bundle("event admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.eventadmin")
                        .version("1.3.2"))

                .resource(bundle("event handler")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.ipojo.handler.eventadmin-1.8.0.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.ipojo.handler.eventadmin")
                        .version("1.8.0"))

                .resource(aPackage("org.apache.felix.ipojo"))

                .resource(bundle("groovy")
                        .source("file:///Volumes/Macintosh%20HD/Users/ozan/dev/rondo/rondo-groovy-script/target/rondo-groovy-script-0.0.1-SNAPSHOT.jar")
                        .state("INSTALLED")
                        .symbolicName("fr.liglab.adele.rondo.groovy-script"))

//                .resource(instance("vadg")
//                        .factory("tata")
//                        .with("toto").setto("tata"))
//
//                .resource(component("tata")
//                        .version("1.0")
//                        .name("tata-x")
//                        .with("property").setto("toto"))
//
//                .resource(Bundle.class, "groovy").dependsOn(Bundle.class, "event handler")
//                .resource(Bundle.class, "event handler").dependsOn(Bundle.class, "event admin")
//
//                .resource(service("")
//                    .objectClass(list(""))
//                    .with("property").setto("value"))

                ;

}
