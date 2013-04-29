package fr.liglab.adele.rondo.infra;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class Package extends AbstractResource<Package> {


    public Package(String name) {
        super(name);
    }

    public static Package aPackage() {
        return new Package(null);
    }

    public static Package aPackage(String name) {
        return new Package(name);
    }

    @Override
    protected Package self() {
        return this;
    }
}
