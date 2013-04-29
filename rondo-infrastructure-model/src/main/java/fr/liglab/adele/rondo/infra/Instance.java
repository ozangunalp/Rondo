package fr.liglab.adele.rondo.infra;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class Instance extends AbstractResource<Instance> {

    public static Instance instance() {
        return new Instance(null);
    }

    public static Instance instance(String name) {
        return new Instance(name);
    }

    protected Instance(String name) {
        super(name);
    }

    @Override
    protected Instance self() {
        return this;
    }
}
