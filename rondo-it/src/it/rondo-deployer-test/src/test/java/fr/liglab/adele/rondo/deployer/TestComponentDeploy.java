package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Bundle;
import fr.liglab.adele.rondo.infra.model.Component;
import fr.liglab.adele.rondo.infra.model.Configuration;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import org.junit.Test;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;

import java.io.IOException;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.ComponentImpl.component;
import static fr.liglab.adele.rondo.infra.impl.ConfigurationImpl.configuration;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/12/13
 * Time: 10:29 AM
 */
@ExamReactorStrategy(PerMethod.class)
public class TestComponentDeploy extends RondoDeployerTest {

    @Test
    public void testComponentDeployment() throws IOException {
        Infrastructure inf = infrastructure()

                .resource(bundle("config admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.configadmin-1.6.0.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.configadmin")
                        .version("1.6.0")
                        .with("checksum").setto("d632c7c4146d764954fdac7c41b597ffa78e3672"))   //d632c7c4146d764954fdac7c41b597ffa78e3672

                .resource(bundle("test bundle")
                        .state("ACTIVE")
                        .symbolicName(getTestBundle().getSymbolicName())
                        .version(getTestBundle().getVersion().toString()))

                .resource(component("bar component")
                        .name("org.apache.felix.ipojo.everest.ipojo.test.b1.BarProviderImpl")
                        .state("VALID"))

                .resource(component("foo component")
                        .name("Foo")
                        .version("1.2.3.foo")
                        .state("VALID"))

                .resource(bundle("event admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.eventadmin")
                        .version("1.3.2"))

                .resource(Component.class, "bar component").dependsOn(Bundle.class, "test bundle")
                .resource(Component.class, "foo component").dependsOn(Bundle.class, "test bundle")
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        System.out.println("DEPLOYMENT PLAN: " + deploymentHandle.getPlan().toString());
        deploymentHandle.apply();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());

        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);
        assertThat(ipojoHelper.getFactory("Foo")).isNotNull();
        assertThat(ipojoHelper.getFactory("org.apache.felix.ipojo.everest.ipojo.test.b1.BarProviderImpl")).isNotNull();
        System.out.flush();
    }
}
