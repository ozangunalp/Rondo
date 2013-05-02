package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Component;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:52 AM
 */
public class ComponentImpl extends AbstractResource<ComponentImpl> implements Component {

    public static ComponentImpl component() {
        return new ComponentImpl(null);
    }

    public static ComponentImpl component(String name) {
        return new ComponentImpl(name);
    }

    private ComponentImpl(String name) {
        super(name);
    }

    @Override
    protected ComponentImpl self() {
        return this;
    }
}
