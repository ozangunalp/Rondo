package fr.liglab.adele.rondo.infra.model;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 4:01 PM
 */
public interface ResourceReference<T extends ResourceDeclaration> {

    public ResourceReference<T> adapt(Class<T> clazz);

    public Class<T> type();

    public String id();

}
