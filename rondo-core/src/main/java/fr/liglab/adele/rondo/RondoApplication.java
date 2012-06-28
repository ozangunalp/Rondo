package fr.liglab.adele.rondo;

import java.util.Set;

import fr.liglab.adele.rondo.model.Application;

public interface RondoApplication {

	String getName();

	Application getApplicationModel();

	void start();

	void stop();

	void resolve();

	void uninstall();

	Set<RondoFragment> getFragments();

	Set<RondoImport> getImports();

	void addApplicationStateListener(ApplicationStateListener listener);

	void removeApplicationStateListener(ApplicationStateListener listener);

}
