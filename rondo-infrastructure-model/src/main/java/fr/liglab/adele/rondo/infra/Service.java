package fr.liglab.adele.rondo.infra;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class Service extends AbstractResource<Service> {


    public static Service service() {
        return new Service(null);
    }

    public static Service service(String name) {
        return new Service(name);
    }

    protected Service(String name) {
        super(name);
    }

    @Override
    protected Service self() {
        return this;
    }

}
