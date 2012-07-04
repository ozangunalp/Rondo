package fr.liglab.adele.rondo;

import fr.liglab.adele.rondo.RondoFragmentContext.FragmentState;

public interface FragmentStateListener {

	void changedState(RondoApplicationContext application, FragmentState newState);

}
