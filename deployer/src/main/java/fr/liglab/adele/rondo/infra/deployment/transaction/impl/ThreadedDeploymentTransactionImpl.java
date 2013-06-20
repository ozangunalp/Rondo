package fr.liglab.adele.rondo.infra.deployment.transaction.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import org.apache.felix.ipojo.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/20/13
 * Time: 9:46 PM
 */
public class ThreadedDeploymentTransactionImpl extends DeploymentTransactionImpl {

    ExecutorService m_executor;
    /**
     * Constructor
     *
     * @param id                    id of the transaction
     * @param deploymentCoordinator coordinator to which this transaction is bound to
     * @param name                  name of the transaction
     * @param timeout               timeout
     */
    public ThreadedDeploymentTransactionImpl(long id, DeploymentCoordinatorImpl deploymentCoordinator, String name, int timeout) {
        super(id, deploymentCoordinator, name, timeout);
        m_executor = Executors.newCachedThreadPool();
    }

    @Override
    public void prepare() throws DeploymentException {
        List<Future<DeploymentException>> futures;
        ArrayList<Callable<DeploymentException>> callables = new ArrayList<Callable<DeploymentException>>();
        ListIterator<DeploymentParticipant> listIterator = this.m_participants.listIterator();
        while (listIterator.hasNext()) {
            // store the index of to be prepared participant
            final DeploymentParticipant nextParticipant = listIterator.next();
            this.m_coordinator.log(Log.DEBUG,"Adding participant for prepare: "+nextParticipant.getParticipantId(),this);
            Callable<DeploymentException> c = new Callable<DeploymentException>() {
                @Override
                public DeploymentException call(){
                    try{
                        nextParticipant.prepare();
                    } catch (DeploymentException e) {
                        return e;
                    }
                    return null;
                }
            };
            callables.add(c);
        }
        try {
            futures = m_executor.invokeAll(callables);
        } catch (InterruptedException e) {
            m_coordinator.log(Log.DEBUG, "Interrupted", this);
            throw new DeploymentException("Prepare execution interrupted");
        }
        Iterator<Future<DeploymentException>> iterator = futures.iterator();
        DeploymentException firstException = null;
        while(iterator.hasNext() && firstException==null){
            try {
                Future<DeploymentException> next = iterator.next();
                firstException = next.get();
            } catch (Exception e) {
                // print stack trace
                e.printStackTrace();
            }
        }
        if(firstException!=null){
            throw firstException;
        }
    }

    @Override
    public boolean fail(Throwable reason) {
        if (startTermination()) {
            this.m_failReason = reason;
            List<Future<DeploymentException>> futures;
            // call cleanup to all participants
            ListIterator<DeploymentParticipant> listIterator = this.m_participants.listIterator();
            while (listIterator.hasNext()) {
                // store the index of to be prepared participant
                final DeploymentParticipant nextParticipant = listIterator.next();
                this.m_coordinator.log(Log.DEBUG,"Adding participant for prepare: "+
                        nextParticipant.getParticipantId(),this);
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        nextParticipant.cleanup();
                    }
                };
                m_executor.execute(r);
            }
            m_state = TERMINATED;
            synchronized (this) {
                this.notifyAll();
            }
        }
        return false;
    }
}
