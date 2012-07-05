package fr.liglab.adele.rondo;

import java.util.Set;

import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.model.ApplicationFragment;
import fr.liglab.adele.rondo.model.Component;

public interface RondoFragmentContext {

	String getName();

	ApplicationFragment getFragmentModel();

	FragmentState getFragmentState();

	void activate();

	void deactivate();

	void resolve();

	void dispose();

	RondoComponentContext createComponent(Component model) throws InvalidConfigurationException;

	void destroyComponent(RondoComponentContext componentContext);

	Set<RondoComponentContext> getComponents();

	void addFragmentStateListener(FragmentStateListener listener);

	void removeFragmentStateListener(FragmentStateListener listener);

	public enum FragmentState {
		INACTIVE, RESOLVED, STARTING, STOPPING, ACTIVE
	}

}
