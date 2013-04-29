package fr.liglab.adele.rondo.infra;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class Bundle extends AbstractResource<Bundle> {

    private String source;

    public static Bundle bundle() {
        return new Bundle(null);
    }

    public static Bundle bundle(String name) {
        return new Bundle(name);
    }

    private Bundle(String name) {
        super(name);
    }

    public String source() {
        return source;
    }

    public Bundle source(String source) {
        this.source = source;
        return this;
    }

    @Override
    protected Bundle self() {
        return this;
    }

}
