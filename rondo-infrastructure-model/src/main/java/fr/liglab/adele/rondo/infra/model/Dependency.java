package fr.liglab.adele.rondo.infra.model;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 4:00 PM
 */
public interface Dependency {

    public ResourceReference provider();

    public ResourceReference requirer();

}
