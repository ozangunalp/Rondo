package fr.liglab.adele.rondo.manager;

import fr.liglab.adele.rondo.RondoApplicationContext;
import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.model.Application;

public interface ApplicationContextFactory {

	RondoApplicationContext createApplication(Application appModel) throws InvalidConfigurationException;

	void destroyApplication(Application appModel);

	void destroyApplication(RondoApplicationContext appContext);

	void destroyApplication(String appName);

	void updateApplication(RondoApplicationContext appContext, Application appModel);

	RondoApplicationContext getApplicationContext(String appName);

}
