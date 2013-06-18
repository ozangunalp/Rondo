package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import fr.liglab.adele.rondo.infra.test.services.FooService;
import org.apache.felix.ipojo.ComponentInstance;
import org.apache.felix.ipojo.architecture.Architecture;
import org.junit.Test;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

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
                        .name(null)
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
        System.out.println(architectureByName.getInstanceDescription().getState());

        Architecture secondArch = ipojoHelper.getArchitectureByName("a-second-instance");
        assertThat(secondArch).isNotNull();
        System.out.println(secondArch.getInstanceDescription().getState());

        ServiceReference[] serviceReferences = osgiHelper.getServiceReferences(FooService.class, null);
        for (ServiceReference serviceReference : serviceReferences) {
            for (String key : serviceReference.getPropertyKeys()) {
                System.out.println(key+" "+serviceReference.getProperty(key));
            }
        }

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
                )

                ;

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
                )

                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.UNSUCCESSFUL);

        System.out.flush();
    }
}
