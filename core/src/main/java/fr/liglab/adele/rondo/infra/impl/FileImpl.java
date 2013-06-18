package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.File;

import java.util.Map;

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

    @Override
    protected FileImpl self() {
        return this;
    }

    @Override
    public Map<String, Object> extraProperties() {
        Map<String, Object> props = this.properties();
        props.remove("source");
        props.remove("template");
        props.remove("path");
        props.remove("owner");
        return props;
    }

    @Override
    public String source() {
        return (String) this.properties().get("source");
    }

    @Override
    public String template() {
        return (String) this.properties().get("template");
    }

    @Override
    public String path() {
        return (String) this.properties().get("path");
    }

    @Override
    public String owner() {
        return (String) this.properties().get("owner");
    }

    @Override
    public Boolean executable() {
        return (Boolean) this.properties().get("executable");
    }

    @Override
    public Boolean readable() {
        return (Boolean) this.properties().get("readable");
    }

    @Override
    public Boolean writable() {
        return (Boolean) this.properties().get("writable");
    }

    public FileImpl source(String source) {
        this.with("source").setto(source);
        return this;
    }

    public FileImpl template(String template) {
        this.with("template").setto(template);
        return this;
    }

    public FileImpl path(String path) {
        this.with("path").setto(path);
        return this;
    }

    public FileImpl owner(String owner) {
        this.with("owner").setto(owner);
        return this;
    }

    public FileImpl executable(boolean executable){
        this.with("executable").setto(executable);
        return this;
    }

    public FileImpl readable(boolean readable){
        this.with("readable").setto(readable);
        return this;
    }

    public FileImpl writable(boolean writable){
        this.with("writable").setto(writable);
        return this;
    }

}
