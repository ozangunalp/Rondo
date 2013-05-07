package fr.liglab.adele.rondo.infra.test;

import fr.liglab.adele.rondo.infra.model.Infrastructure;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Validate;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/7/13
 * Time: 1:34 AM
 */
@Component
@Instantiate
public class TestInfrastructure {

    BundleContext m_context;

    ServiceRegistration<Infrastructure> serviceRegistration;

    public TestInfrastructure(BundleContext m_context) {
        this.m_context = m_context;

    }

    @Validate
    public void start() {

        Infrastructure inf = infrastructure()

                .resource(bundle("config admin")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.configadmin-1.6.0.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.configadmin")
                        .version("1.6.0"))

                .resource(bundle("file install")
                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.fileinstall-3.2.6.jar")
                        .state("ACTIVE")
                        .symbolicName("org.apache.felix.fileinstall")
                        .version("3.2.6"))

//                .resource(bundle("log")
//                    .source("http://apache.opensourcemirror.com//felix/org.apache.felix.log-1.0.1.jar")
//                    .state("ACTIVE")
//                    .symbolicName("org.apache.felix.log")
//                    .version("1.0.1"))
//
//                .resource(bundle("event admin")
//                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.eventadmin-1.3.2.jar")
//                        .state("ACTIVE")
//                        .symbolicName("org.apache.felix.eventadmin")
//                        .version("1.3.2"))
//
//                .resource(bundle("event handler")
//                        .source("http://apache.opensourcemirror.com//felix/org.apache.felix.ipojo.handler.eventadmin-1.8.0.jar")
//                        .state("ACTIVE")
//                        .symbolicName("org.apache.felix.ipojo.handler.eventadmin")
//                        .version("1.8.0"))
//
//                .resource(aPackage("org.apache.felix.ipojo")
//                        .version("1.8.4"))


//                .resource(bundle("")
//                    .source("")
//                    .state("ACTIVE")
//                    .symbolicName("")
//                    .version(""))
//                .resource(aPackage("")
//                    .version("")
//                    .state(""))
//                .resource(service("")
//                    .objectClass(list(""))
//                    .with("property").setto("value"))
//                .resource(instance("")
//                    .factory("factoy")
//                    .factoryVersion(""))
                ;

        serviceRegistration = m_context.registerService(Infrastructure.class, inf, null);
    }


    @Invalidate
    public void stop() {
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }

}
