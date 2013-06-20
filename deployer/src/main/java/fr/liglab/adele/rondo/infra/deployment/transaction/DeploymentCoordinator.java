package fr.liglab.adele.rondo.infra.deployment.transaction;

import java.util.List;

/**
 * Coordinator for transactions
 */
public interface DeploymentCoordinator {

    /**
     * Create a new transaction
     * @param name transaction name
     * @param timeout time out of the new transaction
     * @param threaded if transaction will be parallel
     * @return deployment transaction
     */
    DeploymentTransaction create(String name, int timeout, boolean threaded);

    /**
     * @return current transaction
     */
    DeploymentTransaction getTransaction();

    /**
     * @return all transactions
     */
    List<DeploymentTransaction> getTransactions();

    /**
     * @param id transaction id
     * @return transaction by its id
     */
    DeploymentTransaction getTransaction(long id);

}
