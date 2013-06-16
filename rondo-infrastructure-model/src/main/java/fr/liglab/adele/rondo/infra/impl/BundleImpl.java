package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Bundle;
import org.osgi.framework.Constants;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class BundleImpl extends AbstractResourceDeclaration<BundleImpl> implements Bundle {

    public static BundleImpl bundle() {
        return new BundleImpl(null);
    }

    public static BundleImpl bundle(String name) {
        return new BundleImpl(name);
    }

    private BundleImpl(String name) {
        super(name);
    }

    @Override
    protected BundleImpl self() {
        return this;
    }

    public Map<String, Object> extraProperties() {
        Map<String, Object> props = this.properties();
        props.remove("source");
        props.remove(Constants.BUNDLE_SYMBOLICNAME);
        props.remove(Constants.BUNDLE_VERSION);
        return props;
    }

    public String source() {
        return (String) this.properties().get("source");
    }

    public String symbolicName() {
        return (String) this.properties().get(Constants.BUNDLE_SYMBOLICNAME);
    }

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

}
