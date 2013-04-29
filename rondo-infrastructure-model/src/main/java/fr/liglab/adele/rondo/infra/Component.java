package fr.liglab.adele.rondo.infra;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:52 AM
 */
public class Component extends AbstractResource<Component> {

    public static Component component() {
        return new Component(null);
    }

    public static Component component(String name) {
        return new Component(name);
    }

    private Component(String name) {
        super(name);
    }

    @Override
    protected Component self() {
        return this;
    }
}
