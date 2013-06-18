package fr.liglab.adele.rondo.infra.deployment.transaction;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;

import java.util.List;

/**
 * Deployment transaction
 */
public interface DeploymentTransaction {

    /**
     * Active
     */
    public static final int ACTIVE = 1;

    /**
     * Transaction termination started
     */
    public static final int TERMINATING = 2;

    /**
     * Transaction completed
     */
    public static final int TERMINATED = 3;

    /**
     * Timeout exception
     */
    public static final DeploymentException TIMEOUT = new DeploymentException("Timeout !");

    /**
     * @return transaction id
     */
    long getId();

    /**
     * @return name attributed to this transcation
     */
    String getName();

    /**
     * Prepare the transaction.
     * Prepare all participants.
     * @throws DeploymentException participant exception
     */
    void prepare() throws DeploymentException;

    /**
     * Fail the transaction.
     * Cleanup all participants.
     * @param reason
     * @return
     */
    boolean fail(Throwable reason);

    /**
     * End the transaction.
     * Commit resource changes.
     * @throws DeploymentException
     */
    void end() throws DeploymentException;

    /**
     * @return participants
     */
    List<DeploymentParticipant> getParticipants();

    /**
     * Add participant
     * @param participant
     */
    void addParticipant(DeploymentParticipant participant);

    /**
     * Get Failure if this transaction has failed
     * @return failure
     */
    Throwable getFailure();

    /**
     * Store properties for this transaction.
     * @param key key
     * @param value value
     * @return old value
     */
    Object store(String key, Object value);

    /**
     * Get stored property value
     * @param key key
     * @return value, may return {@literal null} if key does not exist
     */
    Object get(String key);

    /**
     * @return thread that created this transaction
     */
    Thread getThread();

    /**
     * Is this transaction terminated.
     * @return
     */
    boolean isTerminated();

    /**
     * Participants can extend the timeout of the transaction
     * @param time
     * @return
     */
    long extendTimeout(long time);

}
