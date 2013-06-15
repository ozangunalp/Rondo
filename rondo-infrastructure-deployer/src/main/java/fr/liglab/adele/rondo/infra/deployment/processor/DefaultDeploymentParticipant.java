package fr.liglab.adele.rondo.infra.deployment.processor;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/3/13
 * Time: 12:50 AM
 */
public abstract class DefaultDeploymentParticipant implements DeploymentParticipant {

    private final ResourceDeclaration m_resource;
    private final DeploymentTransaction m_coordination;

    public DefaultDeploymentParticipant(ResourceDeclaration resource, DeploymentTransaction m_coordination) {
        this.m_coordination = m_coordination;
        this.m_resource = resource;
    }

    public String getParticipantId() {
        return m_coordination.getId()+" - "+m_resource.name();
    }

    public void prepare() throws DeploymentException {/* default implementation does nothing*/}

    public void cleanup() {/* default implementation does nothing*/}

    public void commit() throws DeploymentException {/* default implementation does nothing*/}

    public void rollback() {/* default implementation does nothing*/}

    public DeploymentTransaction getTransaction(){
        return m_coordination;
    }

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
}
