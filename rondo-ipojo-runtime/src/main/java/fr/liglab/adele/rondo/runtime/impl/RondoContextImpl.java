package fr.liglab.adele.rondo.runtime.impl;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import fr.liglab.adele.rondo.RondoContext;
import fr.liglab.adele.rondo.model.Application;

@Component
@Provides
@Instantiate
public class RondoContextImpl implements RondoContext {

	public RondoContextImpl() {
	}

	@Override
	public void createApplication(Application app) {

	}

	@Override
	public void destroyApplication(Application app) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateApplication(Application app) {
		// TODO Auto-generated method stub

	}

}
