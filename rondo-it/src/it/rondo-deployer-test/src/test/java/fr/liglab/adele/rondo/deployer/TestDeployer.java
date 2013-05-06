package fr.liglab.adele.rondo.deployer;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.apache.felix.ipojo.everest.services.EverestService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.CompositeOption;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.ow2.chameleon.testing.helpers.IPOJOHelper;
import org.ow2.chameleon.testing.helpers.OSGiHelper;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.net.MalformedURLException;

import static junit.framework.Assert.assertNotNull;
import static org.ops4j.pax.exam.CoreOptions.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/3/13
 * Time: 3:44 PM
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class TestDeployer {

    @Inject
    BundleContext bc;

    @Inject
    EverestService everest;

    OSGiHelper osgiHelper;
    IPOJOHelper ipojoHelper;

    @Configuration
    public Option[] config() throws MalformedURLException {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);

        return options(
                ipojoBundles(),
                everestBundles(),
                junitBundles(),
                festBundles(),
                rondoBundles(),
                systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("WARN")
        );
    }

    @Before
    public void commonSetUp() {
        osgiHelper = new OSGiHelper(bc);
        ipojoHelper = new IPOJOHelper(bc);

        // Dump OSGi Framework information
        String vendor = (String) osgiHelper.getBundle(0).getHeaders().get(Constants.BUNDLE_VENDOR);
        if (vendor == null) {
            vendor = (String) osgiHelper.getBundle(0).getHeaders().get(Constants.BUNDLE_SYMBOLICNAME);
        }
        String version = (String) osgiHelper.getBundle(0).getHeaders().get(Constants.BUNDLE_VERSION);
        System.out.println("OSGi Framework : " + vendor + " - " + version);

        waitForStability(bc);
    }

    @After
    public void commonTearDown() {
        ipojoHelper.dispose();
        osgiHelper.dispose();
    }

    public CompositeOption ipojoBundles() {
        return new DefaultCompositeOption(
                mavenBundle("org.apache.felix", "org.apache.felix.ipojo").versionAsInProject(),
                mavenBundle("org.ow2.chameleon.testing", "osgi-helpers").versionAsInProject(),
                mavenBundle("org.apache.felix", "org.apache.felix.configadmin").versionAsInProject()
        );
    }

    public CompositeOption everestBundles() {
        return new DefaultCompositeOption(
                mavenBundle("org.apache.felix.ipojo", "everest-core").versionAsInProject(),
                mavenBundle("org.apache.felix.ipojo", "everest-osgi").versionAsInProject()
        );
    }

    // Wrap fest-util and fest-assert into a bundle so we can test with happiness.
    public CompositeOption festBundles() {
        return new DefaultCompositeOption(
                wrappedBundle(mavenBundle("org.easytesting", "fest-util").versionAsInProject()),
                wrappedBundle(mavenBundle("org.easytesting", "fest-assert").versionAsInProject())
        );
    }

    private CompositeOption rondoBundles() {
        return new DefaultCompositeOption(
                mavenBundle("fr.liglab.adele.rondo", "rondo-infrastructure-model").versionAsInProject(),
                mavenBundle("fr.liglab.adele.rondo", "rondo-infrastructure-deployer").versionAsInProject()
        );
    }

    /**
     * Waits for stability:
     * <ul>
     * <li>all bundles are activated
     * <li>service count is stable
     * </ul>
     * If the stability can't be reached after a specified time,
     * the method throws a {@link IllegalStateException}.
     *
     * @param context the bundle context
     * @throws IllegalStateException when the stability can't be reach after a several attempts.
     */
    private void waitForStability(BundleContext context) throws IllegalStateException {
        // Wait for bundle initialization.
        boolean bundleStability = getBundleStability(context);
        int count = 0;
        while (!bundleStability && count < 500) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                // Interrupted
            }
            count++;
            bundleStability = getBundleStability(context);
        }

        if (count == 500) {
            System.err.println("Bundle stability isn't reached after 500 tries");
            throw new IllegalStateException("Cannot reach the bundle stability");
        }

        boolean serviceStability = false;
        count = 0;
        int count1 = 0;
        int count2 = 0;
        while (!serviceStability && count < 500) {
            try {
                ServiceReference[] refs = context.getServiceReferences((String) null, null);
                count1 = refs.length;
                Thread.sleep(500);
                refs = context.getServiceReferences((String) null, null);
                count2 = refs.length;
                serviceStability = count1 == count2;
            } catch (Exception e) {
                System.err.println(e);
                serviceStability = false;
                // Nothing to do, while recheck the condition
            }
            count++;
        }

        if (count == 500) {
            System.err.println("Service stability isn't reached after 500 tries (" + count1 + " != " + count2);
            throw new IllegalStateException("Cannot reach the service stability");
        }
    }

    /**
     * Are bundle stables.
     *
     * @param bc the bundle context
     * @return <code>true</code> if every bundles are activated.
     */
    private boolean getBundleStability(BundleContext bc) {
        boolean stability = true;
        Bundle[] bundles = bc.getBundles();
        for (int i = 0; i < bundles.length; i++) {
            stability = stability && (bundles[i].getState() == Bundle.ACTIVE);
        }
        return stability;
    }

    /**
     * Check that the EverestService service is present.
     */
    @Test
    public void testEverestServiceIsPresent() {
        assertNotNull(everest);
        for (Bundle b : osgiHelper.getContext().getBundles()) {
            System.out.println(b.getSymbolicName());
        }
    }
}
