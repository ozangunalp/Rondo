package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Configuration;

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

}
