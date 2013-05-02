package fr.liglab.adele.rondo.infra.deployer;

import fr.liglab.adele.rondo.infra.deployer.impl.DeploymentHandleImpl;
import fr.liglab.adele.rondo.infra.deployer.impl.InfrastructureDeployer;
import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;
import fr.liglab.adele.rondo.infra.model.*;
import fr.liglab.adele.rondo.infra.model.Package;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.List;
import java.util.Map;

import static fr.liglab.adele.rondo.infra.impl.AbstractResource.list;
import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.ConfigurationImpl.configuration;
import static fr.liglab.adele.rondo.infra.impl.FileImpl.file;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
import static fr.liglab.adele.rondo.infra.impl.PackageImpl.aPackage;
import static fr.liglab.adele.rondo.infra.impl.ServiceImpl.service;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 3:29 PM
 */
public class TestDeploymentPlan {

    @Test
    public void testDeploymentPlan() {

        InfrastructureImpl inf = infrastructure()
                .resource(
                        bundle().name("bundle1")
                                .source("some url")
                                .state("resolved")
                                .with("symbolic-name").setto("org.core.osgi")
                                .with("version").setto("4.3.1"))
                .resource(
                        file().name("file")
                                .source("some other url")
                                .state("present")
                                .template("template url"))

                .resource(
                        configuration().name("conf name"))
                .resource(
                        bundle().name("bundle2")
                                .source("some url2")
                                .state("active")
                                .with("symbolic-name").setto("asdgasdg")
                                .with("version").setto("1.2.4"))
                .resource(
                        aPackage("package1")
                                .with("version").setto(list(1, 3, 4))
                                .with("adsf").setto(list().with(1).with(3)))
                .resource(
                        service("service")
                                .state("published")
                );


        inf.resource(Bundle.class, "bundle1").dependsOn(Package.class, "package1")
                .resource(Configuration.class, "conf name").dependsOn(File.class, "file").dependsOn(Bundle.class, "bundle2").dependsOn(Bundle.class, "bundle1")
                .resource(Configuration.class, "conf name").dependsOn(Bundle.class, "bundle2")
        //      .resource(File.class,"file").dependsOn(Configuration.class,"conf name");
        ;
        System.out.println("Resources:");
        System.out.println("------");
        for (ResourceReference ref : inf.getResourceReferences()) {
            System.out.println(ref);
        }
        System.out.println("");

        System.out.println("Resources:");
        System.out.println("------");
        for (Resource res : inf.getResources()) {
            System.out.println(res);
        }
        System.out.println("");

        System.out.println("Dependencies:");
        System.out.println("------");
        for (Dependency d : inf.getDependencies()) {
            System.out.println(d);
        }
        System.out.println("");
        System.out.println("Deployment:");
        System.out.println("------");
        mock(BundleContext.class, RETURNS_MOCKS);
        BundleContext context = mock(BundleContext.class);
        ServiceReference<Infrastructure> reference = mock(ServiceReference.class);
        when(context.getService(any(ServiceReference.class))).thenReturn(inf);

        InfrastructureDeployer deployer = new InfrastructureDeployer(context);
        try {
            deployer.bindInfrastructure(reference);
            DeploymentPlan dp = deployer.calculateDeploymentPlan(inf);

            for (Map.Entry<Integer, List<ResourceReference>> e : dp.entrySet()) {
                System.out.println(e.getKey());
                System.out.println(e.getValue());
            }
            DeploymentHandle deploymentHandle = new DeploymentHandleImpl(dp, deployer);
            deploymentHandle.apply();
        } catch (DependencyResolutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
}
