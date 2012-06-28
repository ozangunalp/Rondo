package fr.liglab.adele.rondo;

import fr.liglab.adele.rondo.model.Application;

public interface RondoContext {

	void createApplication(Application app);

	void destroyApplication(Application app);

	void updateApplication(Application app);

}
