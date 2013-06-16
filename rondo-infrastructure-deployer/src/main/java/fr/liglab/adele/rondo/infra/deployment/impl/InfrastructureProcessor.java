package fr.liglab.adele.rondo.infra.deployment.impl;

import fr.liglab.adele.rondo.infra.annotations.Infrastructure;
import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.service.log.LogService;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

import static org.apache.felix.ipojo.util.Reflection.fields;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/14/13
 * Time: 11:27 AM
 */
public class InfrastructureProcessor {

    /**
     *
     */
    private final BundleContext m_context;

    /**
     *
     */
    private LogService m_logger;

    /**
     *
     */
    Map<Bundle,ServiceRegistration> registeredInfrastructures = new HashMap<Bundle,ServiceRegistration>();

    /**
     *
     */
    private BundleCapability packageCapability;

    /**
     *
     * @param context
     * @param logger
     */
    public InfrastructureProcessor(BundleContext context, LogService logger) {
        this.m_context = context;
        this.m_logger = logger;
    }

    /**
     *
     * @param bundle
     */
    public void activate(Bundle bundle) {
        BundleWiring wiring = bundle.adapt(BundleWiring.class);
        if (wiring == null) {
            // Invalid state.
            m_logger.log(LogService.LOG_ERROR, "The bundle " + bundle.getBundleId() + " (" + bundle.getSymbolicName() + ") " +
                    "cannot be adapted to BundleWiring, state: " + bundle.getState());
            return;
        }

        if(packageCapability==null){
            return;
        }

        // calculate pakcage import
        BundleRequirement bundleRequirement = null;
        List<BundleWire> requiredWires = wiring.getRequiredWires("osgi.wiring.package");
        for (BundleWire requiredWire : requiredWires) {
            if(requiredWire.getCapability().equals(packageCapability)){
                bundleRequirement = requiredWire.getRequirement();
            }
        }

        if(bundleRequirement==null){
            return;
        }

        // Only lookup for local classes, parent classes will be analyzed on demand.
        Collection<String> resources = wiring.listResources("/", "*.class",
                BundleWiring.FINDENTRIES_RECURSE + BundleWiring.LISTRESOURCES_LOCAL);
        if (resources == null) {
            m_logger.log(LogService.LOG_ERROR, "The bundle " + bundle.getBundleId() + " (" + bundle.getSymbolicName() + ") " +
                    " does not have any classes to be analyzed");
            return;
        }
        m_logger.log(LogService.LOG_DEBUG, resources.size() + " classes found");
        handleResources(bundle, resources, wiring.getClassLoader());
    }

    /**
     *
     * @param bundle
     * @param resources
     * @param classLoader
     */
    private void handleResources(Bundle bundle, Collection<String> resources, ClassLoader classLoader) {
        // for now take the first resource with infrastructure annotation
        ServiceRegistration foundInfrastructure = null;
        Iterator<String> resourceIterator = resources.iterator();
        while(resourceIterator.hasNext() && foundInfrastructure==null){
            foundInfrastructure = handleResource(bundle, resourceIterator.next(), classLoader);
        }
        if(foundInfrastructure!=null){
            registeredInfrastructures.put(bundle, foundInfrastructure);
        }
    }

