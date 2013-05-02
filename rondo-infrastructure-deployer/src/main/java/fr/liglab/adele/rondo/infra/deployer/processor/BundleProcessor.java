package fr.liglab.adele.rondo.infra.deployer.processor;

import fr.liglab.adele.rondo.infra.model.Resource;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.everest.core.Everest;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/30/13
 * Time: 9:38 AM
 */
public class BundleProcessor implements ResourceProcessor {

    @Requires(optional = false)
    private Everest m_everest;

    @Override
    public void begin(Resource resource) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void cancel() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void prepare() throws ResourceProcessorException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void commit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void rollback() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
