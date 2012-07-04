package fr.liglab.adele.rondo;

import fr.liglab.adele.rondo.RondoApplicationContext.ApplicationState;

public interface ApplicationStateListener {

	void changedState(RondoApplicationContext application, ApplicationState newState);

}
