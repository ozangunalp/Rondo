package fr.liglab.adele.rondo.infra.example;

import fr.liglab.adele.rondo.infra.annotations.Infrastructure;
import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;

import static fr.liglab.adele.rondo.infra.impl.*;
import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/20/13
 * Time: 2:15 PM
 */

@Infrastructure(immediate = true)
public class IcasaPlatform {

    InfrastructureImpl inf ;

    public IcasaPlatform() {
        inf = infrastructure()
                .resource(bundle()
                        .source("mvn:fr.liglab.adele.icasa/common/1.0.0"))
                .resource(bundle()
                        .source("mvn:fr.liglab.adele.icasa/application.api/1.0.0"));
    }

}
