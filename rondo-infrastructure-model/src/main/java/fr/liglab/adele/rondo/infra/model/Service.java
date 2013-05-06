package fr.liglab.adele.rondo.infra.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/29/13
 * Time: 4:27 PM
 */
public interface Service extends ResourceDeclaration {

    public List<String> objectClass();

    public String pid();

}
