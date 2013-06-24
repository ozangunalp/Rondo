package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Bundle;
import fr.liglab.adele.rondo.infra.model.Configuration;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import org.junit.Test;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;

import java.io.IOException;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.ConfigurationImpl.configuration;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/12/13
 * Time: 8:40 AM
 */
@ExamReactorStrategy(PerMethod.class)
public class TestConfigDeploy extends RondoDeployerTest {

    @Test
    public void testConfigCreation() throws IOException {

        Infrastructure inf = infrastructure()

                .resource(bundle("file install")
                        //.source("mvn:org.apache.felix/org.apache.felix.fileinstall/3.2.6")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))

                .resource(configuration("a config")
                        .pid("config pid")
                        .location("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .with("prop").setto("value"))

                .resource(Configuration.class, "a config").dependsOn(Bundle.class, "file install")
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);
        ConfigurationAdmin configurationAdmin = osgiHelper.getServiceObject(ConfigurationAdmin.class);
        assertThat(configurationAdmin).isNotNull();

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        System.out.println("DEPLOYMENT PLAN: " + deploymentHandle.getPlan().toString());
        deploymentHandle.apply();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());

        org.osgi.framework.Bundle fileInstall = osgiHelper.getBundle("org.apache.felix.fileinstall");
        assertThat(fileInstall).isNotNull();
        assertThat(fileInstall.getState()).isEqualTo(org.osgi.framework.Bundle.ACTIVE);
        assertThat(fileInstall.getLocation()).isEqualTo("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar");

        org.osgi.service.cm.Configuration newCfg = configurationAdmin.getConfiguration("config pid", "http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar");
        assertThat(newCfg.getProperties().get("prop"))
                .isNotNull()
                .isEqualTo("value");

        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);
        System.out.flush();

    }
    @Test
    public void testConfigUpdate() throws IOException {

        Infrastructure inf = infrastructure()

                .resource(bundle("event admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.eventadmin")
                        .version("1.3.2"))

                .resource(configuration("a second config")
                        .pid("different pid")
                        .location("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
                        .with("prop").setto("value2"))

                .resource(Configuration.class, "a second config").dependsOn(Bundle.class, "event admin")
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);
        ConfigurationAdmin configurationAdmin = osgiHelper.getServiceObject(ConfigurationAdmin.class);
        assertThat(configurationAdmin).isNotNull();
        // create a config
        org.osgi.service.cm.Configuration existingCfg = configurationAdmin.getConfiguration("different pid", "http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar");
        assertThat(existingCfg).isNotNull();
        assertThat(existingCfg.getProperties()).isNull();

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        System.out.println("DEPLOYMENT PLAN: " + deploymentHandle.getPlan().toString());
        deploymentHandle.apply();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());

        org.osgi.service.cm.Configuration exCfg = configurationAdmin.getConfiguration("different pid", "http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar");
        assertThat(exCfg.getProperties().get("prop"))
                .isNotNull()
                .isEqualTo("value2");
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);
        System.out.flush();

    }

    @Test
    public void testFactroyConfigCreation() throws IOException, InvalidSyntaxException {

        Infrastructure inf = infrastructure()

                .resource(bundle("file install")
                        //.source("mvn:org.apache.felix/org.apache.felix.fileinstall/3.2.6")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))

                .resource(configuration("a config")
                        .factoryPid("factory")
                        .location("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .with("prop").setto("value"))

                .resource(Configuration.class, "a config").dependsOn(Bundle.class, "file install")
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);
        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        System.out.println("DEPLOYMENT PLAN: " + deploymentHandle.getPlan().toString());
        deploymentHandle.apply();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());


        ConfigurationAdmin configurationAdmin = osgiHelper.getServiceObject(ConfigurationAdmin.class);
        org.osgi.service.cm.Configuration[] configurations = configurationAdmin.listConfigurations(null);
        for (org.osgi.service.cm.Configuration configuration : configurations) {
            System.out.println(configuration.getPid()+" - "+configuration.getFactoryPid());
        }
    }

}
