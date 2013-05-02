package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class BundleImpl extends AbstractResource<BundleImpl> implements Bundle {

    private String source;

    public static BundleImpl bundle() {
        return new BundleImpl(null);
    }

    public static BundleImpl bundle(String name) {
        return new BundleImpl(name);
    }

    private BundleImpl(String name) {
        super(name);
    }

    public String source() {
        return source;
    }

    public BundleImpl source(String source) {
        this.source = source;
        return this;
    }

    @Override
    protected BundleImpl self() {
        return this;
    }

}
