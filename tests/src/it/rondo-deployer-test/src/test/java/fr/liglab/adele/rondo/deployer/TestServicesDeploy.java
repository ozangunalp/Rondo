package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Bundle;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import fr.liglab.adele.rondo.infra.model.Service;
import fr.liglab.adele.rondo.infra.test.services.FooService;
import org.apache.felix.ipojo.ComponentInstance;
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

                .resource(service("config admin")
                        .objectClass(list(ConfigurationAdmin.class.getName()))
                        .pid("org.apache.felix.cm.ConfigurationAdmin")
                        //.filter("()"))
                )

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
    @Test
    public void testServiceFilter(){
        ComponentInstance foo = ipojoHelper.createComponentInstance("Foo", "foo-1");
        foo.start();

        Infrastructure inf = infrastructure()
                .resource(service("foo service")
                        .objectClass(list(FooService.class.getName()))
                        //.pid("org.apache.felix.cm.ConfigurationAdmin")
                        .filter("(instance.name=foo-1)"))
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);
        ServiceReference serviceReference = osgiHelper.getServiceReference(FooService.class);
        for (String key : serviceReference.getPropertyKeys()) {
            System.out.println(key + " - " + serviceReference.getProperty(key));
        }
        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);
        System.out.flush();
    }


}
