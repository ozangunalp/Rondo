package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Service;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class ServiceImpl extends AbstractResource<ServiceImpl> implements Service {

    public static ServiceImpl service() {
        return new ServiceImpl(null);
    }

    public static ServiceImpl service(String name) {
        return new ServiceImpl(name);
    }

    protected ServiceImpl(String name) {
        super(name);
    }

    @Override
    protected ServiceImpl self() {
        return this;
    }

}
