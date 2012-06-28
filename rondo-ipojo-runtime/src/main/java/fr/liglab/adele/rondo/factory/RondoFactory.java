package fr.liglab.adele.rondo.factory;

import java.util.Dictionary;

import org.apache.felix.ipojo.ComponentFactory;
import org.apache.felix.ipojo.ConfigurationException;
import org.apache.felix.ipojo.IPojoFactory;
import org.apache.felix.ipojo.architecture.ComponentTypeDescription;
import org.apache.felix.ipojo.metadata.Attribute;
import org.apache.felix.ipojo.metadata.Element;
import org.osgi.framework.BundleContext;

import fr.liglab.adele.rondo.utils.Constants;

public abstract class RondoFactory extends ComponentFactory {

	private final String NAME_STRING = "name";

	private final String NAMESPACE_STRING = "namespace";

	private final String COMP_NAME = getComponentType() + ".name";

	private final String COMP_NAMESPACE = getComponentType() + ".namespace";

	private String componentName;

	private String namespace;

	public abstract String getComponentType();

	public RondoFactory(BundleContext context, Element element) throws ConfigurationException {
		super(context, element);
		componentName = element.getAttribute(NAME_STRING);
		if (componentName == null) {
			throw new ConfigurationException("Every " + getComponentType() + " component needs a name ");
		}
		String ns = element.getAttribute(NAMESPACE_STRING);
		if (ns != null) {
			namespace = ns.toLowerCase();
		} else {
			namespace = Constants.DEFAULT_NAMESPACE;
		}
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public ComponentTypeDescription getComponentTypeDescription() {
		// TODO Auto-generated method stub
		return new RondoTypeDescription(this);
	}

	private class RondoTypeDescription extends ComponentTypeDescription {

		public RondoTypeDescription(IPojoFactory factory) {
			super(factory);
		}

		@Override
		public Dictionary getPropertiesToPublish() {
			Dictionary dict = super.getPropertiesToPublish();
			if (getClassName() != null) {
				return super.getPropertiesToPublish();
			}
			dict.put(COMP_NAME, getComponentName());
			dict.put(COMP_NAMESPACE, getNamespace());
			return dict;

		}

		@Override
		public Element getDescription() {
			Element element = super.getDescription();
			element.addAttribute(new Attribute("Impl-Class", getClassName()));
			return element;
		}

	}

}
