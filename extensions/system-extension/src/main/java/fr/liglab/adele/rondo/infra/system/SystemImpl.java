package fr.liglab.adele.rondo.infra.system;

import fr.liglab.adele.rondo.infra.impl.AbstractResourceDeclaration;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/13/13
 * Time: 4:31 PM
 */
public class SystemImpl  extends AbstractResourceDeclaration<SystemImpl> implements System {

    public static SystemImpl system() {
        return new SystemImpl(null);
    }

    public static SystemImpl bundle(String name) {
        return new SystemImpl(name);
    }

    private SystemImpl(String name) {
        super(name);
    }

    @Override
    protected SystemImpl self() {
        return this;

    }
    @Override
    public Map<String, Object> extraProperties() {
        Map<String, Object> properties = super.properties();
        return properties;
    }

}
