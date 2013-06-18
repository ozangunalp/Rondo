package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Configuration;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:45 AM
 */
public class ConfigurationImpl extends AbstractResourceDeclaration<ConfigurationImpl> implements Configuration {

    public static ConfigurationImpl configuration() {
        return new ConfigurationImpl(null);
    }

    public static ConfigurationImpl configuration(String name) {
        return new ConfigurationImpl(name);
    }

    private ConfigurationImpl(String name) {
        super(name);
    }

    @Override
    protected ConfigurationImpl self() {
        return this;
    }

    @Override
    public Map<String, Object> extraProperties() {
        Map<String, Object> props = this.properties();
        props.remove("pid");
        props.remove("factory.pid");
        props.remove("location");
        return props;
    }

    @Override
    public String pid() {
        return (String) this.properties().get("pid");
    }

    @Override
    public String factoryPid() {
        return (String) this.properties().get("factoryPid");
    }

    @Override
    public String location() {
        return (String) this.properties().get("location");
    }

    public ConfigurationImpl pid(String pid){
        this.with("pid").setto(pid);
        return this;
    }

    public ConfigurationImpl factoryPid(String factoryPid){
        this.with("factoryPid").setto(factoryPid);
        return this;
    }

    public ConfigurationImpl location(String location){
        this.with("location").setto(location);
        return this;
    }
}
