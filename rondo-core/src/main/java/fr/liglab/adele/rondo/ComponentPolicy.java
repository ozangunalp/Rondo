package fr.liglab.adele.rondo;

public interface ComponentPolicy {

	public void activate();

	public void deactivate();

	public void resolve();

	public void dispose();

}
