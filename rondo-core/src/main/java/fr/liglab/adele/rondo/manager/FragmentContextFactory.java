package fr.liglab.adele.rondo.manager;

import fr.liglab.adele.rondo.RondoFragmentContext;
import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.model.ApplicationFragment;

public interface FragmentContextFactory {

	RondoFragmentContext createFragment(ApplicationFragment fModel) throws InvalidConfigurationException;

	void destroyFragment(ApplicationFragment fModel);

	void destroyFragment(RondoFragmentContext fContext);

	void destroyFragment(String fragmentName);

	void updateFragment(RondoFragmentContext fContext, ApplicationFragment fModel);

	RondoFragmentContext getFragmentContext(String fName);

}