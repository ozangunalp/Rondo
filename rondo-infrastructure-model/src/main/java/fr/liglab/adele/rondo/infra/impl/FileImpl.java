package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.File;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:55 AM
 */
public class FileImpl extends AbstractResourceDeclaration<FileImpl> implements File {

    private String source;

    private String template;

    public static FileImpl file() {
        return new FileImpl(null);
    }

    public static FileImpl file(String name) {
        return new FileImpl(name);
    }

    private FileImpl(String name) {
        super(name);
    }

    public String source() {
        return (String) this.properties().get("source");
    }

    public String template() {
        return (String) this.properties().get("template");
    }

    public FileImpl source(String source) {
        this.with("source").setto(source);
        return this;
    }

    public FileImpl template(String template) {
        this.with("template").setto(template);
        return this;
    }

    @Override
    protected FileImpl self() {
        return this;
    }

}
