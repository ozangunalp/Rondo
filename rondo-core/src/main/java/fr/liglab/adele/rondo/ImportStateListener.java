package fr.liglab.adele.rondo;

import fr.liglab.adele.rondo.RondoImportContext.ImportState;

public interface ImportStateListener {

	void changedState(RondoImportContext importContext, ImportState newState);

}
