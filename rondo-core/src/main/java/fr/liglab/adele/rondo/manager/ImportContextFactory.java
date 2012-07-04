package fr.liglab.adele.rondo.manager;

import fr.liglab.adele.rondo.RondoImportContext;
import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.model.Import;

public interface ImportContextFactory {

	RondoImportContext createImport(Import model) throws InvalidConfigurationException;

	void destroyImport(String compName);

	void destroyImport(Import model);

	void destroyImport(RondoImportContext importContext);

	void updateImport(RondoImportContext importcontext, Import model);

	RondoImportContext getImportContext(String compName);

}
