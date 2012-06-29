package fr.liglab.adele.rondo;

import fr.liglab.adele.rondo.model.Component;

public interface RondoComponentContext {

	String getName();

	Component getModel();

	void activate();

	void deactivate();

	void resolve();

	void dispose();

	// Set<> getInstances();

	void addComponentStateListener(ComponentStateListener listener);

	void removeComponentStateListener(ComponentStateListener listener);

}
