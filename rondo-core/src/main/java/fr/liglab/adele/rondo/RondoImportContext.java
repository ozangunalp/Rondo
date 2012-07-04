package fr.liglab.adele.rondo;

import fr.liglab.adele.rondo.model.Import;

public interface RondoImportContext {

	String getName();

	Import getImportModel();

	void activate();

	void deactivate();

	void resolve();

	void dispose();

	// Set<?> getImportedInstances();

	void addImportStateListener(ImportStateListener listener);

	void removeImportStateListener(ImportStateListener listener);

	public enum ImportState {
		RESOLVED, STOPPED, INVALID, VALID
	}

}
