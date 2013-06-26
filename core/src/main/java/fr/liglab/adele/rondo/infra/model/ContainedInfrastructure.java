package fr.liglab.adele.rondo.infra.model;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/25/13
 * Time: 9:29 AM
 */
public interface ContainedInfrastructure {

    public Infrastructure getInfrastructure();

    public Set<Condition> getConditions();

    String getName();
}
