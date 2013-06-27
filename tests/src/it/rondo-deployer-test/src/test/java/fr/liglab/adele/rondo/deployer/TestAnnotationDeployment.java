package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import org.junit.Test;
import org.ops4j.pax.exam.options.CompositeOption;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.Bundle;
import org.ow2.chameleon.testing.helpers.TimeUtils;

import static org.fest.assertions.Assertions.assertThat;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/14/13
 * Time: 5:03 PM
 */
@ExamReactorStrategy(PerMethod.class)
public class TestAnnotationDeployment extends RondoDeployerTest {

    @Override
    public CompositeOption rondoBundles() {
        return new DefaultCompositeOption(
                mavenBundle("commons-io","commons-io").versionAsInProject(),
                mavenBundle("fr.liglab.adele.rondo", "rondo-core").versionAsInProject(),
                mavenBundle("fr.liglab.adele.rondo", "recursive-resolver").versionAsInProject(),
                mavenBundle("fr.liglab.adele.rondo", "rondo-deployer").versionAsInProject(),
                mavenBundle("fr.liglab.adele.rondo", "rondo-infrastructure-example").versionAsInProject()
        );
    }

    @Test
    public void testAnnotationDeployment(){

        Infrastructure infrastructure = osgiHelper.getServiceObject(Infrastructure.class);
        assertThat(infrastructure).isNotNull();
        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();

        TimeUtils.grace(2000);

        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);
        System.out.println(deploymentHandle.getState());

        System.out.flush();

    }

}
