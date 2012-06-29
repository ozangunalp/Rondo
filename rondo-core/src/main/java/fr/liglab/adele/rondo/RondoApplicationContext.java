package fr.liglab.adele.rondo;

import java.util.Set;

import fr.liglab.adele.rondo.model.Application;

public interface RondoApplicationContext {

	String getName();

	Application getApplicationModel();

	ApplicationState getApplicationState();

	void start();

	void stop();

	void resolve();

	void uninstall();

	Set<RondoFragmentContext> getFragments();

	Set<RondoImportContext> getImports();

	void addApplicationStateListener(ApplicationStateListener listener);

	void removeApplicationStateListener(ApplicationStateListener listener);

	public enum ApplicationState {
		UNINSTALLED, INSTALLED, RESOLVED, STARTING, STOPPING, ACTIVE
	}
}
