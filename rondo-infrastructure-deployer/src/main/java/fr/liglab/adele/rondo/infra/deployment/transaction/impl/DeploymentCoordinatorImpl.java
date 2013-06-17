package fr.liglab.adele.rondo.infra.deployment.transaction.impl;

import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentCoordinator;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import org.apache.felix.ipojo.util.Log;
import org.osgi.service.log.LogService;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * An implementation of the coordiator
 */
public class DeploymentCoordinatorImpl implements DeploymentCoordinator {

    /**
     * Transaction id dounter
     */
    private final AtomicLong m_ctr;

    /**
     * Transaction by id
     */
    private final LinkedHashMap<Long, DeploymentTransactionImpl> m_transactions;

    /**
     * Timer for scheduling transaction timeouts
     */
    private final Timer m_coordinationTimer;

    /**
     * Logger
     */
    private final LogService m_logger;

    /**
     * Constructor
     * @param logger logger passed to transactions
     */
    public DeploymentCoordinatorImpl(LogService logger) {
        this.m_ctr = new AtomicLong();
        this.m_transactions = new LinkedHashMap<Long, DeploymentTransactionImpl>();
        this.m_coordinationTimer = new Timer("Transaction Timer", true);
        this.m_logger = logger;
    }

    @Override
    public DeploymentTransaction create(String name, int timeout) {
        long id = m_ctr.incrementAndGet();
        DeploymentTransactionImpl newTransaction = new DeploymentTransactionImpl(id, this, name, timeout);
        m_transactions.put(id, newTransaction);
        return newTransaction;
    }

    @Override
    public DeploymentTransaction getTransaction() { //TODO
        //
        return null;
    }

    @Override
    public List<DeploymentTransaction> getTransactions() {
        ArrayList<DeploymentTransaction> transactions = new ArrayList<DeploymentTransaction>();
        transactions.addAll(this.m_transactions.values());
        return transactions;
    }

    @Override
    public DeploymentTransaction getTransaction(long id) {
        return this.m_transactions.get(id);
    }

    /**
     *
     * @param timerTask
     * @param deadline
     */
    void schedule(final TimerTask timerTask, final long deadline) {
        if (deadline < 0) {
            timerTask.cancel();
        } else {
            m_coordinationTimer.schedule(timerTask, new Date(deadline));
        }
    }

    /**
     *
     * @param logLevel
     * @param message
     * @param transaction
     */
    void log(int logLevel, String message, DeploymentTransaction transaction){
        this.m_logger.log(logLevel,"Transaction["+transaction.getId()+"-"+transaction.getName()+"] : "+message);
    }

    /**
     *
     * @param logLevel
     * @param message
     * @param exception
     * @param transaction
     */
    void log(int logLevel, String message, Throwable exception, DeploymentTransaction transaction){
        this.m_logger.log(logLevel,"Transaction["+transaction.getId()+"-"+transaction.getName()+"] : "+message, exception);
    }

}
