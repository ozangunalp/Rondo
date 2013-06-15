package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.Service;
import org.osgi.framework.Constants;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:44 AM
 */
public class ServiceImpl extends AbstractResourceDeclaration<ServiceImpl> implements Service {

    private String pid;
    private List<String> objectClass;

    public static ServiceImpl service() {
        return new ServiceImpl(null);
    }

    public static ServiceImpl service(String name) {
        return new ServiceImpl(name);
    }

    protected ServiceImpl(String name) {
        super(name);
    }

    @Override
    protected ServiceImpl self() {
        return this;
    }

    @Override
    public Map<String, Object> extraProperties() {
        Map<String, Object> props = this.properties();
        props.remove(Constants.OBJECTCLASS);
        props.remove(Constants.SERVICE_PID);
        props.remove("filter");
        return props;
    }

    @Override
    public List<String> objectClass() {
        return (List<String>) this.properties().get(Constants.OBJECTCLASS);
    }

    @Override
    public String pid() {
        return (String) this.properties().get(Constants.SERVICE_PID);
    }

    @Override
    public String filter() {
        return (String) this.properties().get("filter");
    }

    public ServiceImpl objectClass(List<String> objectClass) {
        this.with(Constants.OBJECTCLASS).setto(objectClass);
        return this;
    }

    public ServiceImpl pid(String pid) {
        this.with(Constants.SERVICE_PID).setto(pid);
        return this;
    }

    public ServiceImpl filter(String filter) {
        this.with("filter").setto(filter);
        return this;
    }


}
