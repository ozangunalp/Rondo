package fr.liglab.adele.rondo.deployer;

import fr.liglab.adele.rondo.infra.deployment.ManagedInfrastructure;
import org.apache.felix.ipojo.everest.services.EverestService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.CompositeOption;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.Bundle;
import org.ow2.chameleon.testing.helpers.BaseTest;


import javax.inject.Inject;

import static org.fest.assertions.Assertions.assertThat;
import static org.ops4j.pax.exam.CoreOptions.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/3/13
 * Time: 3:44 PM
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class RondoDeployerTest extends BaseTest{

    @Inject
    EverestService everest;

    @Inject
    ManagedInfrastructure deployer;

    @Override
    protected Option[] getCustomOptions() {
        return options(
                systemProperty("ipojo.processing.synchronous").value("true"),
                systemProperty("everest.processing.synchronous").value("true"),
                eventAdmin(),
                everestBundles(),
                //log(),
                rondoBundles(),
                festBundles()
        );
    }

    @Override
    public boolean deployConfigAdmin() {
        return true;
    }

    @Override
    public boolean deployTestBundle() {
        return true;
    }

    @Before
    public void commonSetUp() {
        super.commonSetUp();
    }

    @After
    public void commonTearDown() {
        ipojoHelper.dispose();
        osgiHelper.dispose();
    }

    public CompositeOption everestBundles() {
        return new DefaultCompositeOption(
                mavenBundle("org.apache.felix.ipojo", "everest-core").versionAsInProject(),
                mavenBundle("org.apache.felix.ipojo", "everest-ipojo").versionAsInProject(),
                mavenBundle("org.apache.felix.ipojo", "everest-osgi").versionAsInProject(),
                mavenBundle("org.apache.felix.ipojo", "everest-system").versionAsInProject()
        );
    }

    public CompositeOption log() {
        return new DefaultCompositeOption(
                mavenBundle("org.apache.felix", "org.apache.felix.log").versionAsInProject()
        );
    }

    public CompositeOption eventAdmin() {
        return new DefaultCompositeOption(
                mavenBundle("org.apache.felix", "org.apache.felix.eventadmin").versionAsInProject()
        );
    }

    // Wrap fest-util and fest-assert into a bundle so we can test with happiness.
    public CompositeOption festBundles() {
        return new DefaultCompositeOption(
                wrappedBundle(mavenBundle("org.easytesting", "fest-util").versionAsInProject()),
                wrappedBundle(mavenBundle("org.easytesting", "fest-assert").versionAsInProject())
        );
    }

    public CompositeOption rondoBundles() {
        return new DefaultCompositeOption(
                mavenBundle("commons-io","commons-io").versionAsInProject(),
                mavenBundle("fr.liglab.adele.rondo", "rondo-core").versionAsInProject(),
                mavenBundle("fr.liglab.adele.rondo", "rondo-deployer").versionAsInProject()
        );
    }


    /**
     * Check that the EverestService and Deployer service are present.
     * <p>
     * This test also avoids the test container to complain with a "no test method" error.
     * </p>
     */
    @Test
    public void testEverestServiceIsPresent() {
        assertThat(everest).isNotNull();
        assertThat(deployer).isNotNull();
    }


}
