package fr.liglab.adele.rondo.infra.impl;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class PackageImpl extends AbstractResource<PackageImpl> implements fr.liglab.adele.rondo.infra.model.Package {

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
}
