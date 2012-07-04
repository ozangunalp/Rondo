package fr.liglab.adele.rondo.components.application;

import fr.imag.adele.mrt.architecture.ArchitectureManager;
import fr.imag.adele.mrt.du.DeploymentUnitManager;
import fr.liglab.adele.rondo.RondoApplicationContext;
import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.manager.ApplicationContextFactory;
import fr.liglab.adele.rondo.model.Application;
import fr.liglab.adele.rondo.model.ApplicationFragment;

public class SimpleApplicationPolicy {

	// Injected by iPOJO
	private String appName;
	ArchitectureManager architecture;
	DeploymentUnitManager deployment;
	ApplicationContextFactory applicationFactory;

	RondoApplicationContext context;
	Application appModel;

	void start() {
		context = applicationFactory.getApplicationContext(appName);
		appModel = context.getApplicationModel();
		System.out.println(appName + " - " + context.getName());

		for (ApplicationFragment f : appModel.getFragment()) {
			try {
				context.createFragment(f);
			} catch (InvalidConfigurationException e) {
				System.out.println("A problem occured creating fragment context");
				e.printStackTrace();
			}
		}
		// for (Component c : architecture.getComponents()) {
		// System.out.println(c.getName() + " - from " +
		// c.getDeploymentUnit().getExecutionID());
		// }

	}

	void stop() {

	}

}
