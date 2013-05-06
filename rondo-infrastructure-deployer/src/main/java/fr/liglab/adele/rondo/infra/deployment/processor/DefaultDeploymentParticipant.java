package fr.liglab.adele.rondo.infra.deployment.processor;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/3/13
 * Time: 12:50 AM
 */
public class DefaultDeploymentParticipant implements DeploymentParticipant {

    DeploymentTransaction m_coordination;

    public DefaultDeploymentParticipant(DeploymentTransaction m_coordination) {
        this.m_coordination = m_coordination;
    }

    public void prepare() throws DeploymentException {/* default implementation does nothing*/}

    public void cleanup() {/* default implementation does nothing*/}

    public void commit() throws DeploymentException {/* default implementation does nothing*/}

    public void rollback() {/* default implementation does nothing*/}

    public Object get(String key) {
        synchronized (this) {
            return this.m_coordination.get(key);
        }
    }

    public void store(String key, Object object) {
        synchronized (this) {
            this.m_coordination.store(key, object);
        }
    }

    public File getWorkingDirectory() {
        synchronized (this) {
            return (File) this.m_coordination.get("working.dir");
        }
    }

}
