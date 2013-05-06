package fr.liglab.adele.rondo.infra.model;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 4:16 PM
 */
public interface Bundle extends ResourceDeclaration {

    public String source();

    public String symbolicName();

    public String version();

}
