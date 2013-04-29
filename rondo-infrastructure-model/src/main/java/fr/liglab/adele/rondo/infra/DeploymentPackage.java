package fr.liglab.adele.rondo.infra;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/25/13
 * Time: 10:52 AM
 */
public class DeploymentPackage extends AbstractResource<DeploymentPackage> {

    private String source;

    public static DeploymentPackage deploymentPackage() {
        return new DeploymentPackage(null);
    }

    public static DeploymentPackage deploymentPackage(String name) {
        return new DeploymentPackage(name);
    }

    private DeploymentPackage(String name) {
        super(name);
    }

    public String source() {
        return source;
    }

    public DeploymentPackage source(String source) {
        this.source = source;
        return this;
    }

    @Override
    protected DeploymentPackage self() {
        return this;
    }

}
