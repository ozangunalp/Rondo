package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Bundle;
import fr.liglab.adele.rondo.infra.model.Configuration;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import org.apache.felix.ipojo.everest.osgi.OsgiResourceUtils;
import org.junit.Test;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/12/13
 * Time: 8:39 AM
 */
@ExamReactorStrategy(PerMethod.class)
public class TestBundleDeploy extends RondoDeployerTest {

    @Test
    public void testSuccessfulDeployment(){
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

                .resource(bundle("file install")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))

                .resource(bundle("event admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.eventadmin")
                        .version("1.3.2"))

                .resource(bundle("groovy")
                        .source("file:///Volumes/Macintosh%20HD/Users/ozan/dev/rondo/rondo-groovy-script/target/rondo-groovy-script-0.0.1-SNAPSHOT.jar")
                        .state("INSTALLED")
                        .symbolicName("fr.liglab.adele.rondo.groovy-script"))

                .resource(Bundle.class, "groovy").dependsOn(Bundle.class, "event admin")
                .resource(Bundle.class, "config admin").dependsOn(Bundle.class, "file install")
                .resource(Bundle.class, "event admin").dependsOn(Bundle.class, "config admin")
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        System.out.println("DEPLOYMENT PLAN: " + deploymentHandle.getPlan().toString());
        deploymentHandle.apply();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());

        org.osgi.framework.Bundle fileInstall = osgiHelper.getBundle("org.apache.felix.fileinstall");
        org.osgi.framework.Bundle groovy = osgiHelper.getBundle("fr.liglab.adele.rondo.groovy-script");

        assertThat(fileInstall).isNotNull();
        assertThat(fileInstall.getState()).isEqualTo(org.osgi.framework.Bundle.ACTIVE);
        assertThat(fileInstall.getLocation()).isEqualTo("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar");

        assertThat(groovy).isNotNull();
        assertThat(groovy.getState()).isEqualTo(org.osgi.framework.Bundle.INSTALLED);
        assertThat(groovy.getLocation()).isEqualTo("file:///Volumes/Macintosh%20HD/Users/ozan/dev/rondo/rondo-groovy-script/target/rondo-groovy-script-0.0.1-SNAPSHOT.jar");



        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);
        System.out.flush();
    }

    @Test
    public void testSuccessfulDeploymentMvnUrl(){
        Infrastructure inf = infrastructure()

                .resource(bundle("config admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.configadmin-1.6.0.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.configadmin")
                        .version("1.6.0")
                        .with("checksum").setto("d632c7c4146d764954fdac7c41b597ffa78e3672"))   //d632c7c4146d764954fdac7c41b597ffa78e3672

                .resource(bundle("file install")
                        .source("mvn:org.apache.felix/org.apache.felix.fileinstall/LATEST")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))

                .resource(bundle("event admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.eventadmin")
                        .version("1.3.2"))

                .resource(bundle("groovy")
                        .source("file:///Volumes/Macintosh%20HD/Users/ozan/dev/rondo/rondo-groovy-script/target/rondo-groovy-script-0.0.1-SNAPSHOT.jar")
                        .state("INSTALLED")
                        .symbolicName("fr.liglab.adele.rondo.groovy-script"))

                .resource(Bundle.class, "groovy").dependsOn(Bundle.class, "event admin")
                .resource(Bundle.class, "config admin").dependsOn(Bundle.class,"file install")
                .resource(Bundle.class,"event admin").dependsOn(Bundle.class,"config admin")
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        System.out.println("DEPLOYMENT PLAN: " + deploymentHandle.getPlan().toString());
        deploymentHandle.apply();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());

        org.osgi.framework.Bundle fileInstall = osgiHelper.getBundle("org.apache.felix.fileinstall");
        org.osgi.framework.Bundle groovy = osgiHelper.getBundle("fr.liglab.adele.rondo.groovy-script");

        assertThat(fileInstall).isNotNull();
        assertThat(fileInstall.getState()).isEqualTo(org.osgi.framework.Bundle.ACTIVE);
        assertThat(fileInstall.getLocation()).isEqualTo("mvn:org.apache.felix/org.apache.felix.fileinstall/LATEST");

        assertThat(groovy).isNotNull();
        assertThat(groovy.getState()).isEqualTo(org.osgi.framework.Bundle.INSTALLED);
        assertThat(groovy.getLocation()).isEqualTo("file:///Volumes/Macintosh%20HD/Users/ozan/dev/rondo/rondo-groovy-script/target/rondo-groovy-script-0.0.1-SNAPSHOT.jar");


        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);
        System.out.flush();
    }

    @Test
    public void testCheckSumError(){
        Infrastructure inf = infrastructure()

                .resource(bundle("config admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.configadmin-1.6.0.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.configadmin")
                        .version("1.6.0")
                        .with("checksum").setto("d632c7c4146d764954fdac7c41b597ffa78e3671"))   //d632c7c4146d764954fdac7c41b597ffa78e3672

                .resource(bundle("file install")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))

                .resource(bundle("event admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.eventadmin")
                        .version("1.3.2"))

                .resource(bundle("groovy")
                        .source("file:///Volumes/Macintosh%20HD/Users/ozan/dev/rondo/rondo-groovy-script/target/rondo-groovy-script-0.0.1-SNAPSHOT.jar")
                        .state("INSTALLED")
                        .symbolicName("fr.liglab.adele.rondo.groovy-script"))

                .resource(Bundle.class, "groovy").dependsOn(Bundle.class, "event admin")
                .resource(Bundle.class, "config admin").dependsOn(Bundle.class,"file install")
                .resource(Bundle.class,"event admin").dependsOn(Bundle.class,"config admin")
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        System.out.println("DEPLOYMENT PLAN: " + deploymentHandle.getPlan().toString());
        deploymentHandle.apply();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        assertThat(deploymentHandle.getState()).isEqualTo((DeploymentHandle.DeploymentState.UNSUCCESSFUL));

        System.out.flush();
    }

    @Test
    public void testExpectedResourceNotAttainedError(){
        Infrastructure inf = infrastructure()

                .resource(bundle("config admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.configadmin-1.6.0.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.configadmin")
                        .version("1.6.0")
                        .with("checksum").setto("d632c7c4146d764954fdac7c41b597ffa78e3672"))   //d632c7c4146d764954fdac7c41b597ffa78e3672

                .resource(bundle("file install")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))

                .resource(bundle("event admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.eventadmin")
                        .version("1.3.2"))

                .resource(bundle("groovy")
                        .source("file:///Volumes/Macintosh%20HD/Users/ozan/dev/rondo/rondo-groovy-script/target/rondo-groovy-script-0.0.1-SNAPSHOT.jar")
                        .state("RESOLVED")
                        .symbolicName("fr.liglab.adele.rondo.groovy-script"))

                .resource(Bundle.class, "groovy").dependsOn(Bundle.class, "event admin")
                .resource(Bundle.class, "config admin").dependsOn(Bundle.class,"file install")
                .resource(Bundle.class,"event admin").dependsOn(Bundle.class,"config admin")
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        System.out.println("DEPLOYMENT PLAN: " + deploymentHandle.getPlan().toString());
        deploymentHandle.apply();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        assertThat(deploymentHandle.getState()).isEqualTo((DeploymentHandle.DeploymentState.UNSUCCESSFUL));

        System.out.flush();
    }

    @Test
    public void testResourceDownloadError(){
        Infrastructure inf = infrastructure()

                .resource(bundle("config admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.configadmin-1.6.0.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.configadmin")
                        .version("1.6.0")
                        .with("checksum").setto("d632c7c4146d764954fdac7c41b597ffa78e3672"))   //d632c7c4146d764954fdac7c41b597ffa78e3672

                .resource(bundle("file install")
                        .source("http://apache.opensourcamirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                                // http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))

                .resource(bundle("event admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.eventadmin")
                        .version("1.3.2"))

                .resource(bundle("groovy")
                        .source("file:///Volumes/Macintosh%20HD/Users/ozan/dev/rondo/rondo-groovy-script/target/rondo-groovy-script-0.0.1-SNAPSHOT.jar")
                        .state("RESOLVED")
                        .symbolicName("fr.liglab.adele.rondo.groovy-script"))

                .resource(Bundle.class, "groovy").dependsOn(Bundle.class, "event admin")
                .resource(Bundle.class, "config admin").dependsOn(Bundle.class,"file install")
                .resource(Bundle.class,"event admin").dependsOn(Bundle.class,"config admin")
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        System.out.println("DEPLOYMENT PLAN: " + deploymentHandle.getPlan().toString());
        deploymentHandle.apply();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        assertThat(deploymentHandle.getState()).isEqualTo((DeploymentHandle.DeploymentState.UNSUCCESSFUL));

        System.out.flush();
    }

    @Test
    public void testMalformedUrlError(){
        Infrastructure inf = infrastructure()

                .resource(bundle("config admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.configadmin-1.6.0.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.configadmin")
                        .version("1.6.0")
                        .with("checksum").setto("d632c7c4146d764954fdac7c41b597ffa78e3672"))   //d632c7c4146d764954fdac7c41b597ffa78e3672

                .resource(bundle("file install")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))

                .resource(bundle("event admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.eventadmin")
                        .version("1.3.2"))

                .resource(bundle("groovy")
                        .source("filt:///Volumes/Macintosh%20HD/Users/ozan/dev/rondo/rondo-groovy-script/target/rondo-groovy-script-0.0.1-SNAPSHOT.jar")
                        .state("RESOLVED")
                        .symbolicName("fr.liglab.adele.rondo.groovy-script"))

                .resource(Bundle.class, "groovy").dependsOn(Bundle.class, "event admin")
                .resource(Bundle.class, "config admin").dependsOn(Bundle.class, "file install")
                .resource(Bundle.class, "event admin").dependsOn(Bundle.class, "config admin")
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        System.out.println("DEPLOYMENT PLAN: " + deploymentHandle.getPlan().toString());
        deploymentHandle.apply();
        System.out.println("DEPLOYMENT STATE: " + deploymentHandle.getState().toString());
        assertThat(deploymentHandle.getState()).isEqualTo((DeploymentHandle.DeploymentState.UNSUCCESSFUL));

        System.out.flush();
    }



}
