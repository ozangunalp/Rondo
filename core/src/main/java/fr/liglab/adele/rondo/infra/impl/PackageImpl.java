package fr.liglab.adele.rondo.infra.impl;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class PackageImpl extends AbstractResourceDeclaration<PackageImpl> implements fr.liglab.adele.rondo.infra.model.Package {

    public PackageImpl(String name) {
        super(name);
    }

    public static PackageImpl aPackage() {
        return new PackageImpl(null);
    }

    public static PackageImpl aPackage(String name) {
        return new PackageImpl(name);
    }

    @Override
    protected PackageImpl self() {
        return this;
    }

    @Override
    public Map<String, Object> extraProperties() {
        Map<String, Object> props = this.properties();
        props.remove("version");
        return props;
    }

    @Override
    public String version() {
        return (String) this.properties().get("version");
    }

    public PackageImpl version(String version) {
        this.with("version").setto(version);
        return this;
    }

    @Override
    public String filter() {
        return (String) this.properties().get("filter");
    }

    public PackageImpl filter(String filter) {
        this.with("filter").setto(filter);
        return this;
    }

}
