package fr.liglab.adele.rondo.runtime.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.apache.felix.ipojo.ComponentInstance;
import org.osgi.framework.BundleContext;

import fr.liglab.adele.rondo.ComponentStateListener;
import fr.liglab.adele.rondo.FragmentStateListener;
import fr.liglab.adele.rondo.RondoComponentContext;
import fr.liglab.adele.rondo.RondoComponentContext.ComponentState;
import fr.liglab.adele.rondo.RondoFragmentContext;
import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.model.ApplicationFragment;
import fr.liglab.adele.rondo.model.Component;
import fr.liglab.adele.rondo.utils.Constants;

public class RondoFragmentContextImpl extends AbstractRondoContext implements RondoFragmentContext,
		ComponentStateListener {

	ApplicationFragment fragmentModel;
	private final RondoGlobalContext rondo_context;

	List<FragmentStateListener> listeners = new ArrayList<FragmentStateListener>();

	public RondoFragmentContextImpl(ApplicationFragment fModel, BundleContext context, RondoGlobalContext rondo_context) {
		super(context);
		this.fragmentModel = fModel;
		this.rondo_context = rondo_context;
		// TODO
		for (Component c : fModel.getComponent()) {
			RondoComponentContext cc;
			try {
				cc = createComponent(c);
				cc.addComponentStateListener(this);
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public String getName() {
		return fragmentModel.getName();
	}

	@Override
	public ApplicationFragment getFragmentModel() {
		return fragmentModel;
	}

	@Override
	public void activate() {
		for (RondoComponentContext c : getComponents()) {
			c.activate();
		}
	}

	@Override
	public void deactivate() {
		for (RondoComponentContext c : getComponents()) {
			c.deactivate();
		}
	}

	@Override
	public void resolve() {
		for (RondoComponentContext c : getComponents()) {
			c.resolve();
		}
	}

	@Override
	public void dispose() {
		System.out.println("Disposing of Fragment " + getName());
		for (RondoComponentContext c : getComponents()) {
			destroyComponent(c);
		}
		this.stopInstance();
	}

	@Override
	public RondoComponentContext createComponent(Component model) throws InvalidConfigurationException {
		RondoComponentContext comp = rondo_context.createComponent(model);
		comp.addComponentStateListener(this);
		return comp;
	}

	@Override
	public void destroyComponent(RondoComponentContext componentContext) {
		rondo_context.destroyComponent(componentContext);
	}

	@Override
	public Set<RondoComponentContext> getComponents() {
		List<Component> components = fragmentModel.getComponent();
		if (components != null) {
			Set<RondoComponentContext> contexts = new HashSet<RondoComponentContext>();
			for (Component c : components) {
				RondoComponentContext componentContext = rondo_context.getComponentContext(c.getName());
				if (componentContext != null) {
					contexts.add(componentContext);
				}
			}
			return Collections.unmodifiableSet(contexts);
		} else {
			return Collections.emptySet();
		}
	}

	@Override
	public void addFragmentStateListener(FragmentStateListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeFragmentStateListener(FragmentStateListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void stateChanged(ComponentInstance instance, int newState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String createFactoryFilter() {
		StringBuffer filterString = new StringBuffer();
		// TODO
		filterString.append("(&");
		filterString.append("(");
		filterString.append(Constants.RONDO_FACTORY_TYPE);
		filterString.append("=");
		filterString.append(Constants.FRAGMENT_TYPE);
		// TODO
		filterString.append("))");
		return filterString.toString();
	}

	@Override
	public Hashtable getInstanceProperties() {
		Hashtable prs = new Hashtable();
		prs.put("fragment.name", fragmentModel.getName());
		return prs;
	}

	@Override
	public void changedState(RondoComponentContext compContext, ComponentState state) {
		// TODO Auto-generated method stub

	}

}
