package fr.liglab.adele.rondo.factory;

import org.apache.felix.ipojo.ConfigurationException;
import org.apache.felix.ipojo.metadata.Element;
import org.osgi.framework.BundleContext;

public class FragmentManagerFactory extends RondoFactory {

	private final String COMPONENT_TYPE = "fragment";

	public FragmentManagerFactory(BundleContext context, Element element) throws ConfigurationException {
		super(context, element);
	}

	@Override
	public String getComponentType() {
		return COMPONENT_TYPE;
	}

}
