package fr.liglab.adele.rondo.infra.deployment.transaction.impl;

import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentCoordinator;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/5/13
 * Time: 4:22 PM
 */
public class DeploymentCoordinatorImpl implements DeploymentCoordinator {

    private final AtomicLong m_ctr;

    //private final ThreadLocal<Stack<DeploymentTransaction>> m_stackThreadLocal;

    private final HashMap<Long, DeploymentTransactionImpl> m_transactions;

    private final Timer m_coordinationTimer;

    private long m_transactionTimeout;

    private long m_participantTimeout;

    public DeploymentCoordinatorImpl(Long transactionTimeout, Long participantTimeout) {
        this.m_transactionTimeout = transactionTimeout == null ? 10 * 60 * 1000L : transactionTimeout;
        this.m_participantTimeout = participantTimeout == null ? 10 * 30 * 1000L : participantTimeout;
        this.m_ctr = new AtomicLong();
        //this.m_stackThreadLocal = new ThreadLocal<Stack<DeploymentTransaction>>();
        this.m_transactions = new HashMap<Long, DeploymentTransactionImpl>();
        this.m_coordinationTimer = new Timer("Transaction Timer", true);
    }

    @Override
    public DeploymentTransaction create(String name, int timeout) {
        long id = m_ctr.incrementAndGet();
        DeploymentTransactionImpl newTransaction = new DeploymentTransactionImpl(id, this, name, timeout);
        m_transactions.put(id, newTransaction);
        return newTransaction;
    }

    @Override
    public void begin(String name, int timeout) {

//        Stack<DeploymentTransaction> transactionStack = m_stackThreadLocal.get();
//        if(transactionStack==null){
//            transactionStack = new Stack<DeploymentTransaction>();
//            m_stackThreadLocal.set(transactionStack);
//        }
//        transactionStack.push(this.create(name,timeout));
    }

    @Override
    public DeploymentTransaction getTransaction() { //TODO
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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


    void schedule(final TimerTask timerTask, final long deadline) {
        if (deadline < 0) {
            timerTask.cancel();
        } else {
            m_coordinationTimer.schedule(timerTask, new Date(deadline));
        }
    }

}
