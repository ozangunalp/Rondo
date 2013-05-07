package fr.liglab.adele.rondo.infra.deployment.transaction.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/5/13
 * Time: 6:57 PM
 */
public class DeploymentTransactionImpl implements DeploymentTransaction {

    /**
     * Active
     */
    private static final int ACTIVE = 1;

    /**
     * Transaction termination started
     */
    private static final int TERMINATING = 2;

    /**
     * Transaction completed
     */
    private static final int TERMINATED = 3;

    private static final DeploymentException TIMEOUT = new DeploymentException("Timeout !");

    private final long m_id;
    private final String m_name;

    private final DeploymentCoordinatorImpl m_coordinator;
    private final LinkedList<DeploymentParticipant> m_participants;
    private final HashMap<String, Object> m_variables;

    private final Thread m_initiatorThread;

    private volatile int m_state;

    private long m_deadline;
    private TimerTask timeoutTask;

    private int prepareIndex;
    private Throwable m_failReason;

    public DeploymentTransactionImpl(long id, DeploymentCoordinatorImpl deploymentCoordinator, String name, int timeout) {
        this.m_id = id;
        this.m_coordinator = deploymentCoordinator;
        this.m_name = name;
        this.m_state = ACTIVE;

        this.m_participants = new LinkedList<DeploymentParticipant>();
        this.m_deadline = (timeout > 0) ? System.currentTimeMillis() + timeout : 0;
        this.m_variables = new HashMap<String, Object>();
        this.m_initiatorThread = Thread.currentThread();
    }

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
            this.prepareIndex = listIterator.nextIndex();
            listIterator.next().prepare();
        }
    }

    @Override
    public boolean fail(Throwable reason) {
        if (startTermination()) {
            this.m_failReason = reason;
            // reverse iterate over participants and do a clean up
            System.out.println("cleaning up from " + prepareIndex);
            ListIterator<DeploymentParticipant> listIterator = this.m_participants.listIterator(prepareIndex);
            while (listIterator.hasPrevious()) {
                listIterator.previous().cleanup();
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
            ListIterator<DeploymentParticipant> listIterator = this.m_participants.listIterator();
            while (listIterator.hasNext() && !commitFailure) {
                try {
                    listIterator.next().commit();
                } catch (Throwable t) {
                    t.printStackTrace();
                    commitFailure = true;
                }
            }
            if (commitFailure) {
                //TODO log something here
                System.out.println("Commit failed rolling back!");
                startTermination();
                while (listIterator.hasPrevious()) {
                    listIterator.previous().rollback();
                }
                // should decide where to clean up
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
            }

            if (time > 0) {
                this.m_deadline += time;
                scheduleTimeout(this.m_deadline);
            }
            return this.m_deadline;
        }
    }

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
        if (timeoutTask != null) {
            this.m_coordinator.schedule(timeoutTask, -1);
            timeoutTask = null;
        }

        if (deadline > System.currentTimeMillis()) {
            timeoutTask = new TimerTask() {
                @Override
                public void run() {
                    DeploymentTransactionImpl.this.timeout();
                }
            };
            this.m_coordinator.schedule(timeoutTask, deadline);
        }
    }
}
