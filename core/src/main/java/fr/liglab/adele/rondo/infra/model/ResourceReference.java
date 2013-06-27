package fr.liglab.adele.rondo.infra.model;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 4:01 PM
 */
public interface ResourceReference<T extends ResourceDeclaration> {

    public <A extends ResourceDeclaration> ResourceReference<A> adapt(Class<A> clazz);

    public Class<T> type();

    public String id();

    public Dependency[] dependencies();

    public Dependency[] providings();

}
