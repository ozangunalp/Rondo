package fr.liglab.adele.rondo.infra.command;

import fr.liglab.adele.rondo.infra.deployment.DeploymentHandle;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.service.command.Descriptor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/7/13
 * Time: 2:18 AM
 */
@Component(immediate = true)
@Instantiate
@Provides(specifications = RondoDeploymentCommand.class)
public class RondoDeploymentCommand {

    /**
     * Defines the command scope (ipojo).
     */
    @ServiceProperty(name = "osgi.command.scope", value = "deployment")
    String m_scope;

    /**
     * Defines the functions (commands).
     */
    @ServiceProperty(name = "osgi.command.function", value = "{}")
    String[] m_function = new String[]{"apply", "dryRun", "handles"};

    BundleContext context;

    Map<String, DeploymentHandle> handleMap = new HashMap<String, DeploymentHandle>();

    public RondoDeploymentCommand(BundleContext context) {
        this.context = context;
    }

    @Bind(specification = "fr.liglab.adele.rondo.infra.deployment.DeploymentHandle", aggregate = true, optional = true)
    public void bindDeploymentHandle(ServiceReference<DeploymentHandle> handle) {
        String serviceId = handle.getProperty(Constants.SERVICE_ID).toString();
        handleMap.put(serviceId, context.getService(handle));
    }

    @Unbind
    public void unbindDeploymentHandle(ServiceReference<DeploymentHandle> handle) {
        String serviceId = handle.getProperty(Constants.SERVICE_ID).toString();
        handleMap.remove(serviceId);
    }

    @Descriptor("apply handle")
    public void apply(@Descriptor("handle") String handleId) {
        DeploymentHandle deploymentHandle = handleMap.get(handleId);
        deploymentHandle.apply();

    }


    @Descriptor("dryRun handle")
    public void dryRun(@Descriptor("handle") String handleId) {
        DeploymentHandle deploymentHandle = handleMap.get(handleId);
        deploymentHandle.dryRun();

    }

    @Descriptor("list handle")
    public void handles() {
        System.out.println(handleMap);

    }


}
