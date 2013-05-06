package fr.liglab.adele.rondo.infra.model;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 4:17 PM
 */
public interface ResourceDeclaration {

    public String name();

    public String state();

    public Map<String, Object> properties();

}
