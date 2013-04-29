package fr.liglab.adele.rondo.infra;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:55 AM
 */
public class File extends AbstractResource<File> {

    private String source;

    private String template;

    public static File file() {
        return new File(null);
    }

    public static File file(String name) {
        return new File(name);
    }

    private File(String name) {
        super(name);
    }

    public String source() {
        return source;
    }

    public String template() {
        return template;
    }

    public File source(String source) {
        this.source = source;
        return this;
    }

    public File template(String template) {
        this.template = template;
        return this;
    }

    @Override
    protected File self() {
        return this;
    }

}
