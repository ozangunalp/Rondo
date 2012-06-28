package fr.liglab.adele.rondo;

import fr.liglab.adele.rondo.model.Import;

public interface RondoImport {

	String getName();

	Import getImportModel();

	void start();

	void stop();

	void resolve();

	void dispose();

	// Set<?> getImportedInstances();

}
