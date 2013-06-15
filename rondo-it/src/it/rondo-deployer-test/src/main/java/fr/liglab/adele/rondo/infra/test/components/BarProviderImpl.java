package fr.liglab.adele.rondo.infra.test.components;

import fr.liglab.adele.rondo.infra.test.services.BarService;
import fr.liglab.adele.rondo.infra.test.services.FooService;
import org.apache.felix.ipojo.annotations.*;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/6/13
 * Time: 11:36 AM
 */
@Component(name = "org.apache.felix.ipojo.everest.ipojo.test.b1.BarProviderImpl", version = "2.0.0")
@Provides
public class BarProviderImpl implements BarService {

    @Requires
    private FooService m_foo;

    @Property
    private String barPrefix;

    @ServiceProperty
    private String barSuffix;

    public String getBar() {
        return barPrefix + m_foo.getFoo() + barSuffix + "-v2.0.0";
    }

}
