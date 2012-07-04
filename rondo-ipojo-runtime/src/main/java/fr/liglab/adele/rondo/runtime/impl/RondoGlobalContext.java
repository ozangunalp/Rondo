package fr.liglab.adele.rondo.runtime.impl;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.liglab.adele.rondo.RondoApplicationContext;
import fr.liglab.adele.rondo.RondoComponentContext;
import fr.liglab.adele.rondo.RondoFragmentContext;
import fr.liglab.adele.rondo.RondoImportContext;
import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.manager.ApplicationContextFactory;
import fr.liglab.adele.rondo.manager.ComponentContextFactory;
import fr.liglab.adele.rondo.manager.FragmentContextFactory;
import fr.liglab.adele.rondo.manager.ImportContextFactory;
import fr.liglab.adele.rondo.model.Application;
import fr.liglab.adele.rondo.model.ApplicationFragment;
import fr.liglab.adele.rondo.model.Component;
import fr.liglab.adele.rondo.model.Import;

public class RondoGlobalContext implements ApplicationContextFactory, FragmentContextFactory, ComponentContextFactory,
		ImportContextFactory {

	protected static Logger logger = LoggerFactory.getLogger("rondo.runtime.application.manager");

	BundleContext context;

	Map<String, RondoApplicationContext> applicationContextMap = new HashMap<String, RondoApplicationContext>();
	Map<String, RondoFragmentContext> fragmentContextMap = new HashMap<String, RondoFragmentContext>();
	Map<String, RondoComponentContext> componentContextMap = new HashMap<String, RondoComponentContext>();
	Map<String, RondoImportContext> importContextMap = new HashMap<String, RondoImportContext>();

	public RondoGlobalContext(BundleContext context) {
		this.context = context;
	}

	// ApplicationContextFactory

	@Override
	public RondoApplicationContext createApplication(Application appModel) throws InvalidConfigurationException {
		// TODO Auto-generated method stub
		System.out.println("Created application : " + appModel.getName());
		System.out.println("Imports");
		for (Import i : appModel.getImport()) {
			System.out.println("\t" + i.getName());
		}
		System.out.println("Fragments");
		for (ApplicationFragment f : appModel.getFragment()) {
			System.out.println("\t" + f.getName() + " " + f.getPolicy().getName());
		}
		String appName = appModel.getName();
		if (!applicationContextMap.containsKey(appName)) {
			RondoApplicationContext appContext = new RondoApplicationContextImpl(appModel, context, this);
			applicationContextMap.put(appName, appContext);
			return appContext;
		} else {
			String message = "An application named +" + appName + "already exists";
			System.out.println(message);
			throw new InvalidConfigurationException(message);
		}
	}

	@Override
	public void destroyApplication(RondoApplicationContext appContext) {
		String appName = appContext.getName();
		appContext.uninstall();
		applicationContextMap.remove(appName);
	}

	@Override
	public void destroyApplication(Application appModel) {
		destroyApplication(applicationContextMap.get(appModel.getName()));
	}

	@Override
	public void destroyApplication(String appName) {
		destroyApplication(applicationContextMap.get(appName));
	}

	@Override
	public void updateApplication(RondoApplicationContext appContext, Application appModel) {
		System.out.println("Update application is not supported right now");

	}

	@Override
	public RondoApplicationContext getApplicationContext(String appName) {
		return applicationContextMap.get(appName);
	}

	// FragmentContextFactory

	@Override
	public RondoFragmentContext createFragment(ApplicationFragment fModel) throws InvalidConfigurationException {
		String fName = fModel.getName();
		if (!fragmentContextMap.containsKey(fName)) {
			RondoFragmentContext fragContext = new RondoFragmentContextImpl(fModel, context, this);
			fragmentContextMap.put(fName, fragContext);
			return fragContext;
		} else {
			String message = "A fragment named +" + fName + "already exists";
			System.out.println(message);
			throw new InvalidConfigurationException(message);
		}
	}

	@Override
	public void destroyFragment(ApplicationFragment fModel) {
		destroyFragment(fragmentContextMap.get(fModel.getName()));
	}

	@Override
	public void destroyFragment(String fragmentName) {
		destroyFragment(fragmentContextMap.get(fragmentName));
	}

	@Override
	public void destroyFragment(RondoFragmentContext fContext) {
		if (fContext != null) {
			String fName = fContext.getName();
			fragmentContextMap.remove(fName);
			fContext.dispose();
		} else {
			// TODO
		}
	}

	@Override
	public void updateFragment(RondoFragmentContext fContext, ApplicationFragment fModel) {
		System.out.println("Update application is not supported right now");

	}

	@Override
	public RondoFragmentContext getFragmentContext(String fName) {
		return fragmentContextMap.get(fName);
	}

	// ComponentContextFactory

	@Override
	public RondoComponentContext createComponent(Component model) throws InvalidConfigurationException {
		String compName = model.getName();
		if (!componentContextMap.containsKey(compName)) {
			RondoComponentContext compContext = new RondoComponentContextImpl(model, context, this);
			componentContextMap.put(compName, compContext);
			return compContext;
		} else {
			String message = "An component named +" + compName + "already exists";
			System.out.println(message);
			throw new InvalidConfigurationException(message);
		}
	}

	@Override
	public void destroyComponent(String compName) {
		destroyComponent(componentContextMap.get(compName));
	}

	@Override
	public void destroyComponent(Component model) {
		destroyComponent(componentContextMap.get(model.getName()));
	}

	@Override
	public void destroyComponent(RondoComponentContext compContext) {
		String compName = compContext.getName();
		componentContextMap.remove(compName);
		compContext.dispose();
	}

	@Override
	public void updateComponent(RondoComponentContext compcontext, Component model) {
		System.out.println("Update application is not supported right now");

	}

	@Override
	public RondoComponentContext getComponentContext(String compName) {
		return componentContextMap.get(compName);
	}

	// ImportContextFactory

	@Override
	public RondoImportContext createImport(Import model) throws InvalidConfigurationException {
		String impName = model.getName();
		if (!importContextMap.containsKey(impName)) {
			RondoImportContext importContext = new RondoImportContextImpl(model, context, this);
			importContextMap.put(impName, importContext);
			return importContext;
		} else {
			String message = "An import named " + impName + "already exists";
			System.out.println(message);
			throw new InvalidConfigurationException(message);
		}
	}

	@Override
	public void destroyImport(String compName) {
		destroyImport(importContextMap.get(compName));
	}

	@Override
	public void destroyImport(Import model) {
		destroyImport(importContextMap.get(model.getName()));
	}

	@Override
	public void destroyImport(RondoImportContext importContext) {
		String importName = importContext.getName();
		importContextMap.remove(importName);
		importContext.dispose();
	}

	@Override
	public void updateImport(RondoImportContext importContext, Import model) {
		System.out.println("Update application is not supported right now");
	}

	@Override
	public RondoImportContext getImportContext(String importName) {
		return importContextMap.get(importName);
	}

}
