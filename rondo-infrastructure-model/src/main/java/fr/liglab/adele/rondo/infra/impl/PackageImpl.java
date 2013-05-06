package fr.liglab.adele.rondo.infra.impl;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class PackageImpl extends AbstractResourceDeclaration<PackageImpl> implements fr.liglab.adele.rondo.infra.model.Package {

    private String version;

    public PackageImpl(String name) {
        super(name);
    }

    public static PackageImpl aPackage() {
        return new PackageImpl(null);
    }

    public static PackageImpl aPackage(String name) {
        return new PackageImpl(name);
    }

    public PackageImpl version(String version) {
        this.with("version").setto(version);
        return this;
    }

    @Override
    public String version() {
        return (String) this.properties().get("version");
    }

    @Override
    protected PackageImpl self() {
        return this;
    }

}
