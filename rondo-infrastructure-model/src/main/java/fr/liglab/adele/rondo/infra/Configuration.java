package fr.liglab.adele.rondo.infra;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:45 AM
 */
public class Configuration extends AbstractResource<Configuration> {

    public static Configuration configuration() {
        return new Configuration(null);
    }

    public static Configuration configuration(String name) {
        return new Configuration(name);
    }

    private Configuration(String name) {
        super(name);
    }

    @Override
    protected Configuration self() {
        return this;
    }

}
