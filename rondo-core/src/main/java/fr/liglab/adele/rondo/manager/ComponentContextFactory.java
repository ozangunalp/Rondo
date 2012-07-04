package fr.liglab.adele.rondo.manager;

import fr.liglab.adele.rondo.RondoComponentContext;
import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.model.Component;

public interface ComponentContextFactory {

	RondoComponentContext createComponent(Component model) throws InvalidConfigurationException;

	void destroyComponent(String compName);

	void destroyComponent(Component model);

	void destroyComponent(RondoComponentContext compContext);

	void updateComponent(RondoComponentContext compcontext, Component model);

	RondoComponentContext getComponentContext(String compName);

}