    /**
     *
     * @param bundle
     * @param resource
     * @param classLoader
     * @return
     */
    private ServiceRegistration handleResource(Bundle bundle, String resource, ClassLoader classLoader) {
        URL url = classLoader.getResource(resource);
        if (url == null) {
            m_logger.log(LogService.LOG_ERROR, "The resource " + resource + " cannot be loaded by " + bundle.getBundleId() + " " +
                    "(" + bundle.getSymbolicName() + ")");
            return null;
        }

        String classname = getClassNameFromResource(resource);
        try {
            Class clazz = classLoader.loadClass(classname);
            Infrastructure infraAnnotation = (Infrastructure) clazz.getAnnotation(Infrastructure.class);
            if(infraAnnotation!=null){ // has configuration
                List<InfrastructureImpl> infrastructures = new ArrayList<InfrastructureImpl>();
                Object infrastructure = clazz.newInstance();
                // Collect fields
                Map<Field, InfrastructureImpl> fields =
                        fields().ofType(InfrastructureImpl.class).in(infrastructure).map();

                for (Map.Entry<Field, InfrastructureImpl> entry : fields.entrySet()) {
                    InfrastructureImpl infra = entry.getValue();
                    infra.nameIfUnnamed(entry.getKey().getName());
                    infrastructures.add(infra);
                }
                m_logger.log(LogService.LOG_WARNING, infrastructures.size() + " infrastructures found in class " + classname);

                InfrastructureImpl consolidatedInfra = consolidate(infrastructures, clazz);
                if(consolidatedInfra!=null){
                    Dictionary properties = new Hashtable();
                    properties.put("deployment.immediate",infraAnnotation.immediate());
                    properties.put("infrastructure.name",consolidatedInfra.getName());
                    return bundle.getBundleContext().registerService(fr.liglab.adele.rondo.infra.model.Infrastructure.class, consolidatedInfra, properties);
                } else {
                    m_logger.log(LogService.LOG_WARNING, "Cannot consolidate " + infrastructures.size() +" infrastructures found in class " + classname);
                    return null;
                }
            }


        } catch (ClassNotFoundException e) {
            m_logger.log(LogService.LOG_ERROR, "Cannot load class " + classname + " despite it was considered as a " +
                    "configuration", e);
            return null;
        } catch (InstantiationException e) {
            m_logger.log(LogService.LOG_ERROR, "Cannot instantiate class " + classname + " despite it was considered as a " +
                    "configuration", e);
            return null;
        } catch (IllegalAccessException e) {
            m_logger.log(LogService.LOG_ERROR, "Cannot instantiate class " + classname + " despite it was considered as a " +
                    "configuration", e);
            return null;
        }
        return null;
    }

    /**
     *
     * @param infrastructures
     * @param clazz
     * @return
     */
    private InfrastructureImpl consolidate(List<InfrastructureImpl> infrastructures, Class clazz) {
        if(infrastructures.isEmpty()){
            return null;
        } else {
            Infrastructure infraAnnotation = (Infrastructure) clazz.getAnnotation(Infrastructure.class);
            InfrastructureImpl infrastructure = infrastructures.get(0);
            String infraName = infraAnnotation.name().equals("") ? clazz.getName() : infraAnnotation.name();
            infrastructure.nameIfUnnamed(infraName);
            return infrastructure;
        }
    }

    /**
     *
     * @param resource
     * @return
     */
    public static String getClassNameFromResource(String resource) {
        String res = resource;
        if (resource.startsWith("/")) {
            res = resource.substring(1); // Remove the first /
        }
        res = res.substring(0, res.length() - ".class".length()); // Remove the .class
        return res.replace("/", ".");
    }

    /**
     *
     * @param bundle
     */
    public void deactivate(Bundle bundle) {
        if(registeredInfrastructures.containsKey(bundle)) {
            ServiceRegistration serviceRegistration = registeredInfrastructures.get(bundle);
            if(serviceRegistration!=null){
                serviceRegistration.unregister();
            }
        }
    }

    /**
     *
     */
    public void start() {
        // get myCapability
        BundleWiring myWiring = m_context.getBundle().adapt(BundleWiring.class);
        List<BundleWire> requiredWires = myWiring.getRequiredWires("osgi.wiring.package");
        for (BundleWire requiredWire : requiredWires) {
            if("fr.liglab.adele.rondo.infra.impl".equals(requiredWire.getCapability().getAttributes().get("osgi.wiring.package"))){
                packageCapability = requiredWire.getCapability();
            }
        }
    }

    /**
     *
     */
    public void stop() {
        for (ServiceRegistration registeredInfrastructure : registeredInfrastructures.values()) {
            if(registeredInfrastructure!=null){
                registeredInfrastructure.unregister();
            }
        }
    }

}
