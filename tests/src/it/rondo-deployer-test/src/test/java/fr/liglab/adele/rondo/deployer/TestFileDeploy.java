package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import org.junit.Test;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.ServiceRegistration;

import static fr.liglab.adele.rondo.infra.impl.FileImpl.file;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/12/13
 * Time: 8:40 AM
 */
@ExamReactorStrategy(PerMethod.class)
public class TestFileDeploy extends RondoDeployerTest{

    @Test
    public void testFileDeploy(){
        Infrastructure inf = infrastructure()

                .resource(file("a file")
                        .source("file:///Users/ozan/Desktop/Untitled.tiff")
                        .path("file:///Users/ozan/Desktop/Untitled.jpeg")
                        .executable(false)
                        .writable(false))

//                .resource(file("a second file")
//                        .source("file:///Users/ozan/Desktop/script.txt")
//                        .path("file:///Users/ozan/Desktop/script.sh")
//                        .executable(true)
//                        .writable(true))
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();

        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);

        System.out.flush();
    }

    @Test
    public void testFileSourceNotFound(){
        Infrastructure inf = infrastructure()

                .resource(file("a file")
                        .source("file:///Users/ozan/Desktop/Untitled.tiff")
                        .path("file:///Users/ozan/Desktop/Untitled.jpeg")
                        .executable(false)
                        .writable(false))

                .resource(file("a second file")
                        .source("file:///Users/ozan/Desktop/script.txt")
                        .path("file:///Users/ozan/Desktop/script.sh")
                        .executable(true)
                        .writable(true))
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();

        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.UNSUCCESSFUL);

        System.out.flush();
    }

}
