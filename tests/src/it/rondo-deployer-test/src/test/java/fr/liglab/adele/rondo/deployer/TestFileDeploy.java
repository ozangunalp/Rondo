package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import fr.liglab.adele.rondo.infra.model.Infrastructure;
import org.junit.Test;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.ServiceRegistration;

import java.io.File;
import java.io.IOException;

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
    public void testFileDeploy() throws IOException {
        File file = new File("target/classes/testFile.txt");

        Infrastructure inf = infrastructure()

                .resource(file("a file")
                        .source("file://"+file.getCanonicalPath())
                        .path("target/testFile.txt")
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
    public void testFilePermissions() throws IOException {
        File file = new File("target/classes/testExecutable.sh");

        Infrastructure inf = infrastructure()

                .resource(file("a file")
                        .source("file://" + file.getCanonicalPath())
                        .path("target/testExecutable.sh")
                        .executable(true)
                        .writable(true))
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();
        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.SUCCESSFUL);
        File target = new File("target/testExecutable.sh");
        assertThat(target).exists();
        assertThat(target).isFile();
        assertThat(target.canExecute()).isTrue();
        assertThat(target.canWrite()).isTrue();

        System.out.flush();
    }

    @Test
    public void testFileSourceNotFound() throws IOException {
        File file = new File("target/classes/testFile.txt");
        Infrastructure inf = infrastructure()

                .resource(file("a file")
                        .source("file://"+file.getCanonicalPath())
                        .path("target/testFile.txt")
                        .executable(false)
                        .writable(false))

                .resource(file("a second file")
                        .source("file://target/test.sh")  // wrong path
                        .path("target/testExecutable.sh")
                        .executable(true)
                        .writable(true))

                .resource(fr.liglab.adele.rondo.infra.model.File.class,"a second file")
                    .dependsOn(fr.liglab.adele.rondo.infra.model.File.class,"a file");
                ;

        ServiceRegistration<Infrastructure> registration = osgiHelper.getContext().registerService(Infrastructure.class, inf, null);

        DeploymentHandle deploymentHandle = deployer.getDeploymentHandle();
        assertThat(deploymentHandle).isNotNull();
        deploymentHandle.apply();

        assertThat(deploymentHandle.getState()).isEqualTo(DeploymentHandle.DeploymentState.UNSUCCESSFUL);

        System.out.flush();
    }

}
