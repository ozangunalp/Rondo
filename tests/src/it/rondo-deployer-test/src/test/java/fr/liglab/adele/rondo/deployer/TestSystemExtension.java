package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import org.junit.Test;
import org.ops4j.pax.exam.options.CompositeOption;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.ServiceRegistration;

import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static fr.liglab.adele.rondo.infra.system.SystemImpl.system;
import static org.fest.assertions.Assertions.assertThat;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/13/13
 * Time: 4:43 PM
 */
@ExamReactorStrategy(PerMethod.class)
public class TestSystemExtension extends RondoDeployerTest {

    @Override
    public CompositeOption rondoBundles() {
        return new DefaultCompositeOption(
                mavenBundle("commons-io","commons-io").versionAsInProject(),
                mavenBundle("fr.liglab.adele.rondo", "rondo-core").versionAsInProject(),
                mavenBundle("fr.liglab.adele.rondo", "rondo-deployer").versionAsInProject(),
                mavenBundle("fr.liglab.adele.rondo", "rondo-system-extension").versionAsInProject()
        );
    }

    @Test
    public void testSystemExtension(){

        Infrastructure inf = infrastructure()
                .resource(system()
                        .with("ozan.system.property").setto("VALUE")
                        .with("everest.processing.synchronous").setto("true")
                );

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        assertThat(System.getProperty("everest.processing.synchronous")).isEqualTo("true");
        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);

        assertThat(System.getProperty("ozan.system.property")).isEqualTo("VALUE");
        assertThat(System.getProperty("everest.processing.synchronous")).isEqualTo("true");

        System.out.flush();

    }
}
