package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Instance;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class InstanceImpl extends AbstractResource<InstanceImpl> implements Instance {

    public static InstanceImpl instance() {
        return new InstanceImpl(null);
    }

    public static InstanceImpl instance(String name) {
        return new InstanceImpl(name);
    }

    protected InstanceImpl(String name) {
        super(name);
    }

    @Override
    protected InstanceImpl self() {
        return this;
    }
}
