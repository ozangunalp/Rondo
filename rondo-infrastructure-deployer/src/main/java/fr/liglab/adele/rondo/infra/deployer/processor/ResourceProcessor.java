package fr.liglab.adele.rondo.infra.deployer.processor;

import fr.liglab.adele.rondo.infra.model.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/30/13
 * Time: 9:40 AM
 */
public interface ResourceProcessor {

    public void begin(Resource resource);

    public void cancel();

    public void prepare() throws ResourceProcessorException;

    public void commit();

    public void rollback();

}
