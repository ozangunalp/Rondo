package fr.liglab.adele.rondo.runtime.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.apache.felix.ipojo.ComponentInstance;
import org.apache.felix.ipojo.util.Tracker;
import org.osgi.framework.BundleContext;

import fr.liglab.adele.rondo.ApplicationStateListener;
import fr.liglab.adele.rondo.FragmentStateListener;
import fr.liglab.adele.rondo.ImportStateListener;
import fr.liglab.adele.rondo.RondoApplicationContext;
import fr.liglab.adele.rondo.RondoFragmentContext;
import fr.liglab.adele.rondo.RondoFragmentContext.FragmentState;
import fr.liglab.adele.rondo.RondoImportContext;
import fr.liglab.adele.rondo.RondoImportContext.ImportState;
import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.model.Application;
import fr.liglab.adele.rondo.model.ApplicationFragment;
import fr.liglab.adele.rondo.model.Import;
import fr.liglab.adele.rondo.utils.Constants;

public class RondoApplicationContextImpl extends AbstractRondoContext implements RondoApplicationContext,
		FragmentStateListener, ImportStateListener {

	Application appModel;

	ApplicationState state;

	Tracker factoryTracker;

	private final RondoGlobalContext rondo_context;

	List<ApplicationStateListener> listeners = new ArrayList<ApplicationStateListener>();

	public RondoApplicationContextImpl(Application appModel, BundleContext context, RondoGlobalContext rondo_context) {
		super(context);
		this.appModel = appModel;
		this.rondo_context = rondo_context;
		// Check maybe app model??
		this.state = ApplicationState.UNINSTALLED;
	}

	@Override
	public String getName() {
		return appModel.getName();
	}

	@Override
	public Application getApplicationModel() {
		return appModel;
	}

	@Override
	public ApplicationState getApplicationState() {
		return state;
	}

	@Override
	public void start() {
		this.registerTracker(createFactoryFilter());
		for (RondoImportContext i : getImports()) {
			System.out.println(i.getName());
			i.activate();
		}
		for (RondoFragmentContext f : getFragments()) {
			System.out.println(f.getName());
			f.activate();
		}
	}

	@Override
	public void stop() {

		for (RondoFragmentContext f : getFragments()) {
			f.deactivate();
		}
		for (RondoImportContext i : getImports()) {
			i.deactivate();
		}
	}

	@Override
	public void resolve() {
		for (RondoFragmentContext f : getFragments()) {
			f.resolve();
		}
		for (RondoImportContext i : getImports()) {
			i.resolve();
		}
	}

	@Override
	public void uninstall() {
		System.out.println("Uninstalling application " + getName());
		for (RondoFragmentContext f : getFragments()) {
			destroyFragment(f);
		}
		for (RondoImportContext i : getImports()) {
			destroyImport(i);
		}
		this.stopInstance();
	}

	@Override
	public RondoFragmentContext createFragment(ApplicationFragment fragmentModel) throws InvalidConfigurationException {
		RondoFragmentContext fragment = rondo_context.createFragment(fragmentModel);
		fragment.addFragmentStateListener(this);
		return fragment;
	}

	@Override
	public void destroyFragment(RondoFragmentContext fragment) {
		rondo_context.destroyFragment(fragment);
	}

	@Override
	public Set<RondoFragmentContext> getFragments() {
		List<ApplicationFragment> fragments = appModel.getFragment();
		if (fragments != null) {
			Set<RondoFragmentContext> contexts = new HashSet<RondoFragmentContext>();
			for (ApplicationFragment f : fragments) {
				RondoFragmentContext fContext = rondo_context.getFragmentContext(f.getName());
				if (fContext != null) {
					contexts.add(fContext);
				}
			}
			return Collections.unmodifiableSet(contexts);
		} else {
			return Collections.emptySet();
		}
	}

	@Override
	public RondoImportContext createImport(Import importModel) throws InvalidConfigurationException {
		RondoImportContext importContext = rondo_context.createImport(importModel);
		importContext.addImportStateListener(this);
		return importContext;
	}

	@Override
	public void destroyImport(RondoImportContext importModel) {
		rondo_context.destroyImport(importModel);
	}

	@Override
	public Set<RondoImportContext> getImports() {
		List<Import> imports = appModel.getImport();
		if (imports != null) {
			Set<RondoImportContext> contexts = new HashSet<RondoImportContext>();
			for (Import f : imports) {
				RondoImportContext iContext = rondo_context.getImportContext(f.getName());
				if (iContext != null) {
					contexts.add(iContext);
				}
			}
			return Collections.unmodifiableSet(contexts);
		} else {
			return Collections.emptySet();
		}
	}

	private void notifyListeners() {
		for (ApplicationStateListener l : listeners) {
			l.changedState(this, state);
		}
	}

	@Override
	public void addApplicationStateListener(ApplicationStateListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeApplicationStateListener(ApplicationStateListener listener) {
		listeners.remove(listener);
	}

	@Override
	public String createFactoryFilter() {
		StringBuffer filterString = new StringBuffer();
		// TODO
		filterString.append("(&");
		filterString.append("(");
		filterString.append(Constants.RONDO_FACTORY_TYPE);
		filterString.append("=");
		filterString.append(Constants.APPLICATION_TYPE);
		// TODO
		filterString.append(")");
		filterString.append("(factory.state=1)");
		filterString.append(")");
		return filterString.toString();
	}

	@Override
	public Hashtable getInstanceProperties() {
		Hashtable prs = new Hashtable();
		prs.put("application.name", appModel.getName());
		return prs;
	}

	@Override
	public void stateChanged(ComponentInstance instance, int newState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changedState(RondoImportContext importContext, ImportState newState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changedState(RondoApplicationContext application, FragmentState newState) {
		// TODO Auto-generated method stub

	}

}
