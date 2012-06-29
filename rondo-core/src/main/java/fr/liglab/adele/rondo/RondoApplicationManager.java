package fr.liglab.adele.rondo;

import fr.liglab.adele.rondo.model.Application;

public interface RondoApplicationManager {

	RondoApplicationContext createApplication(Application appModel);

	void destroyApplication(RondoApplicationContext appContext);

	void updateApplication(RondoApplicationContext appContext, Application appModel);

}
