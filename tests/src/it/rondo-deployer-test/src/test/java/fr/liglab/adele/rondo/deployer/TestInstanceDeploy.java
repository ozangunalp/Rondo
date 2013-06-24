package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import fr.liglab.adele.rondo.infra.test.services.BarService;
import fr.liglab.adele.rondo.infra.test.services.FooService;
import org.apache.felix.ipojo.ComponentInstance;
import org.apache.felix.ipojo.architecture.Architecture;
import org.apache.felix.ipojo.metadata.Element;
import org.junit.Test;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static fr.liglab.adele.rondo.infra.impl.InstanceImpl.instance;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/12/13
 * Time: 8:39 AM
 */
@ExamReactorStrategy(PerMethod.class)
public class TestInstanceDeploy extends RondoDeployerTest{

    @Test
    public void testOneInstanceDeploy(){
        Infrastructure inf = infrastructure()
                .resource(instance("an-instance")
                        .factory("Foo")
                        .factoryVersion("1.2.3.foo")
                        .with("fooPrefix").setto("ozan")
                        .state("valid"));

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);

        Architecture architectureByName = ipojoHelper.getArchitectureByName("an-instance");
        assertThat(architectureByName).isNotNull();
        assertThat(architectureByName.getInstanceDescription().getState()).isEqualTo(ComponentInstance.VALID);
        Element[] handlers = architectureByName.getInstanceDescription().getDescription().getElements("handler");
        Element properties=null;
        for (Element handler : handlers) {
            if(handler.getAttribute("name").equals("org.apache.felix.ipojo:properties")){
                properties = handler;
            }
        }
        assertThat(properties).isNotNull();
        for (Element property : properties.getElements("property")) {
            assertThat(property.getAttribute("name")).isEqualTo("fooPrefix");
            assertThat(property.getAttribute("value")).isEqualTo("ozan");
        }
        System.out.flush();
    }


    @Test
    public void testInstanceReconfigure(){

        ComponentInstance foo = ipojoHelper.createComponentInstance("Foo","an-instance");
        foo.start();
        Infrastructure inf = infrastructure()
                .resource(instance("an-instance")
                        .factory("Foo")
                        .factoryVersion("1.2.3.foo")
                        .with("fooPrefix").setto("ozan")
                        .state("valid")
                );

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);

        Architecture architectureByName = ipojoHelper.getArchitectureByName("an-instance");
        assertThat(architectureByName).isNotNull();
        assertThat(architectureByName.getInstanceDescription().getState()).isEqualTo(ComponentInstance.VALID);
        Element[] handlers = architectureByName.getInstanceDescription().getDescription().getElements("handler");
        Element properties=null;
        for (Element handler : handlers) {
            if(handler.getAttribute("name").equals("org.apache.felix.ipojo:properties")){
                properties = handler;
            }
        }
        assertThat(properties).isNotNull();
        for (Element property : properties.getElements("property")) {
            assertThat(property.getAttribute("name")).isEqualTo("fooPrefix");
            assertThat(property.getAttribute("value")).isEqualTo("ozan");
        }

        System.out.flush();
    }


    @Test
    public void testInstanceWithoutName(){
        Infrastructure inf = infrastructure()
                .resource(instance("an-instance")
                        .name(null)
                        .factory("Foo")
                        .factoryVersion("1.2.3.foo")
                        .with("fooPrefix").setto("ozan")
                        .state("valid"));

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);

        Architecture architectureByName = ipojoHelper.getArchitectureByName("Foo-0");
        assertThat(architectureByName).isNotNull();
        assertThat(architectureByName.getInstanceDescription().getState()).isEqualTo(ComponentInstance.VALID);

        System.out.flush();
    }



    @Test
    public void testInstancesDeploy(){
        Infrastructure inf = infrastructure()
                .resource(instance("an-instance")
                        .factory("Foo")
                        .factoryVersion("1.2.3.foo")
                        .with("fooPrefix").setto("ozan")
                        .state("valid"))
                .resource(instance("a-second-instance")
                        .factory("org.apache.felix.ipojo.everest.ipojo.test.b1.BarProviderImpl")
                        .with("barPrefix").setto("kuku")
                        .state("valid"))
                .resource(instance("ooo")
                        .name("foo-0")
                        .factory("Foo")
                        .with("fooPrefix").setto("kuku")
                        .state("valid"));

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);

        Architecture architectureByName = ipojoHelper.getArchitectureByName("an-instance");
        assertThat(architectureByName).isNotNull();
        assertThat(architectureByName.getInstanceDescription().getState()).isEqualTo(ComponentInstance.VALID);
        Element[] handlers = architectureByName.getInstanceDescription().getDescription().getElements("handler");
        Element properties=null;
        for (Element handler : handlers) {
            if(handler.getAttribute("name").equals("org.apache.felix.ipojo:properties")){
                properties = handler;
            }
        }
        assertThat(properties).isNotNull();
        for (Element property : properties.getElements("property")) {
            assertThat(property.getAttribute("name")).isEqualTo("fooPrefix");
            assertThat(property.getAttribute("value")).isEqualTo("ozan");
        }


        Architecture secondArch = ipojoHelper.getArchitectureByName("a-second-instance");
        assertThat(secondArch).isNotNull();
        assertThat(secondArch.getInstanceDescription().getState()).isEqualTo(ComponentInstance.VALID);
        handlers = secondArch.getInstanceDescription().getDescription().getElements("handler");
        properties=null;
        for (Element handler : handlers) {
            if(handler.getAttribute("name").equals("org.apache.felix.ipojo:properties")){
                properties = handler;
            }
        }
        assertThat(properties).isNotNull();
        for (Element property : properties.getElements("property")) {
            assertThat(property.getAttribute("name")).isEqualTo("barPrefix");
            assertThat(property.getAttribute("value")).isEqualTo("kuku");
        }


        Architecture thirdArch = ipojoHelper.getArchitectureByName("foo-0");
        assertThat(thirdArch).isNotNull();
        assertThat(thirdArch.getInstanceDescription().getState()).isEqualTo(ComponentInstance.VALID);
        handlers = thirdArch.getInstanceDescription().getDescription().getElements("handler");
        properties=null;
        for (Element handler : handlers) {
            if(handler.getAttribute("name").equals("org.apache.felix.ipojo:properties")){
                properties = handler;
            }
        }
        assertThat(properties).isNotNull();
        for (Element property : properties.getElements("property")) {
            assertThat(property.getAttribute("name")).isEqualTo("fooPrefix");
            assertThat(property.getAttribute("value")).isEqualTo("kuku");
        }

        ServiceReference[] fooReferences = osgiHelper.getServiceReferences(FooService.class, null);
        assertThat(fooReferences.length).isEqualTo(2);
        ServiceReference[] barReferences = osgiHelper.getServiceReferences(BarService.class, null);
        assertThat(barReferences.length).isEqualTo(1);

        System.out.flush();
    }

    @Test
    public void testErrorDeploy(){
        Infrastructure inf = infrastructure()
                .resource(instance("an-instance")
                        .factory("Foo")
                        .factoryVersion("1.2.3.foo")
                        .with("fooPrefix").setto("ozan")
                        .with("fooCounter").setto(6)
                        .state("valid")
                );

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.UNSUCCESSFUL);

        ComponentInstance instanceByName = ipojoHelper.getInstanceByName("an-instance");
        assertThat(instanceByName).isNull();

        System.out.flush();
    }

    @Test
    public void testFactoryNotFound(){
        Infrastructure inf = infrastructure()
                .resource(instance("an-instance")
                        .factory("Foos")
                        .factoryVersion("1.2.3.foo")
                        .with("fooPrefix").setto("ozan")
                        .with("fooCounter").setto(6)
                        .state("valid")
                );

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.UNSUCCESSFUL);

        System.out.flush();
    }
}
