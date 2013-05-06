package fr.liglab.adele.rondo.infra.impl;

import fr.liglab.adele.rondo.infra.model.DeploymentPackage;

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

    public String source() {
        return (String) this.properties().get("source");
    }

    public DeploymentPackageImpl source(String source) {
        this.with("source").setto(source);
        return this;
    }

    @Override
    protected DeploymentPackageImpl self() {
        return this;
    }

}
