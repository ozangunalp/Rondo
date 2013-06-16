package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.DeploymentPackage;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:52 AM
 */
public class DeploymentPackageImpl extends AbstractResourceDeclaration<DeploymentPackageImpl> implements DeploymentPackage {

    private String source;

    public static DeploymentPackageImpl deploymentPackage() {
        return new DeploymentPackageImpl(null);
    }

    public static DeploymentPackageImpl deploymentPackage(String name) {
        return new DeploymentPackageImpl(name);
    }

    private DeploymentPackageImpl(String name) {
        super(name);
    }

    @Override
    protected DeploymentPackageImpl self() {
        return this;
    }

    @Override
    public Map<String, Object> extraProperties() {
        Map<String, Object> props = this.properties();
        props.remove("source");
        return props;
    }

    @Override
    public String source() {
        return (String) this.properties().get("source");
    }

    public DeploymentPackageImpl source(String source) {
        this.with("source").setto(source);
        return this;
    }

}
