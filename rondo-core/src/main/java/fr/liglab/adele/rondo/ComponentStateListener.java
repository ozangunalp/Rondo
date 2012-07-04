package fr.liglab.adele.rondo;

import fr.liglab.adele.rondo.RondoComponentContext.ComponentState;

public interface ComponentStateListener {

	void changedState(RondoComponentContext compContext, ComponentState state);
}
