package fr.liglab.adele.rondo.runtime.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.felix.ipojo.ComponentInstance;
import org.osgi.framework.BundleContext;

import fr.liglab.adele.rondo.ComponentPolicy;
import fr.liglab.adele.rondo.ComponentStateListener;
import fr.liglab.adele.rondo.RondoComponentContext;
import fr.liglab.adele.rondo.model.Component;
import fr.liglab.adele.rondo.utils.Constants;

public class RondoComponentContextImpl extends AbstractRondoContext implements RondoComponentContext {

	ComponentState state;

	Component compModel;

	ComponentPolicy policy = null;

	List<ComponentStateListener> listeners = new ArrayList<ComponentStateListener>();

	public RondoComponentContextImpl(Component model, BundleContext context, RondoGlobalContext rondo_context) {
		super(context);
		this.compModel = model;
	}

	@Override
	public String getName() {
		return compModel.getName();
	}

	@Override
	public Component getModel() {
		return compModel;
	}

	@Override
	public void activate() {
		this.registerTracker(createFactoryFilter());
		if (policy != null) {
			policy.activate();
		}
	}

	@Override
	public void deactivate() {
		if (policy != null) {
			policy.deactivate();
		}
	}

	@Override
	public void resolve() {
		if (policy != null) {
			policy.resolve();
		}
	}

	@Override
	public void dispose() {
		if (policy != null) {
			policy.dispose();
		}
		this.stopInstance();
	}

	@Override
	public void setState(ComponentState newState) {
		state = newState;
		notifyListeners();
		System.out.println("Component" + compModel.getName() + "policy changes state :" + newState.name());
	}

	@Override
	public ComponentState getComponentState() {
		return this.state;
	}

	private void notifyListeners() {
		for (ComponentStateListener l : listeners) {
			l.changedState(this, this.state);
		}
	}

	@Override
	public void addComponentStateListener(ComponentStateListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeComponentStateListener(ComponentStateListener listener) {
		listeners.add(listener);
	}

	@Override
	public void stateChanged(ComponentInstance instance, int newState) {
		if (ComponentInstance.VALID == newState) {
			try {
				policy = (ComponentPolicy) getObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			policy = null;
		}
	}

	@Override
	protected String createFactoryFilter() {
		StringBuffer filterString = new StringBuffer();
		// TODO
		filterString.append("(&");
		filterString.append("(");
		filterString.append(Constants.RONDO_FACTORY_TYPE);
		filterString.append("=");
		filterString.append(Constants.COMPONENT_TYPE);
		// TODO
		filterString.append(")");
		filterString.append("(factory.state=1)");
		filterString.append(")");
		return filterString.toString();
	}

	@Override
	public Hashtable getInstanceProperties() {
		Hashtable prs = new Hashtable();
		prs.put("component.name", compModel.getName());
		return prs;
	}

}
