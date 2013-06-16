package fr.liglab.adele.rondo.infra.deployment.transaction;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/3/13
 * Time: 11:27 AM
 */
public interface DeploymentTransaction {

    long getId();

    String getName();

    void prepare() throws DeploymentException;

    boolean fail(Throwable reason);

    void end() throws DeploymentException;

    List<DeploymentParticipant> getParticipants();

    void addParticipant(DeploymentParticipant participant);

    Throwable getFailure();

    Object store(String key, Object value);

    Object get(String key);

    Thread getThread();

    boolean isTerminated();

    long extendTimeout(long time);

}
