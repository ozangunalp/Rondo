package fr.liglab.adele.rondo.components.component;

import org.osgi.framework.BundleContext;

import fr.imag.adele.mrt.architecture.ArchitectureManager;
import fr.imag.adele.mrt.architecture.model.Component;
import fr.imag.adele.mrt.architecture.model.DependencyDeclaration;
import fr.imag.adele.mrt.du.DeploymentUnitManager;
import fr.imag.adele.mrt.du.model.DeploymentUnit;
import fr.imag.adele.mrt.exception.DynamismException;
import fr.liglab.adele.rondo.ComponentPolicy;
import fr.liglab.adele.rondo.RondoComponentContext;
import fr.liglab.adele.rondo.RondoComponentContext.ComponentState;
import fr.liglab.adele.rondo.manager.ComponentContextFactory;
import fr.liglab.adele.rondo.model.Configuration;

public class SimpleComponentPolicy implements ComponentPolicy {

	// injected by ipojo
	private final BundleContext b_context;
	private ArchitectureManager architecture;
	private DeploymentUnitManager deployment;
	private ComponentContextFactory compFactory;
	private String compName;

	//
	RondoComponentContext context;
	private fr.liglab.adele.rondo.model.Component model;

	public SimpleComponentPolicy(BundleContext b_context) {
		this.b_context = b_context;
	}

	void start() {

		context = compFactory.getComponentContext(compName);
		model = context.getModel();

	}

	void stop() {

	}

	@Override
	public void activate() {
		Component c = architecture.getComponent(compName);
		if (c != null) {
			System.out.println("Component not found with name " + compName);
		}
		context.setState(ComponentState.INVALID);
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resolve() {
		Component c = architecture.getComponent(compName);
		if (c != null) {
			if (c.getState() == Component.VALID) {
				try {
					checkComponentForConfiguration(c, model.getConfiguation());
				} catch (DynamismException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Component not found with name " + compName);
			DeploymentUnit du = deployment.getDeploymentUnitFromComponent(compName);
			if (du != null) {
				resolveDeploymentUnit(du, this.b_context);
			} else {

			}
			context.setState(ComponentState.INVALID);
		}

	}

	private void resolveDeploymentUnit(DeploymentUnit du, BundleContext b_context2) {
		// TODO Auto-generated method stub

	}

	private void checkComponentForConfiguration(Component c, Configuration configuration) throws DynamismException {
		for (DependencyDeclaration dd : c.getDependencies()) {
			dd.g
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
