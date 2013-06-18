package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Bundle;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import fr.liglab.adele.rondo.infra.model.Service;
import org.junit.Test;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;

import static fr.liglab.adele.rondo.infra.impl.AbstractResourceDeclaration.list;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static fr.liglab.adele.rondo.infra.impl.ServiceImpl.service;
import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/12/13
 * Time: 8:40 AM
 */
@ExamReactorStrategy(PerMethod.class)
public class TestServicesDeploy extends RondoDeployerTest {

    @Test
    public void testServices(){
        Infrastructure inf = infrastructure()

                .resource(bundle("config admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.configadmin-1.6.0.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.configadmin")
                        .version("1.6.0")
                        .with("checksum").setto("d632c7c4146d764954fdac7c41b597ffa78e3672"))   //d632c7c4146d764954fdac7c41b597ffa78e3672
//
//                .resource(bundle("file install")
//                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
//                        .state("ACTIVE")
//                        .symbolicName("org.apache.felix.fileinstall")
//                        .version("3.2.6"))
//
//                .resource(bundle("event admin")
//                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
//                        .state("ACTIVE")
//                        .symbolicName("org.apache.felix.eventadmin")
//                        .version("1.3.2"))
//
//                .resource(bundle("groovy")
//                        .source("file:///Volumes/Macintosh%20HD/Users/ozan/dev/rondo/rondo-groovy-script/target/rondo-groovy-script-0.0.1-SNAPSHOT.jar")
//                        .state("INSTALLED")
//                        .symbolicName("fr.liglab.adele.rondo.groovy-script"))

                .resource(service("config admin")
                        .objectClass(list(ConfigurationAdmin.class.getName()))
                        .pid("org.apache.felix.cm.ConfigurationAdmin")
                        //.filter("()"))
                )

//                .resource(file("a third file")
//                        .source("file:///Users/ozan/Desktop/Untitled.tiff")
//                        .path("file:///Users/ozan/Desktop/Untitled.jpeg")
//                        .executable(true)
//                        .writable(true))

//                .resource(Bundle.class, "groovy").dependsOn(Bundle.class, "event admin")
//                .resource(Bundle.class,"event admin").dependsOn(Bundle.class,"config admin")
//                .resource(File.class,"a file").dependsOn(Bundle.class,"config admin")
                .resource(Service.class,"config admin").dependsOn(Bundle.class,"config admin");
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        ConfigurationAdmin configurationAdmin;
        ServiceReference serviceReference = osgiHelper.getServiceReference(ConfigurationAdmin.class);
        for (String key : serviceReference.getPropertyKeys()) {
            System.out.println(key+ " - " + serviceReference.getProperty(key));
        }

        configurationAdmin = (ConfigurationAdmin) osgiHelper.getContext().getService(serviceReference);
        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();

        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);

        System.out.flush();
    }


}
