package fr.liglab.adele.rondo.runtime.impl;

import java.util.Hashtable;

import org.apache.felix.ipojo.ComponentInstance;
import org.osgi.framework.BundleContext;

import fr.liglab.adele.rondo.ImportStateListener;
import fr.liglab.adele.rondo.RondoImportContext;
import fr.liglab.adele.rondo.model.Import;

public class RondoImportContextImpl extends AbstractRondoContext implements RondoImportContext {

	Import model;
	RondoGlobalContext rondo_context;

	public RondoImportContextImpl(Import model, BundleContext context, RondoGlobalContext rondo_context) {
		super(context);
		this.model = model;
		this.rondo_context = rondo_context;
	}

	@Override
	public String getName() {
		return model.getName();
	}

	@Override
	public Import getImportModel() {
		return model;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resolve() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		System.out.println("Mock : import" + getName() + " disposed");
	}

	@Override
	public void stateChanged(ComponentInstance instance, int newState) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String createFactoryFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hashtable getInstanceProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addImportStateListener(ImportStateListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeImportStateListener(ImportStateListener listener) {
		// TODO Auto-generated method stub

	}

}
