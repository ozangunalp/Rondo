package fr.liglab.adele.rondo.infra.deployment;

import fr.liglab.adele.rondo.infra.deployment.impl.DeploymentHandleImpl;
import fr.liglab.adele.rondo.infra.deployment.impl.InfrastructureDeployer;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.impl.*;
import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;
import fr.liglab.adele.rondo.infra.model.*;
import org.apache.felix.ipojo.everest.services.EverestService;
import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;
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
                        bundle().name("file install")
                                .source("http://apache.crihan.fr/dist//felix/org.apache.felix.fileinstall-3.2.6.jar")
                                .state("active")
                                .symbolicName("org.apache.felix.fileinstall")
                                .version("3.2.5"))

//                .resource(
//                        file().name("file")
//                                .source("some other url")
//                                .state("present")
//                                .template("template url"))
//
//                .resource(
//                        configuration().name("conf name"))
                .resource(
                        bundle().name("everest-core")
                                .source("file:/Volumes/Macintosh%20HD/Users/ozan/Downloads/felix-framework-4.2.1/bundle/everest-core-1.0-SNAPSHOT.jar")
                                .state("active")
                                .symbolicName("org.apache.felix.ipojo.everest-core")
                                .version("1.0.0.SNAPSHOT"))
//                .resource(
//                        aPackage("package1")
//                                .with("version").setto(list(1, 3, 4))
//                                .with("adsf").setto(list().with(1).with(3)))
//                .resource(
//                        service("service")
//                                .state("published")
//                )
                ;


//        inf.resource(Bundle.class, "file install").dependsOn(Package.class, "package1")
//                .resource(Configuration.class, "conf name").dependsOn(File.class, "file").dependsOn(Bundle.class, "log").dependsOn(Bundle.class, "file install")
//                .resource(Configuration.class, "conf name").dependsOn(Bundle.class, "log");
//        //      .resource(File.class,"file").dependsOn(Configuration.class,"conf name");

        inf.resource(Bundle.class, "file install").dependsOn(Bundle.class, "everest-core");

        System.out.println("Resources:");
        System.out.println("------");
        for (ResourceReference ref : inf.getResourceReferences()) {
            System.out.println(ref);
        }
        System.out.println("");

        System.out.println("Resources:");
        System.out.println("------");
        for (ResourceDeclaration res : inf.getResources()) {
            System.out.println(res);
        }
        System.out.println("");

        System.out.println("Dependencies:");
        System.out.println("------");
        for (Dependency d : inf.getDependencies()) {
            System.out.println(d);
        }
        System.out.println("");
        System.out.println("Deployment Plan:");
        System.out.println("------");

        EverestService everestService = mock(EverestService.class, RETURNS_MOCKS);

        BundleContext context = mock(BundleContext.class);
        ServiceReference<Infrastructure> reference = mock(ServiceReference.class);
        when(context.getService(reference)).thenReturn(inf);

        // mocking processors
        ServiceReference<ResourceProcessor> bundleProcessor = mock(ServiceReference.class, RETURNS_MOCKS);
        when(bundleProcessor.getProperty(anyString())).thenReturn("fr.liglab.adele.rondo.infra.model.Bundle");
        BundleProcessor bundleProcessor1 = new BundleProcessor(context);
        bundleProcessor1.m_everest = everestService;
        when(context.getService(bundleProcessor)).thenReturn(bundleProcessor1);

        ServiceReference<ResourceProcessor> instanceProcessor = mock(ServiceReference.class, RETURNS_MOCKS);
        when(instanceProcessor.getProperty(anyString())).thenReturn("fr.liglab.adele.rondo.infra.model.Instance");
        InstanceProcessor instanceProcessor1 = new InstanceProcessor(context);
        instanceProcessor1.m_everest = everestService;
        when(context.getService(instanceProcessor)).thenReturn(instanceProcessor1);

        ServiceReference<ResourceProcessor> packageProcessor = mock(ServiceReference.class, RETURNS_MOCKS);
        when(packageProcessor.getProperty(anyString())).thenReturn("fr.liglab.adele.rondo.infra.model.Package");
        PackageProcessor packageProcessor1 = new PackageProcessor(context);
        packageProcessor1.m_everest = everestService;
        when(context.getService(packageProcessor)).thenReturn(packageProcessor1);

        ServiceReference<ResourceProcessor> serviceProcessor = mock(ServiceReference.class, RETURNS_MOCKS);
        when(serviceProcessor.getProperty(anyString())).thenReturn("fr.liglab.adele.rondo.infra.model.Service");
        ServiceProcessor serviceProcessor1 = new ServiceProcessor(context);
        serviceProcessor1.m_everest = everestService;
        when(context.getService(serviceProcessor)).thenReturn(serviceProcessor1);

        ServiceReference<ResourceProcessor> fileProcessor = mock(ServiceReference.class, RETURNS_MOCKS);
        when(fileProcessor.getProperty(anyString())).thenReturn("fr.liglab.adele.rondo.infra.model.File");
        FileProcessor fileProcessor1 = new FileProcessor(context);
        fileProcessor1.m_everest = everestService;
        when(context.getService(fileProcessor)).thenReturn(fileProcessor1);

        ServiceReference<ResourceProcessor> confProcessor = mock(ServiceReference.class, RETURNS_MOCKS);
        when(confProcessor.getProperty(anyString())).thenReturn("fr.liglab.adele.rondo.infra.model.Configuration");
        ConfigProcessor configProcessor = new ConfigProcessor(context);
        configProcessor.m_everest = everestService;
        when(context.getService(confProcessor)).thenReturn(configProcessor);

        InfrastructureDeployer deployer = new InfrastructureDeployer(context);
        deployer.bindResourceProcessor(bundleProcessor);
        deployer.bindResourceProcessor(instanceProcessor);
        deployer.bindResourceProcessor(packageProcessor);
        deployer.bindResourceProcessor(serviceProcessor);
        deployer.bindResourceProcessor(fileProcessor);
        deployer.bindResourceProcessor(confProcessor);

        DeploymentPlan dp = null;
        try {
            deployer.bindInfrastructure(reference);
            dp = deployer.calculateDeploymentPlan(inf);
        } catch (DependencyResolutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        for (ResourceReference res : dp) {
            System.out.println(res.toString());
        }
        DeploymentHandle deploymentHandle = new DeploymentHandleImpl(dp, deployer);
        deploymentHandle.apply();


    }
}
