package fr.liglab.adele.rondo.infra.deployment.transaction.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import org.apache.felix.ipojo.util.Log;

import java.util.*;

/**
 * An implementation of the deployment transaction
 */
public class DeploymentTransactionImpl implements DeploymentTransaction {

    /**
     * Transaction id
     */
    private final long m_id;

    /**
     * Transaction name
     */
    private final String m_name;

    /**
     * Coordinator reference
     */
    private final DeploymentCoordinatorImpl m_coordinator;

    /**
     * Participants list
     */
    private final LinkedList<DeploymentParticipant> m_participants;

    /**
     * Properties of this transacation
     */
    private final HashMap<String, Object> m_variables;

    /**
     * Initiator thread
     */
    private final Thread m_initiatorThread;

    /**
     * Transaction state
     */
    private volatile int m_state;

    /**
     * Deadline set by the timeout
     */
    private long m_deadline;

    /**
     * Task to call when timeout deadline is up
     */
    private TimerTask m_timeoutTask;

    /**
     * Prepare index
     */
    private int m_prepareIndex;

    /**
     * Fail reason
     */
    private Throwable m_failReason;

    /**
     * Constructor
     * @param id id of the transaction
     * @param deploymentCoordinator coordinator to which this transaction is bound to
     * @param name name of the transaction
     * @param timeout timeout
     */
    public DeploymentTransactionImpl(long id, DeploymentCoordinatorImpl deploymentCoordinator, String name, int timeout) {
        this.m_id = id;
        this.m_coordinator = deploymentCoordinator;
        this.m_name = name;
        this.m_state = ACTIVE;

        this.m_participants = new LinkedList<DeploymentParticipant>();
        this.m_deadline = (timeout > 0) ? System.currentTimeMillis() + timeout : 0;
        this.m_variables = new HashMap<String, Object>();
        this.m_initiatorThread = Thread.currentThread();
        this.scheduleTimeout(m_deadline);
    }

    // DeploymentTransaction Methods
    // =================================================================================================================

    @Override
    public long getId() {
        return this.m_id;
    }

    @Override
    public String getName() {
        return this.m_name;
    }

    @Override
    public void prepare() throws DeploymentException {
        ListIterator<DeploymentParticipant> listIterator = this.m_participants.listIterator();
        while (listIterator.hasNext()) {
            // store the index of to be prepared participant
            this.m_prepareIndex = listIterator.nextIndex();
            DeploymentParticipant nextParticipant = listIterator.next();
            m_coordinator.log(Log.DEBUG,"Preparing participant: "+nextParticipant.getParticipantId(),this);
            nextParticipant.prepare();
        }
    }

    @Override
    public boolean fail(Throwable reason) {
        if (startTermination()) {
            this.m_failReason = reason;
            // reverse iterate over participants and do a clean up
            m_coordinator.log(Log.DEBUG, "Cleaning from " + m_prepareIndex, this);
            ListIterator<DeploymentParticipant> listIterator = this.m_participants.listIterator(m_prepareIndex);
            while (listIterator.hasPrevious()) {
                DeploymentParticipant previousParticipant = listIterator.previous();
                m_coordinator.log(Log.DEBUG, "Cleaning participant: " + previousParticipant.getParticipantId(), this);
                previousParticipant.cleanup();
            }
            m_state = TERMINATED;
            synchronized (this) {
                this.notifyAll();
            }
        }
        return false;
    }


    @Override
    public void end() throws DeploymentException {
        if (startTermination()) {
            boolean commitFailure = false;
            ListIterator<DeploymentParticipant> listIterator = this.m_participants.listIterator(0);
            while (listIterator.hasNext() && !commitFailure) {
                try {
                    DeploymentParticipant nextParticipant = listIterator.next();
                    m_coordinator.log(Log.DEBUG, "Commiting participant: " + nextParticipant.getParticipantId(), this);
                    nextParticipant.commit();
                } catch (Throwable t) {
                    this.m_failReason = t;
                    commitFailure = true;
                }
            }
            if (commitFailure) {
                m_coordinator.log(Log.DEBUG, "Commit failed, rolling back", this);
                startTermination();
                while (listIterator.hasPrevious()) {
                    DeploymentParticipant previousParticipant = listIterator.previous();
                    m_coordinator.log(Log.DEBUG, "Rolling back participant: " + previousParticipant.getParticipantId(), this);
                    try{
                        previousParticipant.rollback();
                    }catch (Throwable t){
                        m_coordinator.log(Log.DEBUG, "Failed to roll back participant: " + previousParticipant.getParticipantId(), this);
                    }
                }
                // TODO should decide where to clean up
                throw new DeploymentException("Transaction error on commit, rolled back to initial state");
            }
            this.m_state = TERMINATED;
        } else {
            throw new DeploymentException("Already terminated");
        }
    }

    @Override
    public List<DeploymentParticipant> getParticipants() {
        synchronized (this) {
            if (m_state == ACTIVE)
                return new LinkedList<DeploymentParticipant>(m_participants);
        }
        return Collections.emptyList();
    }

    @Override
    public void addParticipant(DeploymentParticipant participant) {
        synchronized (this) {
            if (isTerminated()) {
                throw new RuntimeException();
            }
            if (!this.m_participants.contains(participant)) {
                this.m_participants.add(participant);
            }
        }
    }

    @Override
    public Throwable getFailure() {
        return this.m_failReason;
    }

    @Override
    public Object store(String key, Object value) {
        return this.m_variables.put(key, value);
    }

    @Override
    public Object get(String key) {
        return this.m_variables.get(key);
    }

    @Override
    public Thread getThread() {
        return this.m_initiatorThread;
    }

    @Override
    public boolean isTerminated() {
        return this.m_state != ACTIVE;
    }

    @Override
    public long extendTimeout(long time) {
        synchronized (this) {
            if (isTerminated()) {
                //TODO
                m_coordinator.log(Log.WARNING, "Already terminated", this);
            }

            if (time > 0) {
                this.m_deadline += time;
                scheduleTimeout(this.m_deadline);
            }
            return this.m_deadline;
        }
    }

    // Util Methods
    // =================================================================================================================


    void timeout() {
        // Fail the Coordination upon timeout
        fail(TIMEOUT);
    }

    private boolean startTermination() {
        if (this.m_state == ACTIVE) {
            this.m_state = TERMINATING;
            this.scheduleTimeout(-1);
            return true;
        }
        // this coordination is not active any longer, nothing to do
        return false;
    }

    private void scheduleTimeout(final long deadline) {
        if (m_timeoutTask != null) {
            this.m_coordinator.schedule(m_timeoutTask, -1);
            m_timeoutTask = null;
        }

        if (deadline > System.currentTimeMillis()) {
            m_timeoutTask = new TimerTask() {
                @Override
                public void run() {
                    DeploymentTransactionImpl.this.timeout();
                }
            };
            this.m_coordinator.schedule(m_timeoutTask, deadline);
        }
    }
}
