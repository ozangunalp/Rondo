package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Instance;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class InstanceImpl extends AbstractResourceDeclaration<InstanceImpl> implements Instance {

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

    @Override
    public String factory() {
        return (String) this.properties().get("factory.name");
    }

    public String factoryVersion() {
        return (String) this.properties().get("factory.version");
    }

    public InstanceImpl factory(String factory) {
        this.with("factory.name").setto(factory);
        return this;
    }

    public InstanceImpl factoryVersion(String version) {
        this.with("factory.version").setto(version);
        return this;
    }
}
