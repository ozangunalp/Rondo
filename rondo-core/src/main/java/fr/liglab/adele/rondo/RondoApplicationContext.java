package fr.liglab.adele.rondo;

import java.util.Set;

import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.model.Application;
import fr.liglab.adele.rondo.model.ApplicationFragment;
import fr.liglab.adele.rondo.model.Import;

public interface RondoApplicationContext {

	String getName();

	Application getApplicationModel();

	ApplicationState getApplicationState();

	void start();

	void stop();

	void resolve();

	void uninstall();

	Set<RondoFragmentContext> getFragments();

	RondoFragmentContext createFragment(ApplicationFragment fragmentModel) throws InvalidConfigurationException;

	void destroyFragment(RondoFragmentContext fragment);

	Set<RondoImportContext> getImports();

	RondoImportContext createImport(Import importModel) throws InvalidConfigurationException;

	void destroyImport(RondoImportContext importModel);

	void addApplicationStateListener(ApplicationStateListener listener);

	void removeApplicationStateListener(ApplicationStateListener listener);

	public enum ApplicationState {
		UNINSTALLED, INSTALLED, RESOLVED, STARTING, STOPPING, ACTIVE
	}

}
