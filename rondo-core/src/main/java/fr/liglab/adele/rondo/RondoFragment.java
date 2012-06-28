package fr.liglab.adele.rondo;

import java.util.Set;

import fr.liglab.adele.rondo.model.ApplicationFragment;

public interface RondoFragment {

	String getName();

	ApplicationFragment getFragmentModel();

	void activate();

	void deactivate();

	void resolve();

	void dispose();

	Set<RondoComponent> getComponents();

	void addFragmentStateListener(FragmentStateListener listener);

	void removeFragmentStateListener(FragmentStateListener listener);

}
