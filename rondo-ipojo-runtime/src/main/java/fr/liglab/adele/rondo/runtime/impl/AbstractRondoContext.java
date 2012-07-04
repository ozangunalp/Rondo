package fr.liglab.adele.rondo.runtime.impl;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.felix.ipojo.ComponentInstance;
import org.apache.felix.ipojo.ConfigurationException;
import org.apache.felix.ipojo.Factory;
import org.apache.felix.ipojo.InstanceManager;
import org.apache.felix.ipojo.InstanceStateListener;
import org.apache.felix.ipojo.MissingHandlerException;
import org.apache.felix.ipojo.UnacceptableConfiguration;
import org.apache.felix.ipojo.util.Tracker;
import org.apache.felix.ipojo.util.TrackerCustomizer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRondoContext implements TrackerCustomizer, InstanceStateListener {

	Object lock = new Object();

	private static final Logger log = LoggerFactory.getLogger("cilia.ipojo.runtime");

	BundleContext context;

	Tracker factoryTracker;

	ComponentInstance componentInstance;

	public AbstractRondoContext(BundleContext context) {
		this.context = context;
	}

	private void createPolicyInstance(Factory factory) {
		boolean created = false;
		synchronized (lock) {
			try {
				componentInstance = factory.createComponentInstance(getInstanceProperties());
				componentInstance.addInstanceStateListener(this);
				created = true;
			} catch (UnacceptableConfiguration e) {
				e.printStackTrace();
			} catch (MissingHandlerException e) {
				e.printStackTrace();
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
		}
		if (created) {
			stateChanged(componentInstance, getState());
		}
	}

	protected abstract String createFactoryFilter();

	public abstract Hashtable getInstanceProperties();

	/**
	 * Get the object instance. return the object instance. Null when the
	 * instance is not valid.
	 */
	public Object getObject() {
		Object object = null;
		synchronized (lock) {
			if (getState() == ComponentInstance.VALID) {
				object = ((InstanceManager) componentInstance).getPojoObject();
			}
		}
		if (object == null) {
			log.error("Is not valid");
		}
		return object;
	}

	/**
	 * Get the instance state.
	 */
	public int getState() {
		synchronized (lock) {
			if (componentInstance == null) {
				return ComponentInstance.INVALID;
			}
			return componentInstance.getState();
		}
	}

	/**
	 * 
	 */
	protected void stopInstance() {
		unregisterTracker();
		try {
		} catch (IllegalStateException ex) {
		}
		disposeInstance();
	}

	public void updateInstanceProperties(Dictionary properties) {
		properties.remove("instance.name");
		synchronized (lock) {
			if (componentInstance != null) {
				componentInstance.reconfigure(properties);
			}
		}
	}

	/**
	 * Dispose the ipojo instance.
	 */
	private void disposeInstance() {
		synchronized (lock) {
			if (componentInstance != null) {
				componentInstance.removeInstanceStateListener(this);
				componentInstance.stop();
				componentInstance.dispose();
			}
			componentInstance = null;
		}
	}

	public ComponentInstance getInstanceManager() {
		synchronized (lock) {
			return componentInstance;
		}
	}

	/** Tracker Customizer methods **/
	/**
	 * Registering the factory tracker.
	 */
	protected void registerTracker(String filter) {
		if (factoryTracker == null) {
			try {
				factoryTracker = new Tracker(context, context.createFilter(filter), this);
				factoryTracker.open();
			} catch (InvalidSyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Unregistering the factory tracker.
	 */
	protected void unregisterTracker() {
		if (factoryTracker != null) {
			factoryTracker.close();
			factoryTracker = null;
		}
	}

	@Override
	public boolean addingService(ServiceReference reference) {
		return true;
	}

	@Override
	public void addedService(ServiceReference reference) {
		System.out.println("Found factory creating policy instance");
		Factory factory = (Factory) context.getService(reference);
		createPolicyInstance(factory);
		context.ungetService(reference);
	}

	@Override
	public void modifiedService(ServiceReference reference, Object service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		disposeInstance();
	}
}
