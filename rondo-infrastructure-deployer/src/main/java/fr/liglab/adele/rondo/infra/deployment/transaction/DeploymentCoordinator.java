package fr.liglab.adele.rondo.infra.deployment.transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/3/13
 * Time: 11:28 AM
 */
public interface DeploymentCoordinator {

    DeploymentTransaction create(String name, int timeout);

    void begin(String name, int timeout);

    DeploymentTransaction getTransaction();

    List<DeploymentTransaction> getTransactions();

    DeploymentTransaction getTransaction(long id);

}
