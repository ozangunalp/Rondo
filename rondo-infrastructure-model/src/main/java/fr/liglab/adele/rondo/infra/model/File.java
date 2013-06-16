package fr.liglab.adele.rondo.infra.model;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 4:25 PM
 */
public interface File extends ResourceDeclaration {

    public String source();

    public String template();

    public String path();

    public String owner();

    public Boolean readable();

    public Boolean writable();

    public Boolean executable();

}
