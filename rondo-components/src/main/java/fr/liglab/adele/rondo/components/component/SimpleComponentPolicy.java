package fr.liglab.adele.rondo.components.component;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;

import fr.imag.adele.mrt.architecture.ArchitectureManager;
import fr.imag.adele.mrt.architecture.model.Component;
import fr.imag.adele.mrt.architecture.model.DependencyDeclaration;
import fr.imag.adele.mrt.architecture.model.Instance;
import fr.imag.adele.mrt.architecture.model.InstanceStateListener;
import fr.imag.adele.mrt.architecture.model.PropertyDeclaration;
import fr.imag.adele.mrt.du.DeploymentUnitManager;
import fr.imag.adele.mrt.du.model.DeploymentUnit;
import fr.imag.adele.mrt.du.model.ProvidedPackageInfo;
import fr.imag.adele.mrt.du.model.RequiredPackageInfo;
import fr.imag.adele.mrt.exception.DeploymentUnitException;
import fr.imag.adele.mrt.exception.DynamismException;
import fr.imag.adele.mrt.exception.InvalidStateException;
import fr.imag.adele.obr.client.repository.OBRRepositoryAdmin;
import fr.liglab.adele.rondo.ComponentPolicy;
import fr.liglab.adele.rondo.RondoComponentContext;
import fr.liglab.adele.rondo.RondoComponentContext.ComponentState;
import fr.liglab.adele.rondo.manager.ComponentContextFactory;
import fr.liglab.adele.rondo.model.Configuration;
import fr.liglab.adele.rondo.model.ConfigurationDataConsuming;
import fr.liglab.adele.rondo.model.ConfigurationDataPublishing;
import fr.liglab.adele.rondo.model.ConfigurationProperty;
import fr.liglab.adele.rondo.model.ConfigurationServiceDependency;
import fr.liglab.adele.rondo.model.ConfigurationServiceProviding;

/**
 * @author gunalp (ozan.gunalp<at>imag.fr)
 * 
 */
public class SimpleComponentPolicy implements ComponentPolicy, InstanceStateListener {

	// injected by ipojo
	private final BundleContext b_context;
	private ArchitectureManager architecture;
	private DeploymentUnitManager deployment;
	private ComponentContextFactory compFactory;
	private OBRRepositoryAdmin obrAdmin;
	private String compName;

	/**
	 * 
	 */
	RondoComponentContext context;
	/**
	 * 
	 */
	private fr.liglab.adele.rondo.model.Component model;

	/**
	 * 
	 */
	String implementationName;

	/**
	 * 
	 */
	Filter implementationFilter;

	/**
	 * Choosen implementation to instanciate, subject to change
	 */
	Component implementation;

	/**
	 * Configuration properties
	 */
	private final List<ConfigurationProperty> properties = new ArrayList<ConfigurationProperty>();
	private final List<ConfigurationServiceDependency> dependency = new ArrayList<ConfigurationServiceDependency>();
	private final List<ConfigurationServiceProviding> providing = new ArrayList<ConfigurationServiceProviding>();
	private final List<ConfigurationDataPublishing> publishing = new ArrayList<ConfigurationDataPublishing>();
	private final List<ConfigurationDataConsuming> consuming = new ArrayList<ConfigurationDataConsuming>();

	/**
	 * Instance name list for created instances
	 */
	private final List<String> instances = new ArrayList<String>();

	public SimpleComponentPolicy(BundleContext b_context) {
		this.b_context = b_context;
	}

