package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Bundle;
import org.osgi.framework.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class BundleImpl extends AbstractResourceDeclaration<BundleImpl> implements Bundle {

    private String source;

    private String symbolicName;

    private String version;

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
        return (String) this.properties().get("source");
    }

    @Override
    public String symbolicName() {
        return (String) this.properties().get(Constants.BUNDLE_SYMBOLICNAME);
    }

    @Override
    public String version() {
        return (String) this.properties().get(Constants.BUNDLE_VERSION);
    }

    public BundleImpl source(String source) {
        this.with("source").setto(source);
        return this;
    }

    public BundleImpl symbolicName(String symbolicName) {
        this.with(Constants.BUNDLE_SYMBOLICNAME).setto(symbolicName);
        return this;
    }

    public BundleImpl version(String version) {
        this.with(Constants.BUNDLE_VERSION).setto(version);
        return this;
    }

    @Override
    protected BundleImpl self() {
        return this;
    }

}
