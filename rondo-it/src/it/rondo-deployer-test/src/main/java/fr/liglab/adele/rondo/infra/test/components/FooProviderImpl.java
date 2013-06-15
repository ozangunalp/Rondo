package fr.liglab.adele.rondo.infra.test.components;

import fr.liglab.adele.rondo.infra.test.services.FooService;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Property;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.ServiceProperty;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/6/13
 * Time: 11:37 AM
 */
@Component(name = "Foo", version = "1.2.3.foo")
@Provides
public class FooProviderImpl implements FooService {

    @Property
    private String fooPrefix;

    @ServiceProperty
    private int fooCounter;

    public String getFoo() {
        return fooPrefix + Integer.toString(fooCounter);
    }

}