	/**
	 * iPOJO validate method
	 */
	void start() {
		System.out.println("Starting component policy");
		context = compFactory.getComponentContext(compName);
		model = context.getModel();
		// re-parse configuration properties fuck jaxb
		initializeConfiguraitonProperties(model.getConfiguation());
		// set component state to stopped
		context.setState(ComponentState.STOPPED);

		// lets see if implementation is setted?
		if (model.getImplementation() != null) {
			implementationName = model.getImplementation();
			// if not maybe the filter?
		} else if (model.getImplementationFilter() != null) {
			try {
				System.out.println("Creating filter with:" + model.getImplementationFilter());
				implementationFilter = b_context.createFilter(model.getImplementationFilter());
			} catch (InvalidSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// TODO throw some exceptions
			context.setState(ComponentState.INVALID);
		}

	}

	/**
	 * iPOJO invalidate method
	 */
	void stop() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.liglab.adele.rondo.ComponentPolicy#activate()
	 */
	@Override
	public void activate() {
		System.out.println("activating component implementation");
		if (context.getComponentState() == ComponentState.RESOLVED) {
			if (implementation != null) {
				createInstance(implementation);
			}
		} else {
			resolve();
			if (context.getComponentState() == ComponentState.RESOLVED) {
				if (implementation != null) {
					createInstance(implementation);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.liglab.adele.rondo.ComponentPolicy#deactivate()
	 */
	@Override
	public void deactivate() {
		System.out.println("deacticating component implementation");
		for (String instanceName : instances) {
			Instance i = architecture.getInstance(instanceName);
			try {
				i.stop();
			} catch (DynamismException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.liglab.adele.rondo.ComponentPolicy#resolve()
	 */
	@Override
	public void resolve() {
		System.out.println("Resolving component implementation ");
		// Oh clearly we need clearly a resolver here!
		resolveLocally();
		if (context.getComponentState() != ComponentState.RESOLVED) {
			resolveRemotely();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.liglab.adele.rondo.ComponentPolicy#dispose()
	 */
	@Override
	public void dispose() {
		System.out.println("Disposing ");
		for (String instanceName : instances) {
			Instance i = architecture.getInstance(instanceName);
			try {
				i.destroy();
			} catch (DynamismException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		context.setState(ComponentState.STOPPED);
	}

	/**
	 * 
	 */
	private void resolveLocally() {
		System.out.println("Resolving locally first");
		// start by searching locally
		if (implementationName != null) {
			implementation = architecture.getComponent(implementationName);
		} else {
			// he who comes the first is the best
			Set<Component> allComponents = architecture.getComponents();
			Set<Component> cs = matchFilter(allComponents, implementationFilter);
			if (cs.iterator().hasNext()) {
				implementation = cs.iterator().next();
			}
		}
		System.out.println(implementation);
		if (implementation != null) {
			System.out.println("Great! we found an implementation: " + implementation.getName() + " - "
					+ implementation.getVersion());
			if (implementation.getState() == Component.VALID) {
				try {
					if (checkComponentForConfiguration(implementation)) {
						context.setState(ComponentState.RESOLVED);
					} else {
						implementation = null;
					}
				} catch (DynamismException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (implementation.getState() == Component.INSTALLED) {
					try {
						implementation.getDeploymentUnit().activate();
					} catch (DeploymentUnitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DynamismException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	private void resolveRemotely() {
		System.out.println("Searching the repositories " + obrAdmin.getRepositories().size());
		if (implementationName != null) {
			Set<fr.imag.adele.obr.client.repository.DeploymentUnit> units = obrAdmin
					.getDeploymentUnitFromComponent(implementationName);
			System.out.println("found " + units.size() + "deployment units in the repository containing the component "
					+ model.getImplementation());
			if (!units.isEmpty()) {
				fr.imag.adele.obr.client.repository.DeploymentUnit u = units.iterator().next();
				u.install();
				resolve();
			}
		} else {
			System.out.println("we are unable to make a search by filter on remote repository");
			context.setState(ComponentState.INVALID);
		}
	}

	/**
	 * @param components
	 * @param implementationFilter
	 * @return
	 */
	private Set<Component> matchFilter(Set<Component> components, Filter implementationFilter) {
		Set<Component> filterResults = new HashSet<Component>();
		for (Component c : components) {
			Dictionary<String, Object> props = new Hashtable<String, Object>();
			props.put("name", c.getName());
			if (c.getVersion() != null) {
				props.put("version", c.getVersion());
			}
			// System.out.println(c.getParametersOfConfiguration());
			// if (c.getParametersOfConfiguration() != null) {
			// for (PropertyDeclaration pd :
			// c.getParametersOfConfiguration().values()) {
			// if (pd.isDeclarative()) {
			// props.put(pd.getKey(), pd.defaultValue());
			// }
			// }
			// }

			if (implementationFilter.match(props)) {
				filterResults.add(c);
			}
		}
		return filterResults;
	}

	/**
	 * @param c
	 */
	private void createInstance(Component c) {
		String instanceName = null;
		Dictionary properties = generatePropertiesFor(c);
		System.out.println("creating instance with these properties: " + properties);
		try {
			instanceName = c.createInstance(properties);
		} catch (DynamismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (instanceName != null) {
			instances.add(instanceName);
			Instance i = architecture.getInstance(instanceName);
			i.addInstanceStateListener(this);
			try {
				if (i.getState() == Instance.VALID) {
					context.setState(ComponentState.VALID);
				}
			} catch (DynamismException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("something went wrong");
		}
	}

	/**
	 * @param c
	 * @return
	 */
	private Dictionary generatePropertiesFor(Component c) {
		Dictionary props = new Hashtable();
		for (ConfigurationProperty property : properties) {
			props.put(property.getName(), property.getValue());
		}
		Dictionary requiresFilters = new Hashtable();
		for (ConfigurationServiceDependency dep : dependency) {
			requiresFilters.put(dep.getName(), dep.getFilter());
		}
		if (!requiresFilters.isEmpty()) {
			props.put("requires.filters", requiresFilters);
		}
		// for (ConfigurationServiceProviding service : providing) {
		// service.getProperty();
		// props.put(, value)
		// }

		return props;
	}

	/**
	 * @param du
	 * @param b_context
	 * @return
	 * @throws DynamismException
	 */
	private List<RequiredPackageInfo> resolveDeploymentUnit(DeploymentUnit du, BundleContext b_context)
			throws DynamismException {
		List<RequiredPackageInfo> rpiList = new ArrayList<RequiredPackageInfo>();
		for (RequiredPackageInfo rpi : du.getRequiredPackages()) {
			boolean alreadyPresent = false;
			String packageName = rpi.getPackageName();
			Set<DeploymentUnit> packageContainingdeploymentUnits = deployment.getDeploymentUnitFromPackage(packageName);
			for (DeploymentUnit duz : packageContainingdeploymentUnits) {
				if (duz.getState() == Bundle.RESOLVED || duz.getState() == Bundle.ACTIVE) {
					Set<ProvidedPackageInfo> packages = duz.getProvidedPackages();
					for (ProvidedPackageInfo ppi : packages) {
						if (packageName.equals(ppi.getPackageName())) {
							if (ppi.getVersion().equals(rpi.getVersion())) {
								alreadyPresent = true;
							}
						}
					}
				}
				if (!alreadyPresent) {
					rpiList.add(rpi);
				}
			}

		}
		return rpiList;
	}

	/**
	 * @param c
	 * @return
	 * @throws DynamismException
	 */
	private boolean checkComponentForConfiguration(Component c) throws DynamismException {
		boolean res = false;

		res = checkDependencyForConfiguration(c.getDependencies(), dependency);
		res = res && checkPropertyForConfiguration(c.getParametersOfConfiguration(), properties);

		return res;
	}

	private boolean checkPropertyForConfiguration(Map<String, PropertyDeclaration> parametersOfConfiguration,
			List<ConfigurationProperty> properties) {
		// for (PropertyDeclaration pd : parametersOfConfiguration.values()) {
		// if (!pd.isOptional()) {
		// if (pd.defaultValue() == null) {
		//
		// }
		// }
		// }
		// return false;
		return true;
	}

	private boolean checkDependencyForConfiguration(Set<DependencyDeclaration> dependencyDeclarations,
			List<ConfigurationServiceDependency> dependency) {
		// boolean res = true;
		// for (ConfigurationServiceDependency sd : dependency) {
		// String id = sd.getName();
		// boolean exists = false;
		// for (DependencyDeclaration dd : dependencyDeclarations) {
		// if (id.equals(dd.getId())) {
		// exists = true;
		// }
		// }
		// if (exists == false) {
		// res = false;
		// }
		// }
		// return res;
		return true;
	}

	/**
	 * @param configuration
	 */
	private void initializeConfiguraitonProperties(Configuration configuration) {
		if (configuration != null) {
			System.out.println("Configuration not null extracting it");
			List conf = configuration.getProvidesOrDependencyOrDataPublish();
			for (Object o : conf) {
				if (o instanceof ConfigurationProperty) {
					properties.add((ConfigurationProperty) o);
				}
				if (o instanceof ConfigurationServiceDependency) {
					dependency.add((ConfigurationServiceDependency) o);
				}
				if (o instanceof ConfigurationServiceProviding) {
					providing.add((ConfigurationServiceProviding) o);
				}
				if (o instanceof ConfigurationDataPublishing) {
					publishing.add((ConfigurationDataPublishing) o);
				}
				if (o instanceof ConfigurationDataConsuming) {
					consuming.add((ConfigurationDataConsuming) o);
				}
			}
		}
	}

	@Override
	public void stateChanged(String instanceName, int newState) {
		// TODO
		System.out.println("handled ipojo " + instanceName + " changed state :" + newState);
		if (instances.contains(instanceName)) {
			if (context.getComponentState() == ComponentState.RESOLVED && newState == Instance.VALID) {
				context.setState(ComponentState.VALID);
			}
		}
	}
}
