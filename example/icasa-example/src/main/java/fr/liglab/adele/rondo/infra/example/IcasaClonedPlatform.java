package fr.liglab.adele.rondo.infra.example;

import fr.liglab.adele.rondo.infra.annotations.Infrastructure;
import fr.liglab.adele.rondo.infra.impl.InfrastructureImpl;
import fr.liglab.adele.rondo.infra.model.Bundle;
import fr.liglab.adele.rondo.infra.model.Package;

import static fr.liglab.adele.rondo.infra.impl.BundleImpl.bundle;
import static fr.liglab.adele.rondo.infra.impl.PackageImpl.aPackage;
import static fr.liglab.adele.rondo.infra.impl.InfrastructureImpl.infrastructure;


@Infrastructure(immediate = true)
public class IcasaClonedPlatform {

    InfrastructureImpl infra;
    public IcasaClonedPlatform() {
        infra = infrastructure();

        infra.resource(bundle("38-javax.mail")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/mail-1.4.3.jar")
                .symbolicName("javax.mail")
                .version("1.4.3")
                .state("ACTIVE"));


        infra.resource(bundle("19-org.apache.felix.ipojo.handler.extender")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.ipojo.handler.extender-1.4.0.jar")
                .symbolicName("org.apache.felix.ipojo.handler.extender")
                .version("1.4.0")
                .state("ACTIVE"));


        infra.resource(bundle("11-org.apache.felix.gogo.command")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.gogo.command-0.12.0.jar")
                .symbolicName("org.apache.felix.gogo.command")
                .version("0.12.0")
                .state("ACTIVE"));


        infra.resource(bundle("58-context.api")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/context.api-1.0.1-SNAPSHOT.jar")
                .symbolicName("context.api")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("35-context.remote")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/context.remote-1.0.1-SNAPSHOT.jar")
                .symbolicName("context.remote")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("48-com.sun.jersey.jersey-server")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/jersey-server-1.9.jar")
                .symbolicName("com.sun.jersey.jersey-server")
                .version("1.9.0")
                .state("ACTIVE"));


        infra.resource(bundle("34-org.apache.felix.ipojo.everest-osgi")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/everest-osgi-1.0-SNAPSHOT.jar")
                .symbolicName("org.apache.felix.ipojo.everest-osgi")
                .version("1.0.0.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("63-org.apache.felix.ipojo.everest-system")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/everest-system-1.0-SNAPSHOT.jar")
                .symbolicName("org.apache.felix.ipojo.everest-system")
                .version("1.0.0.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("51-json-configurator")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/json-configurator-1.2.2-SNAPSHOT.jar")
                .symbolicName("json-configurator")
                .version("1.2.2.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("36-org.apache.felix.ipojo.everest-servlet")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/everest-servlet-1.0-SNAPSHOT.jar")
                .symbolicName("org.apache.felix.ipojo.everest-servlet")
                .version("1.0.0.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("61-fr.liglab.adele.rondo.system-extension")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/rondo-system-extension-0.0.1-SNAPSHOT.jar")
                .symbolicName("fr.liglab.adele.rondo.system-extension")
                .version("0.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("2-autoload.res.processor")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/autoload.res.processor-1.0.5-SNAPSHOT.jar")
                .symbolicName("autoload.res.processor")
                .version("1.0.5.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("53-fr.liglab.adele.rondo.cloner")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/rondo-cloner-0.0.1-SNAPSHOT.jar")
                .symbolicName("fr.liglab.adele.rondo.cloner")
                .version("0.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("43-jaxb-api")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/jaxb-api-osgi-2.2.1.jar")
                .symbolicName("jaxb-api")
                .version("2.2.1")
                .state("ACTIVE"));


        infra.resource(bundle("16-org.apache.felix.ipojo")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.ipojo-1.10.1.jar")
                .symbolicName("org.apache.felix.ipojo")
                .version("1.10.1")
                .state("ACTIVE"));


        infra.resource(bundle("49-dp-rest-installer")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/dp-rest-installer-1.0.1-SNAPSHOT.jar")
                .symbolicName("dp-rest-installer")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("26-org.ow2.chameleon.shared.preferences")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/shared-preferences-service-0.2.0.jar")
                .symbolicName("org.ow2.chameleon.shared.preferences")
                .version("0.2.0")
                .state("ACTIVE"));


        infra.resource(bundle("5-deployment.package.file.install")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/deployment.package.file.install-1.0.5-SNAPSHOT.jar")
                .symbolicName("deployment.package.file.install")
                .version("1.0.5.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("50-com.sun.jersey.contribs.jersey-multipart")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/jersey-multipart-1.9.jar")
                .symbolicName("com.sun.jersey.contribs.jersey-multipart")
                .version("1.9.0")
                .state("ACTIVE"));


        infra.resource(bundle("7-org.ow2.chameleon.json.json.org-implementation")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/json-service-json.org-0.4.0.jar")
                .symbolicName("org.ow2.chameleon.json.json.org-implementation")
                .version("0.4.0")
                .state("ACTIVE"));


        infra.resource(bundle("39-common")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/common-1.0.1-SNAPSHOT.jar")
                .symbolicName("common")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("13-org.apache.felix.gogo.shell")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.gogo.shell-0.10.0.jar")
                .symbolicName("org.apache.felix.gogo.shell")
                .version("0.10.0")
                .state("ACTIVE"));


        infra.resource(bundle("17-org.apache.felix.ipojo.composite")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.ipojo.composite-1.10.1.jar")
                .symbolicName("org.apache.felix.ipojo.composite")
                .version("1.10.1")
                .state("ACTIVE"));


        infra.resource(bundle("54-device.manager.api")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/device.manager.api-1.0.1-SNAPSHOT.jar")
                .symbolicName("device.manager.api")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("3-com.springsource.org.apache.log4j")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/com.springsource.org.apache.log4j-1.2.16.jar")
                .symbolicName("com.springsource.org.apache.log4j")
                .version("1.2.16")
                .state("ACTIVE"));


        infra.resource(bundle("14-org.apache.felix.http.jetty")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.http.jetty-2.2.0.jar")
                .symbolicName("org.apache.felix.http.jetty")
                .version("2.2.0")
                .state("ACTIVE"));


        infra.resource(bundle("46-com.sun.jersey.jersey-core")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/jersey-core-1.9.jar")
                .symbolicName("com.sun.jersey.jersey-core")
                .version("1.9.0")
                .state("ACTIVE"));


        infra.resource(bundle("47-atmosgi-component")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/atmosgi-component-1.2.2-SNAPSHOT.jar")
                .symbolicName("atmosgi-component")
                .version("1.2.2.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("56-com.sun.jersey.jersey-client")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/jersey-client-1.9.jar")
                .symbolicName("com.sun.jersey.jersey-client")
                .version("1.9.0")
                .state("ACTIVE"));


        infra.resource(bundle("18-org.apache.felix.ipojo.gogo")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.ipojo.gogo-1.10.1.jar")
                .symbolicName("org.apache.felix.ipojo.gogo")
                .version("1.10.1")
                .state("ACTIVE"));


        infra.resource(bundle("1-de.akquinet.gomobile.deployment.rp.autoconf")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/autoconf-resource-processor-1.0.1.jar")
                .symbolicName("de.akquinet.gomobile.deployment.rp.autoconf")
                .version("1.0.1")
                .state("ACTIVE"));


        infra.resource(bundle("32-org.apache.felix.ipojo.everest-core")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/everest-core-1.0-SNAPSHOT.jar")
                .symbolicName("org.apache.felix.ipojo.everest-core")
                .version("1.0.0.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("42-jersey-exporter")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/jersey-exporter-1.2.2-SNAPSHOT.jar")
                .symbolicName("jersey-exporter")
                .version("1.2.2.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("45-org.apache.commons.io")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/commons-io-2.4.jar")
                .symbolicName("org.apache.commons.io")
                .version("2.4.0")
                .state("ACTIVE"));


        infra.resource(bundle("31-application.impl")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/application.impl-1.0.1-SNAPSHOT.jar")
                .symbolicName("application.impl")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("40-atmosgi-service")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/atmosgi-service-1.2.2-SNAPSHOT.jar")
                .symbolicName("atmosgi-service")
                .version("1.2.2.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("52-fr.liglab.adele.rondo.deployer")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/rondo-deployer-0.0.1-SNAPSHOT.jar")
                .symbolicName("fr.liglab.adele.rondo.deployer")
                .version("0.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("62-technical.services.api")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/technical.services.api-1.0.1-SNAPSHOT.jar")
                .symbolicName("technical.services.api")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("41-technical.services.impl")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/technical.services.impl-1.0.1-SNAPSHOT.jar")
                .symbolicName("technical.services.impl")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("22-org.apache.felix.prefs")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.prefs-1.0.4.jar")
                .symbolicName("org.apache.felix.prefs")
                .version("1.0.4")
                .state("ACTIVE"));


        infra.resource(bundle("20-org.apache.felix.ipojo.webconsole")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.ipojo.webconsole-1.7.0.jar")
                .symbolicName("org.apache.felix.ipojo.webconsole")
                .version("1.7.0")
                .state("ACTIVE"));


        infra.resource(bundle("37-gogo.adapter")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/gogo.adapter-1.0.1-SNAPSHOT.jar")
                .symbolicName("gogo.adapter")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("30-org.jvnet.mimepull")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/mimepull-1.4.jar")
                .symbolicName("org.jvnet.mimepull")
                .version("1.4.0")
                .state("ACTIVE"));


        infra.resource(bundle("4-de.akquinet.gomobile.deploymentadmin")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/deployment-admin-impl-1.0.1.jar")
                .symbolicName("de.akquinet.gomobile.deploymentadmin")
                .version("1.0.1")
                .state("ACTIVE"));


        infra.resource(bundle("23-org.apache.felix.webconsole")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.webconsole-3.1.6.jar")
                .symbolicName("org.apache.felix.webconsole")
                .version("3.1.6")
                .state("ACTIVE"));


        infra.resource(bundle("10-org.apache.felix.fileinstall")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.fileinstall-3.1.4.jar")
                .symbolicName("org.apache.felix.fileinstall")
                .version("3.1.4")
                .state("ACTIVE"));


        infra.resource(bundle("57-fr.liglab.adele.rondo.core")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/rondo-core-0.0.1-SNAPSHOT.jar")
                .symbolicName("fr.liglab.adele.rondo.core")
                .version("0.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("29-org.ow2.chameleon.sharedprefs.xml-implementation")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/xml-shared-preferences-0.2.0.jar")
                .symbolicName("org.ow2.chameleon.sharedprefs.xml-implementation")
                .version("0.2.0")
                .state("ACTIVE"));


        infra.resource(bundle("59-clock.system.impl")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/clock.system.impl-1.0.1-20130618.084530-25.jar")
                .symbolicName("clock.system.impl")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("6-com.google.guava")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/guava-14.0.1.jar")
                .symbolicName("com.google.guava")
                .version("14.0.1")
                .state("ACTIVE"));


        infra.resource(bundle("9-org.apache.felix.eventadmin")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.eventadmin-1.3.0.jar")
                .symbolicName("org.apache.felix.eventadmin")
                .version("1.3.0")
                .state("ACTIVE"));


        infra.resource(bundle("25-osgi.cmpn")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.osgi.compendium-4.3.1.jar")
                .symbolicName("osgi.cmpn")
                .version("4.3.1.201210102024")
                .state("ACTIVE"));


        infra.resource(bundle("24-org.apache.felix.webconsole.plugins.event")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.webconsole.plugins.event-1.0.2.jar")
                .symbolicName("org.apache.felix.webconsole.plugins.event")
                .version("1.0.2")
                .state("ACTIVE"));


        infra.resource(bundle("8-org.apache.felix.configadmin")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.configadmin-1.2.8.jar")
                .symbolicName("org.apache.felix.configadmin")
                .version("1.2.8")
                .state("ACTIVE"));


        infra.resource(bundle("33-org.apache.felix.ipojo.everest-ipojo")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/everest-ipojo-1.0-SNAPSHOT.jar")
                .symbolicName("org.apache.felix.ipojo.everest-ipojo")
                .version("1.0.0.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("60-rose-core")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/rose-core-1.2.2-SNAPSHOT.jar")
                .symbolicName("rose-core")
                .version("1.2.2.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("44-context.impl")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/context.impl-1.0.1-SNAPSHOT.jar")
                .symbolicName("context.impl")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("28-slf4j.log4j12")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/slf4j-log4j12-1.6.4.jar")
                .symbolicName("slf4j.log4j12")
                .version("1.6.4")
                .state("RESOLVED"));


        infra.resource(bundle("21-org.apache.felix.log")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.log-1.0.1.jar")
                .symbolicName("org.apache.felix.log")
                .version("1.0.1")
                .state("ACTIVE"));


        infra.resource(bundle("55-application.api")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/load/application.api-1.0.1-SNAPSHOT.jar")
                .symbolicName("application.api")
                .version("1.0.1.SNAPSHOT")
                .state("ACTIVE"));


        infra.resource(bundle("27-slf4j.api-active")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/slf4j-api-1.6.4.jar")
                .symbolicName("slf4j.api")
                .version("1.6.4")
                .state("ACTIVE"));


        infra.resource(bundle("27-slf4j.api-installed")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/slf4j-api-1.6.4.jar")
                .symbolicName("slf4j.api")
                .version("1.6.4")
                .state("INSTALLED"));


        infra.resource(bundle("12-org.apache.felix.gogo.runtime")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.gogo.runtime-0.10.0.jar")
                .symbolicName("org.apache.felix.gogo.runtime")
                .version("0.10.0")
                .state("ACTIVE"));


        infra.resource(bundle("15-org.apache.felix.http.whiteboard")
                .source("file:/Volumes/Macintosh%20HD/Users/ozan/dev/iCasa-Platform/distribution/icasa-platform-distribution/target/icasa.platform.distribution/bundle/org.apache.felix.http.whiteboard-2.0.4.jar")
                .symbolicName("org.apache.felix.http.whiteboard")
                .version("2.0.4")
                .state("ACTIVE"));




        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.border-0.0.0.1_007_JavaSE")
                .name("javax.swing.border")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("43-osgi.wiring.package-javax.xml.bind.helpers-2.2.1")
                .name("javax.xml.bind.helpers")
                .version("2.2.1"));


        infra.resource(aPackage("60-osgi.wiring.package-org.ow2.chameleon.rose.registry-1.2.2.SNAPSHOT")
                .name("org.ow2.chameleon.rose.registry")
                .version("1.2.2.SNAPSHOT"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.spi.dispatch-0.0.0")
                .name("com.sun.jersey.spi.dispatch")
                .version("0.0.0"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device.bathroomscale-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.bathroomscale")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.model.parameter-0.0.0")
                .name("com.sun.jersey.server.impl.model.parameter")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.PortableServer.CurrentPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.PortableServer.CurrentPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.ws.spi-0.0.0.1_007_JavaSE")
                .name("javax.xml.ws.spi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl-0.0.0")
                .name("com.sun.jersey.server.impl")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.security.auth.x500-0.0.0.1_007_JavaSE")
                .name("javax.security.auth.x500")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("62-osgi.wiring.package-fr.liglab.adele.icasa.service.scheduler-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.service.scheduler")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("38-osgi.wiring.package-javax.mail.search-1.4.0")
                .name("javax.mail.search")
                .version("1.4.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.CosNaming-0.0.0.1_007_JavaSE")
                .name("org.omg.CosNaming")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.model.parameter.multivalued-0.0.0")
                .name("com.sun.jersey.server.impl.model.parameter.multivalued")
                .version("0.0.0"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.handlers.dependency-1.10.0")
                .name("org.apache.felix.ipojo.handlers.dependency")
                .version("1.10.0"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io.comparator-1.4.9999")
                .name("org.apache.commons.io.comparator")
                .version("1.4.9999"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.impl-0.0.0")
                .name("com.sun.jersey.impl")
                .version("0.0.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.deploymentadmin.spi-1.0.1")
                .name("org.osgi.service.deploymentadmin.spi")
                .version("1.0.1"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.clock.util-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.clock.util")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("38-osgi.wiring.package-javax.mail.event-1.4.0")
                .name("javax.mail.event")
                .version("1.4.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.metatype-1.2.0")
                .name("org.osgi.service.metatype")
                .version("1.2.0"));


        infra.resource(aPackage("52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.util-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.deployment.util")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.api.provider.jaxb-0.0.0")
                .name("com.sun.jersey.api.provider.jaxb")
                .version("0.0.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.component.annotations-1.2.0")
                .name("org.osgi.service.component.annotations")
                .version("1.2.0"));


        infra.resource(aPackage("27-osgi.wiring.package-org.slf4j.helpers-1.6.4")
                .name("org.slf4j.helpers")
                .version("1.6.4"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.api.view-0.0.0")
                .name("com.sun.jersey.api.view")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.bind.annotation-0.0.0.1_007_JavaSE")
                .name("javax.xml.bind.annotation")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.PortableServer.ServantLocatorPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.PortableServer.ServantLocatorPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.annotation-0.0.0.1_007_JavaSE")
                .name("javax.annotation")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("61-osgi.wiring.package-fr.liglab.adele.rondo.infra.system-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.system")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device.power-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.power")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.reflection-0.0.0")
                .name("com.sun.jersey.core.reflection")
                .version("0.0.0"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io.comparator-2.4.0")
                .name("org.apache.commons.io.comparator")
                .version("2.4.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.print.attribute-0.0.0.1_007_JavaSE")
                .name("javax.print.attribute")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("14-osgi.wiring.package-javax.servlet.http-2.5.0")
                .name("javax.servlet.http")
                .version("2.5.0"));


        infra.resource(aPackage("34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.service-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.osgi.service")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing-0.0.0.1_007_JavaSE")
                .name("javax.swing")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.framework.hooks.service-1.1.0")
                .name("org.osgi.framework.hooks.service")
                .version("1.1.0"));


        infra.resource(aPackage("43-osgi.wiring.package-javax.xml.bind.attachment-2.2.1")
                .name("javax.xml.bind.attachment")
                .version("2.2.1"));


        infra.resource(aPackage("49-osgi.wiring.package-fr.imag.adele.appstore.gateway.api-1.0.1.SNAPSHOT")
                .name("fr.imag.adele.appstore.gateway.api")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.framework.launch>1.0.0")
                .name("org.osgi.framework.launch")
                .filter("(version>=1.0.0)"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.ws.soap-0.0.0.1_007_JavaSE")
                .name("javax.xml.ws.soap")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.template-0.0.0")
                .name("com.sun.jersey.server.impl.template")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.spi.resource-0.0.0")
                .name("com.sun.jersey.spi.resource")
                .version("0.0.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.nt-1.2.16")
                .name("org.apache.log4j.nt")
                .version("1.2.16"));


        infra.resource(aPackage("7-osgi.wiring.package-org.ow2.chameleon.json-1.1.0")
                .name("org.ow2.chameleon.json")
                .version("1.1.0"));


        infra.resource(aPackage("14-osgi.wiring.package-javax.servlet.resources-2.5.0")
                .name("javax.servlet.resources")
                .version("2.5.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.CORBA-0.0.0.1_007_JavaSE")
                .name("org.omg.CORBA")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.xml-1.2.16")
                .name("org.apache.log4j.xml")
                .version("1.2.16"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.IOP.CodecFactoryPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.IOP.CodecFactoryPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.rmi.ssl-0.0.0.1_007_JavaSE")
                .name("javax.rmi.ssl")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.cache-1.1.0.RC1")
                .name("org.atmosphere.cache")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.dmt.notification.spi-2.0.0")
                .name("org.osgi.service.dmt.notification.spi")
                .version("2.0.0"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io.output-2.4.0")
                .name("org.apache.commons.io.output")
                .version("2.4.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.lf5.config-1.2.16")
                .name("org.apache.log4j.lf5.config")
                .version("1.2.16"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.plaf.synth-0.0.0.1_007_JavaSE")
                .name("javax.swing.plaf.synth")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("7-osgi.wiring.package-org.json-0.0.0")
                .name("org.json")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.spi.scanning-0.0.0")
                .name("com.sun.jersey.spi.scanning")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.transform.dom-0.0.0.1_007_JavaSE")
                .name("javax.xml.transform.dom")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.core.json-2.2.0")
                .name("com.fasterxml.jackson.core.json")
                .version("2.2.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.ws.spi.http-0.0.0.1_007_JavaSE")
                .name("javax.xml.ws.spi.http")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.monitor-1.0.0")
                .name("org.osgi.service.monitor")
                .version("1.0.0"));


        infra.resource(aPackage("27-osgi.wiring.package-org.slf4j.spi-1.6.4")
                .name("org.slf4j.spi")
                .version("1.6.4"));


        infra.resource(aPackage("38-osgi.wiring.package-javax.mail.internet-1.4.0")
                .name("javax.mail.internet")
                .version("1.4.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.blueprint.reflect-1.0.1")
                .name("org.osgi.service.blueprint.reflect")
                .version("1.0.1"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.stream-0.0.0.1_007_JavaSE")
                .name("javax.xml.stream")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.CORBA.TypeCodePackage-0.0.0.1_007_JavaSE")
                .name("org.omg.CORBA.TypeCodePackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("56-osgi.wiring.package-com.sun.jersey.api.client.filter-0.0.0")
                .name("com.sun.jersey.api.client.filter")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.security.auth.spi-0.0.0.1_007_JavaSE")
                .name("javax.security.auth.spi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.component-1.2.0")
                .name("org.osgi.service.component")
                .version("1.2.0"));


        infra.resource(aPackage("40-osgi.wiring.package-org.barjo.atmosgi-0.0.0")
                .name("org.barjo.atmosgi")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.CosNaming.NamingContextExtPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.CosNaming.NamingContextExtPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.processor.impl-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.deployment.processor.impl")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.api.uri-0.0.0")
                .name("com.sun.jersey.api.uri")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.uri-0.0.0")
                .name("com.sun.jersey.server.impl.uri")
                .version("0.0.0"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.spi.component-0.0.0")
                .name("com.sun.jersey.core.spi.component")
                .version("0.0.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.exc-2.2.0")
                .name("com.fasterxml.jackson.databind.exc")
                .version("2.2.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.imageio.spi-0.0.0.1_007_JavaSE")
                .name("javax.imageio.spi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.extender.builder-1.10.0")
                .name("org.apache.felix.ipojo.extender.builder")
                .version("1.10.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.spi.monitoring-0.0.0")
                .name("com.sun.jersey.spi.monitoring")
                .version("0.0.0"));


        infra.resource(aPackage("28-osgi.wiring.package-org.slf4j.impl-1.6.4")
                .name("org.slf4j.impl")
                .version("1.6.4"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.container.version-1.1.0.RC1")
                .name("org.atmosphere.container.version")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("60-osgi.wiring.package-org.ow2.chameleon.rose-1.2.2.SNAPSHOT")
                .name("org.ow2.chameleon.rose")
                .version("1.2.2.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.transform.stax-0.0.0.1_007_JavaSE")
                .name("javax.xml.transform.stax")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.ser-2.2.0")
                .name("com.fasterxml.jackson.databind.ser")
                .version("2.2.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.pattern-1.2.16")
                .name("org.apache.log4j.pattern")
                .version("1.2.16"));


        infra.resource(aPackage("43-osgi.wiring.package-javax.xml.bind.annotation-2.2.1")
                .name("javax.xml.bind.annotation")
                .version("2.2.1"));


        infra.resource(aPackage("20-osgi.wiring.package-org.apache.felix.ipojo.webconsole-1.7.0")
                .name("org.apache.felix.ipojo.webconsole")
                .version("1.7.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.ws-0.0.0.1_007_JavaSE")
                .name("javax.xml.ws")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.CORBA.portable-0.0.0.1_007_JavaSE")
                .name("org.omg.CORBA.portable")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.core-2.2.0")
                .name("com.fasterxml.jackson.core")
                .version("2.2.0"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device.settopbox-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.settopbox")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.colorchooser-0.0.0.1_007_JavaSE")
                .name("javax.swing.colorchooser")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.crypto-0.0.0.1_007_JavaSE")
                .name("javax.xml.crypto")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.util.concurrent-14.0.1")
                .name("com.google.common.util.concurrent")
                .version("14.0.1"));


        infra.resource(aPackage("4-osgi.wiring.package-org.osgi.service.deploymentadmin.spi-1.1.0")
                .name("org.osgi.service.deploymentadmin.spi")
                .version("1.1.0"));


        infra.resource(aPackage("43-osgi.wiring.package-javax.xml.bind.util-2.2.1")
                .name("javax.xml.bind.util")
                .version("2.2.1"));


        infra.resource(aPackage("54-osgi.wiring.package-fr.liglab.adele.icasa.device.manager-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.manager")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io.input-1.4.9999")
                .name("org.apache.commons.io.input")
                .version("1.4.9999"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.transaction.xa-0.0.0.1_007_JavaSE")
                .name("javax.transaction.xa")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.sql.rowset.spi-0.0.0.1_007_JavaSE")
                .name("javax.sql.rowset.spi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0")
                .name("org.osgi.service.deploymentadmin")
                .version("1.1.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.jsonschema-2.2.0")
                .name("com.fasterxml.jackson.databind.jsonschema")
                .version("2.2.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.modelapi.annotation-0.0.0")
                .name("com.sun.jersey.server.impl.modelapi.annotation")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.api.container.filter-0.0.0")
                .name("com.sun.jersey.api.container.filter")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.stub.java.rmi-0.0.0.1_007_JavaSE")
                .name("org.omg.stub.java.rmi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.ws.wsaddressing-0.0.0.1_007_JavaSE")
                .name("javax.xml.ws.wsaddressing")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("32-osgi.wiring.package-org.apache.felix.ipojo.everest.impl-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.impl")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.service.startlevel-1.1.0")
                .name("org.osgi.service.startlevel")
                .version("1.1.0"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.provider.jaxb-0.0.0")
                .name("com.sun.jersey.core.provider.jaxb")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.security.auth-0.0.0.1_007_JavaSE")
                .name("javax.security.auth")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.spi-0.0.0")
                .name("com.sun.jersey.spi")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.container-0.0.0")
                .name("com.sun.jersey.server.impl.container")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.management.relation-0.0.0.1_007_JavaSE")
                .name("javax.management.relation")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.Dynamic-0.0.0.1_007_JavaSE")
                .name("org.omg.Dynamic")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.deployment")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("50-osgi.wiring.package-com.sun.jersey.multipart.file-1.9.0")
                .name("com.sun.jersey.multipart.file")
                .version("1.9.0"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.api.representation-0.0.0")
                .name("com.sun.jersey.api.representation")
                .version("0.0.0"));


        infra.resource(aPackage("32-osgi.wiring.package-org.apache.felix.ipojo.everest.core-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.core")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.provider-0.0.0")
                .name("com.sun.jersey.core.provider")
                .version("0.0.0"));


        infra.resource(aPackage("32-osgi.wiring.package-org.apache.felix.ipojo.everest.filters-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.filters")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.text.rtf-0.0.0.1_007_JavaSE")
                .name("javax.swing.text.rtf")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.filechooser-0.0.0.1_007_JavaSE")
                .name("javax.swing.filechooser")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.cfg-2.2.0")
                .name("com.fasterxml.jackson.databind.cfg")
                .version("2.2.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.rmi-0.0.0.1_007_JavaSE")
                .name("javax.rmi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.annotation.processing-0.0.0.1_007_JavaSE")
                .name("javax.annotation.processing")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.w3c.dom.ls-0.0.0.1_007_JavaSE")
                .name("org.w3c.dom.ls")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.net-0.0.0.1_007_JavaSE")
                .name("javax.net")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.provider-0.0.0")
                .name("com.sun.jersey.server.impl.provider")
                .version("0.0.0"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.spi.inject-0.0.0")
                .name("com.sun.jersey.spi.inject")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml-0.0.0.1_007_JavaSE")
                .name("javax.xml")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.deploy-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.osgi.deploy")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.soap-0.0.0.1_007_JavaSE")
                .name("javax.xml.soap")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.crypto-0.0.0.1_007_JavaSE")
                .name("javax.crypto")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.log-1.3.0")
                .name("org.osgi.service.log")
                .version("1.3.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.io-1.0.0")
                .name("org.osgi.service.io")
                .version("1.0.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.deser-2.2.0")
                .name("com.fasterxml.jackson.databind.deser")
                .version("2.2.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.activation-0.0.0.1_007_JavaSE")
                .name("javax.activation")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("32-osgi.wiring.package-org.apache.felix.ipojo.everest.services-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.services")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("53-osgi.wiring.package-fr.liglab.adele.rondo.infra.model-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.model")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("14-osgi.wiring.package-javax.servlet-2.5.0")
                .name("javax.servlet")
                .version("2.5.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.uri.rules-0.0.0")
                .name("com.sun.jersey.server.impl.uri.rules")
                .version("0.0.0"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.net-14.0.1")
                .name("com.google.common.net")
                .version("14.0.1"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.container.servlet-0.0.0")
                .name("com.sun.jersey.server.impl.container.servlet")
                .version("0.0.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.varia-1.2.16")
                .name("org.apache.log4j.varia")
                .version("1.2.16"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.dmt.security-2.0.0")
                .name("org.osgi.service.dmt.security")
                .version("2.0.0"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.spi.factory-0.0.0")
                .name("com.sun.jersey.core.spi.factory")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.datatype-0.0.0.1_007_JavaSE")
                .name("javax.xml.datatype")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.bundle-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.osgi.bundle")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("50-osgi.wiring.package-com.sun.jersey.multipart.impl-1.9.0")
                .name("com.sun.jersey.multipart.impl")
                .version("1.9.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.print-0.0.0.1_007_JavaSE")
                .name("javax.print")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.Messaging-0.0.0.1_007_JavaSE")
                .name("org.omg.Messaging")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.parser-1.10.0")
                .name("org.apache.felix.ipojo.parser")
                .version("1.10.0"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.extender.queue-1.10.0")
                .name("org.apache.felix.ipojo.extender.queue")
                .version("1.10.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.util.measurement-1.0.1")
                .name("org.osgi.util.measurement")
                .version("1.0.1"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.ser.std-2.2.0")
                .name("com.fasterxml.jackson.databind.ser.std")
                .version("2.2.0"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.util-1.1.0.RC1")
                .name("org.atmosphere.util")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.api.model-0.0.0")
                .name("com.sun.jersey.api.model")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.security.sasl-0.0.0.1_007_JavaSE")
                .name("javax.security.sasl")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("18-osgi.wiring.package-org.apache.felix.ipojo.arch.gogo-1.10.1")
                .name("org.apache.felix.ipojo.arch.gogo")
                .version("1.10.1"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.sql-0.0.0.1_007_JavaSE")
                .name("javax.sql")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("63-osgi.wiring.package-org.apache.felix.ipojo.everest.system-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.system")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.transform-0.0.0.1_007_JavaSE")
                .name("javax.xml.transform")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.crypto.interfaces-0.0.0.1_007_JavaSE")
                .name("javax.crypto.interfaces")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.CosNaming.NamingContextPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.CosNaming.NamingContextPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.device-1.1.0")
                .name("org.osgi.service.device")
                .version("1.1.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.PortableServer-0.0.0.1_007_JavaSE")
                .name("org.omg.PortableServer")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.or.sax-1.2.16")
                .name("org.apache.log4j.or.sax")
                .version("1.2.16"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.spi.component.ioc-0.0.0")
                .name("com.sun.jersey.core.spi.component.ioc")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.monitoring-0.0.0")
                .name("com.sun.jersey.server.impl.monitoring")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.text.html-0.0.0.1_007_JavaSE")
                .name("javax.swing.text.html")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("57-osgi.wiring.package-fr.liglab.adele.rondo.infra.annotations-0.0.0")
                .name("fr.liglab.adele.rondo.infra.annotations")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.xml.sax.helpers-0.0.0.1_007_JavaSE")
                .name("org.xml.sax.helpers")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.naming.spi-0.0.0.1_007_JavaSE")
                .name("javax.naming.spi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("27-osgi.wiring.package-org.slf4j-1.6.4")
                .name("org.slf4j")
                .version("1.6.4"));


        infra.resource(aPackage("60-osgi.wiring.package-org.ow2.chameleon.rose.introspect-1.2.2.SNAPSHOT")
                .name("org.ow2.chameleon.rose.introspect")
                .version("1.2.2.SNAPSHOT"));


        infra.resource(aPackage("57-osgi.wiring.package-fr.liglab.adele.rondo.infra.impl-0.0.0")
                .name("fr.liglab.adele.rondo.infra.impl")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.management.remote.rmi-0.0.0.1_007_JavaSE")
                .name("javax.management.remote.rmi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.IOP.CodecPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.IOP.CodecPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.impl.provider.header-0.0.0")
                .name("com.sun.jersey.core.impl.provider.header")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.tools-0.0.0.1_007_JavaSE")
                .name("javax.tools")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.jndi-1.0.0")
                .name("org.osgi.service.jndi")
                .version("1.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.spi.uri.rules-0.0.0")
                .name("com.sun.jersey.spi.uri.rules")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.net.ssl-0.0.0.1_007_JavaSE")
                .name("javax.net.ssl")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("38-osgi.wiring.package-javax.mail-1.4.0")
                .name("javax.mail")
                .version("1.4.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.lf5.viewer-1.2.16")
                .name("org.apache.log4j.lf5.viewer")
                .version("1.2.16"));


        infra.resource(aPackage("0-osgi.wiring.package-org.ietf.jgss-0.0.0.1_007_JavaSE")
                .name("org.ietf.jgss")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.math-14.0.1")
                .name("com.google.common.math")
                .version("14.0.1"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.cdi-0.0.0")
                .name("com.sun.jersey.server.impl.cdi")
                .version("0.0.0"));


        infra.resource(aPackage("12-osgi.wiring.package-org.apache.felix.service.command-0.10.0")
                .name("org.apache.felix.service.command")
                .version("0.10.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.imageio.event-0.0.0.1_007_JavaSE")
                .name("javax.imageio.event")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.util.xml-1.0.1")
                .name("org.osgi.util.xml")
                .version("1.0.1"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.application-1.0.0")
                .name("org.osgi.application")
                .version("1.0.0"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.spi.service-0.0.0")
                .name("com.sun.jersey.spi.service")
                .version("0.0.0"));


        infra.resource(aPackage("34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.packages-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.osgi.packages")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.plaf.nimbus-0.0.0.1_007_JavaSE")
                .name("javax.swing.plaf.nimbus")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.localization-0.0.0")
                .name("com.sun.jersey.localization")
                .version("0.0.0"));


        infra.resource(aPackage("56-osgi.wiring.package-com.sun.ws.rs.ext-0.0.0")
                .name("com.sun.ws.rs.ext")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.api.wadl.config-0.0.0")
                .name("com.sun.jersey.api.wadl.config")
                .version("0.0.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.jsontype-2.2.0")
                .name("com.fasterxml.jackson.databind.jsontype")
                .version("2.2.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.table-0.0.0.1_007_JavaSE")
                .name("javax.swing.table")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("31-osgi.wiring.package-fr.liglab.adele.icasa.application.impl-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.application.impl")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.management.timer-0.0.0.1_007_JavaSE")
                .name("javax.management.timer")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.xml.sax.ext-0.0.0.1_007_JavaSE")
                .name("org.xml.sax.ext")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.bind.util-0.0.0.1_007_JavaSE")
                .name("javax.xml.bind.util")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.imageio.plugins.bmp-0.0.0.1_007_JavaSE")
                .name("javax.imageio.plugins.bmp")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.SendingContext-0.0.0.1_007_JavaSE")
                .name("org.omg.SendingContext")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.text.html.parser-0.0.0.1_007_JavaSE")
                .name("javax.swing.text.html.parser")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("29-osgi.wiring.package-org.ow2.chameleon.sharedprefs.xml-0.0.0")
                .name("org.ow2.chameleon.sharedprefs.xml")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.probes-0.0.0")
                .name("com.sun.jersey.server.probes")
                .version("0.0.0"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.handlers.providedservice-1.10.0")
                .name("org.apache.felix.ipojo.handlers.providedservice")
                .version("1.10.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.config-1.2.16")
                .name("org.apache.log4j.config")
                .version("1.2.16"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.imageio.metadata-0.0.0.1_007_JavaSE")
                .name("javax.imageio.metadata")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.event-0.0.0.1_007_JavaSE")
                .name("javax.swing.event")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.rmi.CORBA-0.0.0.1_007_JavaSE")
                .name("javax.rmi.CORBA")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.sound.sampled.spi-0.0.0.1_007_JavaSE")
                .name("javax.sound.sampled.spi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.management.modelmbean-0.0.0.1_007_JavaSE")
                .name("javax.management.modelmbean")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.provisioning-1.2.0")
                .name("org.osgi.service.provisioning")
                .version("1.2.0"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io-2.4.0")
                .name("org.apache.commons.io")
                .version("2.4.0"));


        infra.resource(aPackage("34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.log-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.osgi.log")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.config-1.1.0.RC1")
                .name("org.atmosphere.config")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.transform.sax-0.0.0.1_007_JavaSE")
                .name("javax.xml.transform.sax")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.ext-2.2.0")
                .name("com.fasterxml.jackson.databind.ext")
                .version("2.2.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.spi.component-0.0.0")
                .name("com.sun.jersey.server.spi.component")
                .version("0.0.0"));


        infra.resource(aPackage("38-osgi.wiring.package-com.sun.mail.pop3-1.4.3")
                .name("com.sun.mail.pop3")
                .version("1.4.3"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.script-0.0.0.1_007_JavaSE")
                .name("javax.script")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.w3c.dom.bootstrap-0.0.0.1_007_JavaSE")
                .name("org.w3c.dom.bootstrap")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.io-14.0.1")
                .name("com.google.common.io")
                .version("14.0.1"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.cm-1.4.0")
                .name("org.osgi.service.cm")
                .version("1.4.0"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.client-1.1.0.RC1")
                .name("org.atmosphere.client")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.PortableServer.POAManagerPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.PortableServer.POAManagerPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.stream.util-0.0.0.1_007_JavaSE")
                .name("javax.xml.stream.util")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.wadl-0.0.0")
                .name("com.sun.jersey.server.impl.wadl")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.inject-0.0.0")
                .name("com.sun.jersey.server.impl.inject")
                .version("0.0.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.http-1.2.1")
                .name("org.osgi.service.http")
                .version("1.2.1"));


        infra.resource(aPackage("44-osgi.wiring.package-fr.liglab.adele.icasa.location.impl-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.location.impl")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("63-osgi.wiring.package-org.apache.felix.ipojo.everest.system.mx-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.system.mx")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.bind-0.0.0.1_007_JavaSE")
                .name("javax.xml.bind")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.handlers.providedservice.strategy-1.10.0")
                .name("org.apache.felix.ipojo.handlers.providedservice.strategy")
                .version("1.10.0"));


        infra.resource(aPackage("38-osgi.wiring.package-com.sun.mail.util.logging-1.4.3")
                .name("com.sun.mail.util.logging")
                .version("1.4.3"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.framework.hooks.bundle>1.0.0")
                .name("org.osgi.framework.hooks.bundle")
                .filter("(version>=1.0.0)"));


        infra.resource(aPackage("33-osgi.wiring.package-org.apache.felix.ipojo.everest.ipojo-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.ipojo")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.model.method-0.0.0")
                .name("com.sun.jersey.server.impl.model.method")
                .version("0.0.0"));


        infra.resource(aPackage("46-osgi.wiring.package-javax.ws.rs.ext-0.0.0")
                .name("javax.ws.rs.ext")
                .version("0.0.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.lf5.viewer.images-1.2.16")
                .name("org.apache.log4j.lf5.viewer.images")
                .version("1.2.16"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.util.tracker-1.5.0")
                .name("org.osgi.util.tracker")
                .version("1.5.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.util-2.2.0")
                .name("com.fasterxml.jackson.databind.util")
                .version("2.2.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.jsontype.impl-2.2.0")
                .name("com.fasterxml.jackson.databind.jsontype.impl")
                .version("2.2.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.wadl.generators.resourcedoc-0.0.0")
                .name("com.sun.jersey.server.wadl.generators.resourcedoc")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.uri.rules.automata-0.0.0")
                .name("com.sun.jersey.server.impl.uri.rules.automata")
                .version("0.0.0"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.impl.provider.xml-0.0.0")
                .name("com.sun.jersey.core.impl.provider.xml")
                .version("0.0.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.upnp-1.2.0")
                .name("org.osgi.service.upnp")
                .version("1.2.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.annotation-2.2.0")
                .name("com.fasterxml.jackson.annotation")
                .version("2.2.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.remoteserviceadmin-1.0.1")
                .name("org.osgi.service.remoteserviceadmin")
                .version("1.0.1"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.blueprint.container-1.0.2")
                .name("org.osgi.service.blueprint.container")
                .version("1.0.2"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.wadl.generators.resourcedoc.model-0.0.0")
                .name("com.sun.jersey.server.wadl.generators.resourcedoc.model")
                .version("0.0.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.event-1.3.0")
                .name("org.osgi.service.event")
                .version("1.3.0"));


        infra.resource(aPackage("62-osgi.wiring.package-fr.liglab.adele.icasa.service.zone.dimension.calculator-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.service.zone.dimension.calculator")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.osgi")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.di-1.1.0.RC1")
                .name("org.atmosphere.di")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.PortableServer.portable-0.0.0.1_007_JavaSE")
                .name("org.omg.PortableServer.portable")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.security.auth.callback-0.0.0.1_007_JavaSE")
                .name("javax.security.auth.callback")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("35-osgi.wiring.package-fr.liglab.adele.icasa.remote.util-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.remote.util")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("8-osgi.wiring.package-org.apache.felix.cm.file-1.0.0")
                .name("org.apache.felix.cm.file")
                .version("1.0.0"));


        infra.resource(aPackage("52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.transaction.impl-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.deployment.transaction.impl")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.coordinator-1.0.0")
                .name("org.osgi.service.coordinator")
                .version("1.0.0"));


        infra.resource(aPackage("38-osgi.wiring.package-javax.mail.util-1.4.0")
                .name("javax.mail.util")
                .version("1.4.0"));


        infra.resource(aPackage("52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.transaction-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.deployment.transaction")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.crypto.dom-0.0.0.1_007_JavaSE")
                .name("javax.xml.crypto.dom")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.dmt.spi-2.0.0")
                .name("org.osgi.service.dmt.spi")
                .version("2.0.0"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.model.method.dispatch-0.0.0")
                .name("com.sun.jersey.server.impl.model.method.dispatch")
                .version("0.0.0"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.collect-14.0.1")
                .name("com.google.common.collect")
                .version("14.0.1"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.type-2.2.0")
                .name("com.fasterxml.jackson.databind.type")
                .version("2.2.0"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device.presence-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.presence")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.websocket.protocol-1.1.0.RC1")
                .name("org.atmosphere.websocket.protocol")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("32-osgi.wiring.package-org.apache.felix.ipojo.everest.managers.everest-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.managers.everest")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.text-0.0.0.1_007_JavaSE")
                .name("javax.swing.text")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.lf5-1.2.16")
                .name("org.apache.log4j.lf5")
                .version("1.2.16"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.CORBA.DynAnyPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.CORBA.DynAnyPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.helpers-1.2.16")
                .name("org.apache.log4j.helpers")
                .version("1.2.16"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.module-2.2.0")
                .name("com.fasterxml.jackson.databind.module")
                .version("2.2.0"));


        infra.resource(aPackage("57-osgi.wiring.package-fr.liglab.adele.rondo.infra.model-0.0.0")
                .name("fr.liglab.adele.rondo.infra.model")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.component-0.0.0")
                .name("com.sun.jersey.server.impl.component")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.tree-0.0.0.1_007_JavaSE")
                .name("javax.swing.tree")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.DynamicAny.DynAnyPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.DynamicAny.DynAnyPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("56-osgi.wiring.package-com.sun.jersey.client.proxy-0.0.0")
                .name("com.sun.jersey.client.proxy")
                .version("0.0.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.lf5.viewer.configure-1.2.16")
                .name("org.apache.log4j.lf5.viewer.configure")
                .version("1.2.16"));


        infra.resource(aPackage("52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.impl-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.deployment.impl")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0")
                .name("org.apache.felix.ipojo.architecture")
                .version("1.10.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.PortableInterceptor-0.0.0.1_007_JavaSE")
                .name("org.omg.PortableInterceptor")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.jsonFormatVisitors-2.2.0")
                .name("com.fasterxml.jackson.databind.jsonFormatVisitors")
                .version("2.2.0"));


        infra.resource(aPackage("44-osgi.wiring.package-fr.liglab.adele.icasa.location.util-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.location.util")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("12-osgi.wiring.package-org.apache.felix.service.threadio-0.10.0")
                .name("org.apache.felix.service.threadio")
                .version("0.10.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.transform.stream-0.0.0.1_007_JavaSE")
                .name("javax.xml.transform.stream")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("46-osgi.wiring.package-javax.ws.rs.core-0.0.0")
                .name("javax.ws.rs.core")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.management.openmbean-0.0.0.1_007_JavaSE")
                .name("javax.management.openmbean")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.extender-1.10.0")
                .name("org.apache.felix.ipojo.extender")
                .version("1.10.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.spi.template-0.0.0")
                .name("com.sun.jersey.spi.template")
                .version("0.0.0"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.cache-14.0.1")
                .name("com.google.common.cache")
                .version("14.0.1"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.core.util-2.2.0")
                .name("com.fasterxml.jackson.core.util")
                .version("2.2.0"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io-1.4.9999")
                .name("org.apache.commons.io")
                .version("1.4.9999"));


        infra.resource(aPackage("43-osgi.wiring.package-javax.xml.bind-2.2.1")
                .name("javax.xml.bind")
                .version("2.2.1"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.sound.midi-0.0.0.1_007_JavaSE")
                .name("javax.sound.midi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.api.container-0.0.0")
                .name("com.sun.jersey.api.container")
                .version("0.0.0"));


        infra.resource(aPackage("2-osgi.wiring.package-fr.liglab.adele.common.dp.autoload.res.proc-1.0.5.SNAPSHOT")
                .name("fr.liglab.adele.common.dp.autoload.res.proc")
                .version("1.0.5.SNAPSHOT"));


        infra.resource(aPackage("37-osgi.wiring.package-fr.liglab.adele.osgi.shell.installer-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.osgi.shell.installer")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.crypto.dsig.keyinfo-0.0.0.1_007_JavaSE")
                .name("javax.xml.crypto.dsig.keyinfo")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("44-osgi.wiring.package-fr.liglab.adele.icasa.commands.impl-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.commands.impl")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device.gasSensor-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.gasSensor")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.spi.scanning-0.0.0")
                .name("com.sun.jersey.core.spi.scanning")
                .version("0.0.0"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device.light-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.light")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.sound.midi.spi-0.0.0.1_007_JavaSE")
                .name("javax.sound.midi.spi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.api.wadl-0.0.0")
                .name("com.sun.jersey.api.wadl")
                .version("0.0.0"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.util-1.10.0")
                .name("org.apache.felix.ipojo.util")
                .version("1.10.0"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.config.service-1.1.0.RC1")
                .name("org.atmosphere.config.service")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.location-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.location")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.managedbeans-0.0.0")
                .name("com.sun.jersey.server.impl.managedbeans")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.sound.sampled-0.0.0.1_007_JavaSE")
                .name("javax.sound.sampled")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("53-osgi.wiring.package-fr.liglab.adele.rondo.infra.annotations-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.annotations")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-org.xml.sax-0.0.0.1_007_JavaSE")
                .name("org.xml.sax")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.annotation-2.2.0")
                .name("com.fasterxml.jackson.databind.annotation")
                .version("2.2.0"));


        infra.resource(aPackage("55-osgi.wiring.package-fr.liglab.adele.icasa.application-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.application")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.security.auth.kerberos-0.0.0.1_007_JavaSE")
                .name("javax.security.auth.kerberos")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.lang.model-0.0.0.1_007_JavaSE")
                .name("javax.lang.model")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("14-osgi.wiring.package-javax.servlet.jsp.resources-2.5.0")
                .name("javax.servlet.jsp.resources")
                .version("2.5.0"));


        infra.resource(aPackage("8-osgi.wiring.package-org.apache.felix.cm-1.0.0")
                .name("org.apache.felix.cm")
                .version("1.0.0"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.listener-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.listener")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.crypto.spec-0.0.0.1_007_JavaSE")
                .name("javax.crypto.spec")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.core.base-2.2.0")
                .name("com.fasterxml.jackson.core.base")
                .version("2.2.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.CORBA_2_3.portable-0.0.0.1_007_JavaSE")
                .name("org.omg.CORBA_2_3.portable")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.container-1.1.0.RC1")
                .name("org.atmosphere.container")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.spi.container-0.0.0")
                .name("com.sun.jersey.spi.container")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.bind.helpers-0.0.0.1_007_JavaSE")
                .name("javax.xml.bind.helpers")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.plaf.metal-0.0.0.1_007_JavaSE")
                .name("javax.swing.plaf.metal")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io.monitor-2.4.0")
                .name("org.apache.commons.io.monitor")
                .version("2.4.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.application-1.1.0")
                .name("org.osgi.service.application")
                .version("1.1.0"));


        infra.resource(aPackage("32-osgi.wiring.package-org.apache.felix.ipojo.everest.internals-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.internals")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.management.loading-0.0.0.1_007_JavaSE")
                .name("javax.management.loading")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("35-osgi.wiring.package-fr.liglab.adele.icasa.remote-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.remote")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.security.auth.login-0.0.0.1_007_JavaSE")
                .name("javax.security.auth.login")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.w3c.dom.events-0.0.0.1_007_JavaSE")
                .name("org.w3c.dom.events")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.crypto.dsig-0.0.0.1_007_JavaSE")
                .name("javax.xml.crypto.dsig")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.util.position-1.0.1")
                .name("org.osgi.util.position")
                .version("1.0.1"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.crypto.dsig.spec-0.0.0.1_007_JavaSE")
                .name("javax.xml.crypto.dsig.spec")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.core.io-2.2.0")
                .name("com.fasterxml.jackson.core.io")
                .version("2.2.0"));


        infra.resource(aPackage("23-osgi.wiring.package-org.apache.felix.webconsole-3.1.2")
                .name("org.apache.felix.webconsole")
                .version("3.1.2"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.jmx-1.2.16")
                .name("org.apache.log4j.jmx")
                .version("1.2.16"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.sql.rowset.serial-0.0.0.1_007_JavaSE")
                .name("javax.sql.rowset.serial")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.ws.handler-0.0.0.1_007_JavaSE")
                .name("javax.xml.ws.handler")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.util.uri-1.1.0.RC1")
                .name("org.atmosphere.util.uri")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("38-osgi.wiring.package-com.sun.mail.util-1.4.3")
                .name("com.sun.mail.util")
                .version("1.4.3"));


        infra.resource(aPackage("49-osgi.wiring.package-fr.imag.adele.appstore.gateway.util-1.0.1.SNAPSHOT")
                .name("fr.imag.adele.appstore.gateway.util")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.imageio.plugins.jpeg-0.0.0.1_007_JavaSE")
                .name("javax.imageio.plugins.jpeg")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.wadl.generators-0.0.0")
                .name("com.sun.jersey.server.wadl.generators")
                .version("0.0.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.lf5.viewer.categoryexplorer-1.2.16")
                .name("org.apache.log4j.lf5.viewer.categoryexplorer")
                .version("1.2.16"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.websocket-1.1.0.RC1")
                .name("org.atmosphere.websocket")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.container.filter-0.0.0")
                .name("com.sun.jersey.server.impl.container.filter")
                .version("0.0.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.jdbc-1.0.0")
                .name("org.osgi.service.jdbc")
                .version("1.0.0"));


        infra.resource(aPackage("62-osgi.wiring.package-fr.liglab.adele.icasa.service.preferences-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.service.preferences")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("56-osgi.wiring.package-com.sun.jersey.client.osgi-0.0.0")
                .name("com.sun.jersey.client.osgi")
                .version("0.0.0"));


        infra.resource(aPackage("38-osgi.wiring.package-com.sun.mail.imap-1.4.3")
                .name("com.sun.mail.imap")
                .version("1.4.3"));


        infra.resource(aPackage("53-osgi.wiring.package-fr.liglab.adele.rondo.cloner-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.cloner")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.header.reader-0.0.0")
                .name("com.sun.jersey.core.header.reader")
                .version("0.0.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.deser.impl-2.2.0")
                .name("com.fasterxml.jackson.databind.deser.impl")
                .version("2.2.0"));


        infra.resource(aPackage("9-osgi.wiring.package-org.osgi.service.event-1.3.0")
                .name("org.osgi.service.event")
                .version("1.3.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.framework.hooks.resolver-1.0.0")
                .name("org.osgi.framework.hooks.resolver")
                .version("1.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.framework.startlevel-1.0.0")
                .name("org.osgi.framework.startlevel")
                .version("1.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.CORBA_2_3-0.0.0.1_007_JavaSE")
                .name("org.omg.CORBA_2_3")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("19-osgi.wiring.package-org.apache.felix.ipojo.handler.extender-0.0.0")
                .name("org.apache.felix.ipojo.handler.extender")
                .version("0.0.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.core.type-2.2.0")
                .name("com.fasterxml.jackson.core.type")
                .version("2.2.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.framework>1.6.0")
                .name("org.osgi.framework")
                .filter("(version>=1.6.0)"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.wadl.generators.resourcedoc.xhtml-0.0.0")
                .name("com.sun.jersey.server.wadl.generators.resourcedoc.xhtml")
                .version("0.0.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.jpa-1.0.0")
                .name("org.osgi.service.jpa")
                .version("1.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.security.cert-0.0.0.1_007_JavaSE")
                .name("javax.security.cert")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("62-osgi.wiring.package-fr.liglab.adele.icasa.service.zone.size.calculator-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.service.zone.size.calculator")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.wadl-0.0.0")
                .name("com.sun.jersey.server.wadl")
                .version("0.0.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.prefs-1.1.1")
                .name("org.osgi.service.prefs")
                .version("1.1.1"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.base-14.0.1")
                .name("com.google.common.base")
                .version("14.0.1"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.lang.model.type-0.0.0.1_007_JavaSE")
                .name("javax.lang.model.type")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.dependency.interceptors-1.10.0")
                .name("org.apache.felix.ipojo.dependency.interceptors")
                .version("1.10.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.plaf-0.0.0.1_007_JavaSE")
                .name("javax.swing.plaf")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.DynamicAny-0.0.0.1_007_JavaSE")
                .name("org.omg.DynamicAny")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.plaf.multi-0.0.0.1_007_JavaSE")
                .name("javax.swing.plaf.multi")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io.filefilter-2.4.0")
                .name("org.apache.commons.io.filefilter")
                .version("2.4.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.activity-0.0.0.1_007_JavaSE")
                .name("javax.activity")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.jws-0.0.0.1_007_JavaSE")
                .name("javax.jws")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.handlers.configuration-1.10.0")
                .name("org.apache.felix.ipojo.handlers.configuration")
                .version("1.10.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.ws.http-0.0.0.1_007_JavaSE")
                .name("javax.xml.ws.http")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.parsers-0.0.0.1_007_JavaSE")
                .name("javax.xml.parsers")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("56-osgi.wiring.package-com.sun.jersey.api.client.config-0.0.0")
                .name("com.sun.jersey.api.client.config")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.IOP-0.0.0.1_007_JavaSE")
                .name("org.omg.IOP")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.container.httpserver-0.0.0")
                .name("com.sun.jersey.server.impl.container.httpserver")
                .version("0.0.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.util.tracker-1.5.0")
                .name("org.osgi.util.tracker")
                .version("1.5.0"));


        infra.resource(aPackage("30-osgi.wiring.package-org.jvnet.mimepull-1.4.0")
                .name("org.jvnet.mimepull")
                .version("1.4.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.validation-0.0.0.1_007_JavaSE")
                .name("javax.xml.validation")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("26-osgi.wiring.package-org.ow2.chameleon.sharedprefs-1.0.0")
                .name("org.ow2.chameleon.sharedprefs")
                .version("1.0.0"));


        infra.resource(aPackage("39-osgi.wiring.package-fr.liglab.adele.icasa.common-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.common")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("11-osgi.wiring.package-org.osgi.service.log-1.3.0")
                .name("org.osgi.service.log")
                .version("1.3.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.naming-0.0.0.1_007_JavaSE")
                .name("javax.naming")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.transaction-0.0.0.1_007_JavaSE")
                .name("javax.transaction")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("12-osgi.wiring.package-org.apache.felix.gogo.api-0.10.0")
                .name("org.apache.felix.gogo.api")
                .version("0.10.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.framework.hooks.weaving-1.0.0")
                .name("org.osgi.framework.hooks.weaving")
                .version("1.0.0"));


        infra.resource(aPackage("34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.config-1.0.0.SNAPSHOT")
                .name("org.apache.felix.ipojo.everest.osgi.config")
                .version("1.0.0.SNAPSHOT"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.net-1.2.16")
                .name("org.apache.log4j.net")
                .version("1.2.16"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.configuration-1.10.0")
                .name("org.apache.felix.ipojo.configuration")
                .version("1.10.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.research.ws.wadl-0.0.0")
                .name("com.sun.research.ws.wadl")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.lang.model.element-0.0.0.1_007_JavaSE")
                .name("javax.lang.model.element")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0")
                .name("org.apache.felix.ipojo")
                .version("1.10.0"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.cpr-1.1.0.RC1")
                .name("org.atmosphere.cpr")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.ws.handler.soap-0.0.0.1_007_JavaSE")
                .name("javax.xml.ws.handler.soap")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.sql.rowset-0.0.0.1_007_JavaSE")
                .name("javax.sql.rowset")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.service.url-1.0.0")
                .name("org.osgi.service.url")
                .version("1.0.0"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io.output-1.4.9999")
                .name("org.apache.commons.io.output")
                .version("1.4.9999"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.core.sym-2.2.0")
                .name("com.fasterxml.jackson.core.sym")
                .version("2.2.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.chainsaw-1.2.16")
                .name("org.apache.log4j.chainsaw")
                .version("1.2.16"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.reflect-14.0.1")
                .name("com.google.common.reflect")
                .version("14.0.1"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.naming.ldap-0.0.0.1_007_JavaSE")
                .name("javax.naming.ldap")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.api.core-0.0.0")
                .name("com.sun.jersey.api.core")
                .version("0.0.0"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.osgi-0.0.0")
                .name("com.sun.jersey.core.osgi")
                .version("0.0.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.resource-0.0.0")
                .name("com.sun.jersey.server.impl.resource")
                .version("0.0.0"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.primitives-14.0.1")
                .name("com.google.common.primitives")
                .version("14.0.1"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.imageio-0.0.0.1_007_JavaSE")
                .name("javax.imageio")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.lf5.util-1.2.16")
                .name("org.apache.log4j.lf5.util")
                .version("1.2.16"));


        infra.resource(aPackage("60-osgi.wiring.package-org.ow2.chameleon.rose.util-1.2.2.SNAPSHOT")
                .name("org.ow2.chameleon.rose.util")
                .version("1.2.2.SNAPSHOT"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.eventbus-14.0.1")
                .name("com.google.common.eventbus")
                .version("14.0.1"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.jws.soap-0.0.0.1_007_JavaSE")
                .name("javax.jws.soap")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("4-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0")
                .name("org.osgi.service.deploymentadmin")
                .version("1.1.0"));


        infra.resource(aPackage("56-osgi.wiring.package-com.sun.jersey.api.client-0.0.0")
                .name("com.sun.jersey.api.client")
                .version("0.0.0"));


        infra.resource(aPackage("50-osgi.wiring.package-com.sun.jersey.multipart-1.9.0")
                .name("com.sun.jersey.multipart")
                .version("1.9.0"));


        infra.resource(aPackage("22-osgi.wiring.package-org.apache.felix.prefs-0.0.0")
                .name("org.apache.felix.prefs")
                .version("0.0.0"));


        infra.resource(aPackage("56-osgi.wiring.package-com.sun.jersey.client.impl-0.0.0")
                .name("com.sun.jersey.client.impl")
                .version("0.0.0"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.spi.scanning.uri-0.0.0")
                .name("com.sun.jersey.core.spi.scanning.uri")
                .version("0.0.0"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io.filefilter-1.4.9999")
                .name("org.apache.commons.io.filefilter")
                .version("1.4.9999"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.PortableInterceptor.ORBInitInfoPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.PortableInterceptor.ORBInitInfoPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.util-0.0.0")
                .name("com.sun.jersey.core.util")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.management.monitor-0.0.0.1_007_JavaSE")
                .name("javax.management.monitor")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.bind.attachment-0.0.0.1_007_JavaSE")
                .name("javax.xml.bind.attachment")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.ejb-0.0.0")
                .name("com.sun.jersey.server.impl.ejb")
                .version("0.0.0"));


        infra.resource(aPackage("38-osgi.wiring.package-com.sun.mail.smtp-1.4.3")
                .name("com.sun.mail.smtp")
                .version("1.4.3"));


        infra.resource(aPackage("53-osgi.wiring.package-fr.liglab.adele.rondo.infra.impl-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.impl")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.osgi-0.0.0")
                .name("com.sun.jersey.server.osgi")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.bind.annotation.adapters-0.0.0.1_007_JavaSE")
                .name("javax.xml.bind.annotation.adapters")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.stream.events-0.0.0.1_007_JavaSE")
                .name("javax.xml.stream.events")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.dmt.notification-2.0.0")
                .name("org.osgi.service.dmt.notification")
                .version("2.0.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.wireadmin-1.0.1")
                .name("org.osgi.service.wireadmin")
                .version("1.0.1"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.or-1.2.16")
                .name("org.apache.log4j.or")
                .version("1.2.16"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind-2.2.0")
                .name("com.fasterxml.jackson.databind")
                .version("2.2.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.or.jms-1.2.16")
                .name("org.apache.log4j.or.jms")
                .version("1.2.16"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.api.container.httpserver-0.0.0")
                .name("com.sun.jersey.api.container.httpserver")
                .version("0.0.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.ser.impl-2.2.0")
                .name("com.fasterxml.jackson.databind.ser.impl")
                .version("2.2.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.management.remote-0.0.0.1_007_JavaSE")
                .name("javax.management.remote")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.application-0.0.0")
                .name("com.sun.jersey.server.impl.application")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.plaf.basic-0.0.0.1_007_JavaSE")
                .name("javax.swing.plaf.basic")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("43-osgi.wiring.package-javax.xml.bind.annotation.adapters-2.2.1")
                .name("javax.xml.bind.annotation.adapters")
                .version("2.2.1"));


        infra.resource(aPackage("56-osgi.wiring.package-com.sun.jersey.client.impl.async-0.0.0")
                .name("com.sun.jersey.client.impl.async")
                .version("0.0.0"));


        infra.resource(aPackage("14-osgi.wiring.package-org.apache.felix.http.api-2.0.4")
                .name("org.apache.felix.http.api")
                .version("2.0.4"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.namespace-0.0.0.1_007_JavaSE")
                .name("javax.xml.namespace")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.CORBA.ORBPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.CORBA.ORBPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("10-osgi.wiring.package-org.apache.felix.fileinstall-3.1.4")
                .name("org.apache.felix.fileinstall")
                .version("3.1.4"));


        infra.resource(aPackage("56-osgi.wiring.package-com.sun.jersey.api.client.async-0.0.0")
                .name("com.sun.jersey.api.client.async")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.w3c.dom-0.0.0.1_007_JavaSE")
                .name("org.w3c.dom")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.lang.model.util-0.0.0.1_007_JavaSE")
                .name("javax.lang.model.util")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.print.event-0.0.0.1_007_JavaSE")
                .name("javax.print.event")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.hash-14.0.1")
                .name("com.google.common.hash")
                .version("14.0.1"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.interceptor-1.1.0.RC1")
                .name("org.atmosphere.interceptor")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.jdbc-1.2.16")
                .name("org.apache.log4j.jdbc")
                .version("1.2.16"));


        infra.resource(aPackage("8-osgi.wiring.package-org.osgi.service.cm-1.3.0")
                .name("org.osgi.service.cm")
                .version("1.3.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.deser.std-2.2.0")
                .name("com.fasterxml.jackson.databind.deser.std")
                .version("2.2.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j.spi-1.2.16")
                .name("org.apache.log4j.spi")
                .version("1.2.16"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.crypto.dsig.dom-0.0.0.1_007_JavaSE")
                .name("javax.xml.crypto.dsig.dom")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.accessibility-0.0.0.1_007_JavaSE")
                .name("javax.accessibility")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("46-osgi.wiring.package-javax.ws.rs-0.0.0")
                .name("javax.ws.rs")
                .version("0.0.0"));


        infra.resource(aPackage("56-osgi.wiring.package-com.sun.jersey.client.urlconnection-0.0.0")
                .name("com.sun.jersey.client.urlconnection")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.swing.undo-0.0.0.1_007_JavaSE")
                .name("javax.swing.undo")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("47-osgi.wiring.package-org.atmosphere.handler-1.1.0.RC1")
                .name("org.atmosphere.handler")
                .version("1.1.0.RC1"));


        infra.resource(aPackage("45-osgi.wiring.package-org.apache.commons.io.input-2.4.0")
                .name("org.apache.commons.io.input")
                .version("2.4.0"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.node-2.2.0")
                .name("com.fasterxml.jackson.databind.node")
                .version("2.2.0"));


        infra.resource(aPackage("17-osgi.wiring.package-org.apache.felix.ipojo.composite-1.8.4")
                .name("org.apache.felix.ipojo.composite")
                .version("1.8.4"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.core.format-2.2.0")
                .name("com.fasterxml.jackson.core.format")
                .version("2.2.0"));


        infra.resource(aPackage("3-osgi.wiring.package-org.apache.log4j-1.2.16")
                .name("org.apache.log4j")
                .version("1.2.16"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.imageio.stream-0.0.0.1_007_JavaSE")
                .name("javax.imageio.stream")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.impl.provider.entity-0.0.0")
                .name("com.sun.jersey.core.impl.provider.entity")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.PortableServer.POAPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.PortableServer.POAPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.modelapi.validation-0.0.0")
                .name("com.sun.jersey.server.impl.modelapi.validation")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.service.packageadmin-1.2.0")
                .name("org.osgi.service.packageadmin")
                .version("1.2.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.naming.event-0.0.0.1_007_JavaSE")
                .name("javax.naming.event")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("46-osgi.wiring.package-com.sun.jersey.core.header-0.0.0")
                .name("com.sun.jersey.core.header")
                .version("0.0.0"));


        infra.resource(aPackage("60-osgi.wiring.package-org.ow2.chameleon.rose.api-1.2.2.SNAPSHOT")
                .name("org.ow2.chameleon.rose.api")
                .version("1.2.2.SNAPSHOT"));


        infra.resource(aPackage("38-osgi.wiring.package-com.sun.mail.handlers-1.4.3")
                .name("com.sun.mail.handlers")
                .version("1.4.3"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.clock-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.clock")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.processor-0.0.1.SNAPSHOT")
                .name("fr.liglab.adele.rondo.infra.deployment.processor")
                .version("0.0.1.SNAPSHOT"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.context-1.10.0")
                .name("org.apache.felix.ipojo.context")
                .version("1.10.0"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.server.impl.model-0.0.0")
                .name("com.sun.jersey.server.impl.model")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.management-0.0.0.1_007_JavaSE")
                .name("javax.management")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("39-osgi.wiring.package-fr.liglab.adele.icasa.common.impl-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.common.impl")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-org.omg.DynamicAny.DynAnyFactoryPackage-0.0.0.1_007_JavaSE")
                .name("org.omg.DynamicAny.DynAnyFactoryPackage")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device.sound-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.sound")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("16-osgi.wiring.package-org.apache.felix.ipojo.metadata-1.10.0")
                .name("org.apache.felix.ipojo.metadata")
                .version("1.10.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.xml.xpath-0.0.0.1_007_JavaSE")
                .name("javax.xml.xpath")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device.util-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.util")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.spi.container.servlet-0.0.0")
                .name("com.sun.jersey.spi.container.servlet")
                .version("0.0.0"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device.motion-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.motion")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("58-osgi.wiring.package-fr.liglab.adele.icasa.device.temperature-1.0.1.SNAPSHOT")
                .name("fr.liglab.adele.icasa.device.temperature")
                .version("1.0.1.SNAPSHOT"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.naming.directory-0.0.0.1_007_JavaSE")
                .name("javax.naming.directory")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("6-osgi.wiring.package-com.google.common.annotations-14.0.1")
                .name("com.google.common.annotations")
                .version("14.0.1"));


        infra.resource(aPackage("48-osgi.wiring.package-com.sun.jersey.api-0.0.0")
                .name("com.sun.jersey.api")
                .version("0.0.0"));


        infra.resource(aPackage("0-osgi.wiring.package-javax.print.attribute.standard-0.0.0.1_007_JavaSE")
                .name("javax.print.attribute.standard")
                .version("0.0.0.1_007_JavaSE"));


        infra.resource(aPackage("0-osgi.wiring.package-org.osgi.framework.wiring-1.0.0")
                .name("org.osgi.framework.wiring")
                .filter("(version>=1.0.0)"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.dmt-2.0.1")
                .name("org.osgi.service.dmt")
                .version("2.0.1"));


        infra.resource(aPackage("36-osgi.wiring.package-com.fasterxml.jackson.databind.introspect-2.2.0")
                .name("com.fasterxml.jackson.databind.introspect")
                .version("2.2.0"));


        infra.resource(aPackage("25-osgi.wiring.package-org.osgi.service.useradmin-1.1.0")
                .name("org.osgi.service.useradmin")
                .version("1.1.0"));

        infra.resource(Bundle.class,"1-de.akquinet.gomobile.deployment.rp.autoconf")
                .dependsOn(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin.spi-1.1.0");

        infra.resource(Bundle.class,"1-de.akquinet.gomobile.deployment.rp.autoconf")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"1-de.akquinet.gomobile.deployment.rp.autoconf")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"1-de.akquinet.gomobile.deployment.rp.autoconf")
                .dependsOn(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0");

        infra.resource(Bundle.class,"1-de.akquinet.gomobile.deployment.rp.autoconf")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"1-de.akquinet.gomobile.deployment.rp.autoconf")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"1-de.akquinet.gomobile.deployment.rp.autoconf")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"1-de.akquinet.gomobile.deployment.rp.autoconf")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Package.class,"2-osgi.wiring.package-fr.liglab.adele.common.dp.autoload.res.proc-1.0.5.SNAPSHOT")
                .dependsOn(Bundle.class,"2-autoload.res.processor");

        infra.resource(Bundle.class,"2-autoload.res.processor")
                .dependsOn(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin.spi-1.1.0");

        infra.resource(Bundle.class,"2-autoload.res.processor")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"2-autoload.res.processor")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"2-autoload.res.processor")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"2-autoload.res.processor")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"2-autoload.res.processor")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"2-autoload.res.processor")
                .dependsOn(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0");

        infra.resource(Bundle.class,"2-autoload.res.processor")
                .dependsOn(Package.class,"10-osgi.wiring.package-org.apache.felix.fileinstall-3.1.4");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.jmx-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5.viewer-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.varia-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.or.jms-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.net-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5.viewer.configure-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.jdbc-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.spi-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.or.sax-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.config-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5.config-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5.util-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.pattern-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.helpers-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5.viewer.categoryexplorer-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.nt-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.chainsaw-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5.viewer.images-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.xml-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Package.class,"3-osgi.wiring.package-org.apache.log4j.or-1.2.16")
                .dependsOn(Bundle.class,"3-com.springsource.org.apache.log4j");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.swing.tree-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.swing.table-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.management-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.parsers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.swing.event-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.w3c.dom-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.swing.border-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.swing-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.swing.text-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax.helpers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"3-com.springsource.org.apache.log4j")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.naming-0.0.0.1_007_JavaSE");

        infra.resource(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin.spi-1.1.0")
                .dependsOn(Bundle.class,"4-de.akquinet.gomobile.deploymentadmin");

        infra.resource(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0")
                .dependsOn(Bundle.class,"4-de.akquinet.gomobile.deploymentadmin");

        infra.resource(Bundle.class,"4-de.akquinet.gomobile.deploymentadmin")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"4-de.akquinet.gomobile.deploymentadmin")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"4-de.akquinet.gomobile.deploymentadmin")
                .dependsOn(Package.class,"9-osgi.wiring.package-org.osgi.service.event-1.3.0");

        infra.resource(Bundle.class,"4-de.akquinet.gomobile.deploymentadmin")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.service.packageadmin-1.2.0");

        infra.resource(Bundle.class,"4-de.akquinet.gomobile.deploymentadmin")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"4-de.akquinet.gomobile.deploymentadmin")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"4-de.akquinet.gomobile.deploymentadmin")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"5-deployment.package.file.install")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"5-deployment.package.file.install")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"5-deployment.package.file.install")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"5-deployment.package.file.install")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"5-deployment.package.file.install")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"5-deployment.package.file.install")
                .dependsOn(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0");

        infra.resource(Bundle.class,"5-deployment.package.file.install")
                .dependsOn(Package.class,"10-osgi.wiring.package-org.apache.felix.fileinstall-3.1.4");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.collect-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.hash-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.math-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.primitives-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.base-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.annotations-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.eventbus-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.cache-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.reflect-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.util.concurrent-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.net-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Package.class,"6-osgi.wiring.package-com.google.common.io-14.0.1")
                .dependsOn(Bundle.class,"6-com.google.guava");

        infra.resource(Bundle.class,"6-com.google.guava")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.annotation-0.0.0.1_007_JavaSE");

        infra.resource(Package.class,"7-osgi.wiring.package-org.json-0.0.0")
                .dependsOn(Bundle.class,"7-org.ow2.chameleon.json.json.org-implementation");

        infra.resource(Package.class,"7-osgi.wiring.package-org.ow2.chameleon.json-1.1.0")
                .dependsOn(Bundle.class,"7-org.ow2.chameleon.json.json.org-implementation");

        infra.resource(Bundle.class,"7-org.ow2.chameleon.json.json.org-implementation")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"7-org.ow2.chameleon.json.json.org-implementation")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"7-org.ow2.chameleon.json.json.org-implementation")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"7-org.ow2.chameleon.json.json.org-implementation")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Package.class,"8-osgi.wiring.package-org.apache.felix.cm-1.0.0")
                .dependsOn(Bundle.class,"8-org.apache.felix.configadmin");

        infra.resource(Package.class,"8-osgi.wiring.package-org.osgi.service.cm-1.3.0")
                .dependsOn(Bundle.class,"8-org.apache.felix.configadmin");

        infra.resource(Package.class,"8-osgi.wiring.package-org.apache.felix.cm.file-1.0.0")
                .dependsOn(Bundle.class,"8-org.apache.felix.configadmin");

        infra.resource(Bundle.class,"8-org.apache.felix.configadmin")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"8-org.apache.felix.configadmin")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Package.class,"9-osgi.wiring.package-org.osgi.service.event-1.3.0")
                .dependsOn(Bundle.class,"9-org.apache.felix.eventadmin");

        infra.resource(Bundle.class,"9-org.apache.felix.eventadmin")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"9-org.apache.felix.eventadmin")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Package.class,"10-osgi.wiring.package-org.apache.felix.fileinstall-3.1.4")
                .dependsOn(Bundle.class,"10-org.apache.felix.fileinstall");

        infra.resource(Bundle.class,"10-org.apache.felix.fileinstall")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.service.startlevel-1.1.0");

        infra.resource(Bundle.class,"10-org.apache.felix.fileinstall")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"10-org.apache.felix.fileinstall")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"10-org.apache.felix.fileinstall")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"10-org.apache.felix.fileinstall")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"10-org.apache.felix.fileinstall")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.service.url-1.0.0");

        infra.resource(Bundle.class,"10-org.apache.felix.fileinstall")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.service.packageadmin-1.2.0");

        infra.resource(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0")
                .dependsOn(Bundle.class,"11-org.apache.felix.gogo.command");

        infra.resource(Bundle.class,"11-org.apache.felix.gogo.command")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"11-org.apache.felix.gogo.command")
                .dependsOn(Package.class,"12-osgi.wiring.package-org.apache.felix.service.command-0.10.0");

        infra.resource(Bundle.class,"11-org.apache.felix.gogo.command")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.service.packageadmin-1.2.0");

        infra.resource(Bundle.class,"11-org.apache.felix.gogo.command")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.service.startlevel-1.1.0");

        infra.resource(Bundle.class,"11-org.apache.felix.gogo.command")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"11-org.apache.felix.gogo.command")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework.wiring-1.0.0");

        infra.resource(Package.class,"12-osgi.wiring.package-org.apache.felix.service.command-0.10.0")
                .dependsOn(Bundle.class,"12-org.apache.felix.gogo.runtime");

        infra.resource(Package.class,"12-osgi.wiring.package-org.apache.felix.gogo.api-0.10.0")
                .dependsOn(Bundle.class,"12-org.apache.felix.gogo.runtime");

        infra.resource(Package.class,"12-osgi.wiring.package-org.apache.felix.service.threadio-0.10.0")
                .dependsOn(Bundle.class,"12-org.apache.felix.gogo.runtime");

        infra.resource(Bundle.class,"12-org.apache.felix.gogo.runtime")
                .dependsOn(Package.class,"9-osgi.wiring.package-org.osgi.service.event-1.3.0");

        infra.resource(Bundle.class,"12-org.apache.felix.gogo.runtime")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"12-org.apache.felix.gogo.runtime")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"13-org.apache.felix.gogo.shell")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"13-org.apache.felix.gogo.shell")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.service.startlevel-1.1.0");

        infra.resource(Bundle.class,"13-org.apache.felix.gogo.shell")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"13-org.apache.felix.gogo.shell")
                .dependsOn(Package.class,"12-osgi.wiring.package-org.apache.felix.service.command-0.10.0");

        infra.resource(Package.class,"14-osgi.wiring.package-javax.servlet.jsp.resources-2.5.0")
                .dependsOn(Bundle.class,"14-org.apache.felix.http.jetty");

        infra.resource(Package.class,"14-osgi.wiring.package-org.apache.felix.http.api-2.0.4")
                .dependsOn(Bundle.class,"14-org.apache.felix.http.jetty");

        infra.resource(Package.class,"14-osgi.wiring.package-javax.servlet.http-2.5.0")
                .dependsOn(Bundle.class,"14-org.apache.felix.http.jetty");

        infra.resource(Package.class,"14-osgi.wiring.package-javax.servlet.resources-2.5.0")
                .dependsOn(Bundle.class,"14-org.apache.felix.http.jetty");

        infra.resource(Package.class,"14-osgi.wiring.package-javax.servlet-2.5.0")
                .dependsOn(Bundle.class,"14-org.apache.felix.http.jetty");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.net.ssl-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.parsers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax.helpers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"27-osgi.wiring.package-org.slf4j-1.6.4");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.http-1.2.1");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"14-org.apache.felix.http.jetty")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.security.cert-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"15-org.apache.felix.http.whiteboard")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.http-1.2.1");

        infra.resource(Bundle.class,"15-org.apache.felix.http.whiteboard")
                .dependsOn(Package.class,"14-osgi.wiring.package-org.apache.felix.http.api-2.0.4");

        infra.resource(Bundle.class,"15-org.apache.felix.http.whiteboard")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"15-org.apache.felix.http.whiteboard")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"15-org.apache.felix.http.whiteboard")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"15-org.apache.felix.http.whiteboard")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet.http-2.5.0");

        infra.resource(Bundle.class,"15-org.apache.felix.http.whiteboard")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet-2.5.0");

        infra.resource(Package.class,"17-osgi.wiring.package-org.apache.felix.ipojo.composite-1.8.4")
                .dependsOn(Bundle.class,"17-org.apache.felix.ipojo.composite");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.util-1.10.0");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.metadata-1.10.0");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.context-1.10.0");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.parser-1.10.0");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.w3c.dom-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.dependency.interceptors-1.10.0");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax.helpers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.parsers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"17-org.apache.felix.ipojo.composite")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax-0.0.0.1_007_JavaSE");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.handlers.configuration-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.configuration-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.handlers.dependency-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.handlers.providedservice-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.dependency.interceptors-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.parser-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.handlers.providedservice.strategy-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.extender-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.context-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.extender.queue-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.util-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.metadata-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.extender.builder-1.10.0")
                .dependsOn(Bundle.class,"16-org.apache.felix.ipojo");

        infra.resource(Bundle.class,"16-org.apache.felix.ipojo")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework.wiring-1.0.0");

        infra.resource(Bundle.class,"16-org.apache.felix.ipojo")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"16-org.apache.felix.ipojo")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"16-org.apache.felix.ipojo")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"16-org.apache.felix.ipojo")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Package.class,"19-osgi.wiring.package-org.apache.felix.ipojo.handler.extender-0.0.0")
                .dependsOn(Bundle.class,"19-org.apache.felix.ipojo.handler.extender");

        infra.resource(Bundle.class,"19-org.apache.felix.ipojo.handler.extender")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.metadata-1.10.0");

        infra.resource(Bundle.class,"19-org.apache.felix.ipojo.handler.extender")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"19-org.apache.felix.ipojo.handler.extender")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"19-org.apache.felix.ipojo.handler.extender")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"19-org.apache.felix.ipojo.handler.extender")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"19-org.apache.felix.ipojo.handler.extender")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"19-org.apache.felix.ipojo.handler.extender")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.util-1.10.0");

        infra.resource(Package.class,"18-osgi.wiring.package-org.apache.felix.ipojo.arch.gogo-1.10.1")
                .dependsOn(Bundle.class,"18-org.apache.felix.ipojo.gogo");

        infra.resource(Bundle.class,"18-org.apache.felix.ipojo.gogo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"18-org.apache.felix.ipojo.gogo")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"18-org.apache.felix.ipojo.gogo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"18-org.apache.felix.ipojo.gogo")
                .dependsOn(Package.class,"12-osgi.wiring.package-org.apache.felix.service.command-0.10.0");

        infra.resource(Bundle.class,"18-org.apache.felix.ipojo.gogo")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"18-org.apache.felix.ipojo.gogo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.extender-1.10.0");

        infra.resource(Bundle.class,"18-org.apache.felix.ipojo.gogo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.metadata-1.10.0");

        infra.resource(Bundle.class,"21-org.apache.felix.log")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"21-org.apache.felix.log")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Package.class,"20-osgi.wiring.package-org.apache.felix.ipojo.webconsole-1.7.0")
                .dependsOn(Bundle.class,"20-org.apache.felix.ipojo.webconsole");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"23-osgi.wiring.package-org.apache.felix.webconsole-3.1.2");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.handlers.providedservice-1.10.0");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.metadata-1.10.0");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"7-osgi.wiring.package-org.json-0.0.0");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.handlers.dependency-1.10.0");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet-2.5.0");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet.http-2.5.0");

        infra.resource(Bundle.class,"20-org.apache.felix.ipojo.webconsole")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Package.class,"23-osgi.wiring.package-org.apache.felix.webconsole-3.1.2")
                .dependsOn(Bundle.class,"23-org.apache.felix.webconsole");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.wireadmin-1.0.1");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet-2.5.0");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.metatype-1.2.0");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet.http-2.5.0");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.http-1.2.1");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.prefs-1.1.1");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.service.startlevel-1.1.0");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0");

        infra.resource(Bundle.class,"23-org.apache.felix.webconsole")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.service.packageadmin-1.2.0");

        infra.resource(Package.class,"22-osgi.wiring.package-org.apache.felix.prefs-0.0.0")
                .dependsOn(Bundle.class,"22-org.apache.felix.prefs");

        infra.resource(Bundle.class,"22-org.apache.felix.prefs")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"22-org.apache.felix.prefs")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"22-org.apache.felix.prefs")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.prefs-1.1.1");

        infra.resource(Bundle.class,"22-org.apache.felix.prefs")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.device-1.1.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.blueprint.reflect-1.0.1")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.component-1.2.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.application-1.1.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.util.tracker-1.5.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.provisioning-1.2.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.upnp-1.2.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.log-1.3.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.io-1.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.util.position-1.0.1")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.remoteserviceadmin-1.0.1")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.blueprint.container-1.0.2")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.jndi-1.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.event-1.3.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.jpa-1.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.dmt.security-2.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.prefs-1.1.1")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.dmt.notification.spi-2.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.deploymentadmin.spi-1.0.1")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.http-1.2.1")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.util.xml-1.0.1")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.application-1.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.metatype-1.2.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.component.annotations-1.2.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.util.measurement-1.0.1")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.dmt.notification-2.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.dmt-2.0.1")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.monitor-1.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.coordinator-1.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.jdbc-1.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.wireadmin-1.0.1")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.useradmin-1.1.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Package.class,"25-osgi.wiring.package-org.osgi.service.dmt.spi-2.0.0")
                .dependsOn(Bundle.class,"25-osgi.cmpn");

        infra.resource(Bundle.class,"25-osgi.cmpn")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.parsers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"25-osgi.cmpn")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"25-osgi.cmpn")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.naming-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"25-osgi.cmpn")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.sql-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"25-osgi.cmpn")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.naming.directory-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"24-org.apache.felix.webconsole.plugins.event")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"24-org.apache.felix.webconsole.plugins.event")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet.http-2.5.0");

        infra.resource(Bundle.class,"24-org.apache.felix.webconsole.plugins.event")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"24-org.apache.felix.webconsole.plugins.event")
                .dependsOn(Package.class,"9-osgi.wiring.package-org.osgi.service.event-1.3.0");

        infra.resource(Bundle.class,"24-org.apache.felix.webconsole.plugins.event")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet-2.5.0");

        infra.resource(Package.class,"27-osgi.wiring.package-org.slf4j-1.6.4")
                .dependsOn(Bundle.class,"27-slf4j.api-active");

        infra.resource(Package.class,"27-osgi.wiring.package-org.slf4j.spi-1.6.4")
                .dependsOn(Bundle.class,"27-slf4j.api-active");

        infra.resource(Package.class,"28-osgi.wiring.package-org.slf4j.impl-1.6.4")
                .dependsOn(Bundle.class,"28-slf4j.log4j12");

        infra.resource(Package.class,"27-osgi.wiring.package-org.slf4j.helpers-1.6.4")
                .dependsOn(Bundle.class,"27-slf4j.api-active");

        infra.resource(Package.class,"26-osgi.wiring.package-org.ow2.chameleon.sharedprefs-1.0.0")
                .dependsOn(Bundle.class,"26-org.ow2.chameleon.shared.preferences");

        infra.resource(Package.class,"29-osgi.wiring.package-org.ow2.chameleon.sharedprefs.xml-0.0.0")
                .dependsOn(Bundle.class,"29-org.ow2.chameleon.sharedprefs.xml-implementation");

        infra.resource(Bundle.class,"29-org.ow2.chameleon.sharedprefs.xml-implementation")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"29-org.ow2.chameleon.sharedprefs.xml-implementation")
                .dependsOn(Package.class,"27-osgi.wiring.package-org.slf4j-1.6.4");

        infra.resource(Bundle.class,"29-org.ow2.chameleon.sharedprefs.xml-implementation")
                .dependsOn(Package.class,"26-osgi.wiring.package-org.ow2.chameleon.sharedprefs-1.0.0");

        infra.resource(Bundle.class,"29-org.ow2.chameleon.sharedprefs.xml-implementation")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"29-org.ow2.chameleon.sharedprefs.xml-implementation")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"29-org.ow2.chameleon.sharedprefs.xml-implementation")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Package.class,"31-osgi.wiring.package-fr.liglab.adele.icasa.application.impl-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"31-application.impl");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"9-osgi.wiring.package-org.osgi.service.event-1.3.0");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"39-osgi.wiring.package-fr.liglab.adele.icasa.common-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"39-osgi.wiring.package-fr.liglab.adele.icasa.common.impl-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"55-osgi.wiring.package-fr.liglab.adele.icasa.application-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"31-application.impl")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Package.class,"30-osgi.wiring.package-org.jvnet.mimepull-1.4.0")
                .dependsOn(Bundle.class,"30-org.jvnet.mimepull");

        infra.resource(Package.class,"34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.config-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi");

        infra.resource(Package.class,"34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.log-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi");

        infra.resource(Package.class,"34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.bundle-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi");

        infra.resource(Package.class,"34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi");

        infra.resource(Package.class,"34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.service-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi");

        infra.resource(Package.class,"34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.deploy-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi");

        infra.resource(Package.class,"34-osgi.wiring.package-org.apache.felix.ipojo.everest.osgi.packages-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.core-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.impl-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework.wiring-1.0.0");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework.startlevel-1.0.0");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"34-org.apache.felix.ipojo.everest-osgi")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.services-1.0.0.SNAPSHOT");

        infra.resource(Package.class,"35-osgi.wiring.package-fr.liglab.adele.icasa.remote.util-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"35-context.remote");

        infra.resource(Package.class,"35-osgi.wiring.package-fr.liglab.adele.icasa.remote-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"35-context.remote");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.clock-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.clock.util-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.location-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs-0.0.0");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"7-osgi.wiring.package-org.json-0.0.0");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.listener-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs.core-0.0.0");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"40-osgi.wiring.package-org.barjo.atmosgi-0.0.0");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"47-osgi.wiring.package-org.atmosphere.cpr-1.1.0.RC1");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"47-osgi.wiring.package-org.atmosphere.interceptor-1.1.0.RC1");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"47-osgi.wiring.package-org.atmosphere.handler-1.1.0.RC1");

        infra.resource(Bundle.class,"35-context.remote")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.http-1.2.1");

        infra.resource(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.core-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"32-org.apache.felix.ipojo.everest-core");

        infra.resource(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.filters-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"32-org.apache.felix.ipojo.everest-core");

        infra.resource(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.internals-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"32-org.apache.felix.ipojo.everest-core");

        infra.resource(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.services-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"32-org.apache.felix.ipojo.everest-core");

        infra.resource(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.impl-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"32-org.apache.felix.ipojo.everest-core");

        infra.resource(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.managers.everest-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"32-org.apache.felix.ipojo.everest-core");

        infra.resource(Bundle.class,"32-org.apache.felix.ipojo.everest-core")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"32-org.apache.felix.ipojo.everest-core")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"32-org.apache.felix.ipojo.everest-core")
                .dependsOn(Package.class,"9-osgi.wiring.package-org.osgi.service.event-1.3.0");

        infra.resource(Bundle.class,"32-org.apache.felix.ipojo.everest-core")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"32-org.apache.felix.ipojo.everest-core")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Package.class,"33-osgi.wiring.package-org.apache.felix.ipojo.everest.ipojo-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.handlers.configuration-1.10.0");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.core-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.impl-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.util-1.10.0");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.handlers.providedservice-1.10.0");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.extender-1.10.0");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.services-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.handlers.dependency-1.10.0");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.metadata-1.10.0");

        infra.resource(Bundle.class,"33-org.apache.felix.ipojo.everest-ipojo")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Package.class,"38-osgi.wiring.package-javax.mail.internet-1.4.0")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Package.class,"38-osgi.wiring.package-com.sun.mail.util-1.4.3")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Package.class,"38-osgi.wiring.package-com.sun.mail.handlers-1.4.3")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Package.class,"38-osgi.wiring.package-javax.mail.event-1.4.0")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Package.class,"38-osgi.wiring.package-com.sun.mail.smtp-1.4.3")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Package.class,"38-osgi.wiring.package-com.sun.mail.util.logging-1.4.3")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Package.class,"38-osgi.wiring.package-javax.mail.search-1.4.0")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Package.class,"38-osgi.wiring.package-javax.mail.util-1.4.0")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Package.class,"38-osgi.wiring.package-com.sun.mail.pop3-1.4.3")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Package.class,"38-osgi.wiring.package-javax.mail-1.4.0")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Package.class,"38-osgi.wiring.package-com.sun.mail.imap-1.4.3")
                .dependsOn(Bundle.class,"38-javax.mail");

        infra.resource(Bundle.class,"38-javax.mail")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.security.sasl-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"38-javax.mail")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.security.auth.x500-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"38-javax.mail")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.security.auth.callback-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"38-javax.mail")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.net.ssl-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"38-javax.mail")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.net-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"38-javax.mail")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.activation-0.0.0.1_007_JavaSE");

        infra.resource(Package.class,"39-osgi.wiring.package-fr.liglab.adele.icasa.common.impl-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"39-common");

        infra.resource(Package.class,"39-osgi.wiring.package-fr.liglab.adele.icasa.common-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"39-common");

        infra.resource(Bundle.class,"39-common")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"39-common")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"39-common")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"39-common")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.ser.impl-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.deser.impl-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.type-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.jsonschema-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.annotation-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.deser-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.module-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.core.io-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.ext-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.core.type-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.exc-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.deser.std-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.annotation-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.ser-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.jsonFormatVisitors-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.node-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.core.format-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.ser.std-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.core-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.core.json-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.core.sym-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.jsontype-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.core.util-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.core.base-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.cfg-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.introspect-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.util-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Package.class,"36-osgi.wiring.package-com.fasterxml.jackson.databind.jsontype.impl-2.2.0")
                .dependsOn(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.services-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.namespace-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.w3c.dom-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet-2.5.0");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.http-1.2.1");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet.http-2.5.0");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.w3c.dom.bootstrap-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.impl-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.datatype-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.parsers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"36-org.apache.felix.ipojo.everest-servlet")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.w3c.dom.ls-0.0.0.1_007_JavaSE");

        infra.resource(Package.class,"37-osgi.wiring.package-fr.liglab.adele.osgi.shell.installer-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"37-gogo.adapter");

        infra.resource(Bundle.class,"37-gogo.adapter")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"37-gogo.adapter")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"37-gogo.adapter")
                .dependsOn(Package.class,"7-osgi.wiring.package-org.json-0.0.0");

        infra.resource(Bundle.class,"37-gogo.adapter")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"37-gogo.adapter")
                .dependsOn(Package.class,"12-osgi.wiring.package-org.apache.felix.service.command-0.10.0");

        infra.resource(Bundle.class,"37-gogo.adapter")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"37-gogo.adapter")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"37-gogo.adapter")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"37-gogo.adapter")
                .dependsOn(Package.class,"10-osgi.wiring.package-org.apache.felix.fileinstall-3.1.4");

        infra.resource(Bundle.class,"37-gogo.adapter")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose-1.2.2.SNAPSHOT");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.component-0.0.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs-0.0.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.core-0.0.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose.registry-1.2.2.SNAPSHOT");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.remoteserviceadmin-1.0.1");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.container.servlet-0.0.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet-2.5.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.component.ioc-0.0.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.container-0.0.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose.introspect-1.2.2.SNAPSHOT");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose.util-1.2.2.SNAPSHOT");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.http-1.2.1");

        infra.resource(Bundle.class,"42-jersey-exporter")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Package.class,"43-osgi.wiring.package-javax.xml.bind.annotation-2.2.1")
                .dependsOn(Bundle.class,"43-jaxb-api");

        infra.resource(Package.class,"43-osgi.wiring.package-javax.xml.bind-2.2.1")
                .dependsOn(Bundle.class,"43-jaxb-api");

        infra.resource(Package.class,"43-osgi.wiring.package-javax.xml.bind.util-2.2.1")
                .dependsOn(Bundle.class,"43-jaxb-api");

        infra.resource(Package.class,"43-osgi.wiring.package-javax.xml.bind.helpers-2.2.1")
                .dependsOn(Bundle.class,"43-jaxb-api");

        infra.resource(Package.class,"43-osgi.wiring.package-javax.xml.bind.annotation.adapters-2.2.1")
                .dependsOn(Bundle.class,"43-jaxb-api");

        infra.resource(Package.class,"43-osgi.wiring.package-javax.xml.bind.attachment-2.2.1")
                .dependsOn(Bundle.class,"43-jaxb-api");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.stream-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform.sax-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.datatype-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax.helpers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.namespace-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.parsers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform.dom-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.w3c.dom-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.activation-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform.stream-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax.ext-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"43-jaxb-api")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.validation-0.0.0.1_007_JavaSE");

        infra.resource(Package.class,"40-osgi.wiring.package-org.barjo.atmosgi-0.0.0")
                .dependsOn(Bundle.class,"40-atmosgi-service");

        infra.resource(Bundle.class,"40-atmosgi-service")
                .dependsOn(Package.class,"47-osgi.wiring.package-org.atmosphere.cpr-1.1.0.RC1");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"62-osgi.wiring.package-fr.liglab.adele.icasa.service.preferences-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"62-osgi.wiring.package-fr.liglab.adele.icasa.service.zone.dimension.calculator-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"44-osgi.wiring.package-fr.liglab.adele.icasa.commands.impl-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.clock-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"27-osgi.wiring.package-org.slf4j-1.6.4");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.listener-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"62-osgi.wiring.package-fr.liglab.adele.icasa.service.zone.size.calculator-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.location-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"7-osgi.wiring.package-org.json-0.0.0");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"62-osgi.wiring.package-fr.liglab.adele.icasa.service.scheduler-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"41-technical.services.impl")
                .dependsOn(Package.class,"26-osgi.wiring.package-org.ow2.chameleon.sharedprefs-1.0.0");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi.inject-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.header.reader-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.reflection-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.component.ioc-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.header-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.impl.provider.xml-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.api.uri-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.scanning-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.impl.provider.header-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.component-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.provider.jaxb-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.scanning.uri-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.factory-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.impl-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.util-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-javax.ws.rs-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi.service-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-javax.ws.rs.core-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.api.provider.jaxb-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.localization-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.api.representation-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.provider-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-javax.ws.rs.ext-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.osgi-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.impl.provider.entity-0.0.0")
                .dependsOn(Bundle.class,"46-com.sun.jersey.jersey-core");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform.sax-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.stream-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.imageio-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"38-osgi.wiring.package-javax.mail.util-1.4.0");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform.stream-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.imageio.stream-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.activation-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.bind.annotation-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.w3c.dom-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"38-osgi.wiring.package-javax.mail.internet-1.4.0");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform.dom-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.parsers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.imageio.spi-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.bind-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"38-osgi.wiring.package-javax.mail-1.4.0");

        infra.resource(Bundle.class,"46-com.sun.jersey.jersey-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax-0.0.0.1_007_JavaSE");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.interceptor-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.client-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.container-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.container.version-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.cache-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.di-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.util.uri-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.cpr-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.websocket.protocol-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.handler-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.config-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.util-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.websocket-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Package.class,"47-osgi.wiring.package-org.atmosphere.config.service-1.1.0.RC1")
                .dependsOn(Bundle.class,"47-atmosgi-component");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.parsers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"27-osgi.wiring.package-org.slf4j-1.6.4");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet.http-2.5.0");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.w3c.dom-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet-2.5.0");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.bind-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"47-atmosgi-component")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.http-1.2.1");

        infra.resource(Package.class,"44-osgi.wiring.package-fr.liglab.adele.icasa.location.impl-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"44-context.impl");

        infra.resource(Package.class,"44-osgi.wiring.package-fr.liglab.adele.icasa.location.util-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"44-context.impl");

        infra.resource(Package.class,"44-osgi.wiring.package-fr.liglab.adele.icasa.commands.impl-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"44-context.impl");

        infra.resource(Bundle.class,"44-context.impl")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.listener-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"44-context.impl")
                .dependsOn(Package.class,"7-osgi.wiring.package-org.json-0.0.0");

        infra.resource(Bundle.class,"44-context.impl")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"44-context.impl")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.location-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"44-context.impl")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"44-context.impl")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"44-context.impl")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"44-context.impl")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"44-context.impl")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa-1.0.1.SNAPSHOT");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io-2.4.0")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io.filefilter-1.4.9999")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io.input-2.4.0")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io.output-1.4.9999")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io.input-1.4.9999")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io.comparator-1.4.9999")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io.comparator-2.4.0")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io.monitor-2.4.0")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io.output-2.4.0")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io.filefilter-2.4.0")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Package.class,"45-osgi.wiring.package-org.apache.commons.io-1.4.9999")
                .dependsOn(Bundle.class,"45-org.apache.commons.io");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose.util-1.2.2.SNAPSHOT");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose.introspect-1.2.2.SNAPSHOT");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"10-osgi.wiring.package-org.apache.felix.fileinstall-3.1.4");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose-1.2.2.SNAPSHOT");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.remoteserviceadmin-1.0.1");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose.api-1.2.2.SNAPSHOT");

        infra.resource(Bundle.class,"51-json-configurator")
                .dependsOn(Package.class,"7-osgi.wiring.package-org.ow2.chameleon.json-1.1.0");

        infra.resource(Package.class,"50-osgi.wiring.package-com.sun.jersey.multipart-1.9.0")
                .dependsOn(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart");

        infra.resource(Package.class,"50-osgi.wiring.package-com.sun.jersey.multipart.file-1.9.0")
                .dependsOn(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart");

        infra.resource(Package.class,"50-osgi.wiring.package-com.sun.jersey.multipart.impl-1.9.0")
                .dependsOn(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.reflection-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.model.parameter.multivalued-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.dispatch-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.container-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.core-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.inject-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs.core-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.model.method.dispatch-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.model-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.util-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.header-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"30-osgi.wiring.package-org.jvnet.mimepull-1.4.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs.ext-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.container-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.component-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi.inject-0.0.0");

        infra.resource(Bundle.class,"50-com.sun.jersey.contribs.jersey-multipart")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi-0.0.0");

        infra.resource(Package.class,"49-osgi.wiring.package-fr.imag.adele.appstore.gateway.api-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"49-dp-rest-installer");

        infra.resource(Package.class,"49-osgi.wiring.package-fr.imag.adele.appstore.gateway.util-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"49-dp-rest-installer");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.header-0.0.0");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"4-osgi.wiring.package-org.osgi.service.deploymentadmin-1.1.0");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs-0.0.0");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"27-osgi.wiring.package-org.slf4j-1.6.4");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"7-osgi.wiring.package-org.ow2.chameleon.json-1.1.0");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"50-osgi.wiring.package-com.sun.jersey.multipart-1.9.0");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs.core-0.0.0");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"49-dp-rest-installer")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.container-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.probes-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.provider-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.container.httpserver-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.container.httpserver-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.dispatch-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.application-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.model.parameter-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.uri-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.spi.component-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.component-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.uri.rules-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.model.parameter.multivalued-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.container.servlet-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.monitoring-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.research.ws.wadl-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.inject-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.wadl-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.template-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.wadl.generators-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.model-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.model.method-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.core-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.view-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.container.filter-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.resource-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.modelapi.validation-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.wadl.generators.resourcedoc-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.container-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.monitoring-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.uri.rules.automata-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.modelapi.annotation-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.resource-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.container.filter-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.template-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.model-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.wadl.generators.resourcedoc.model-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.uri.rules-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.wadl-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.wadl.generators.resourcedoc.xhtml-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.managedbeans-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.wadl-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.container.servlet-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.cdi-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.container-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.ejb-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.osgi-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.api-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.api.wadl.config-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.spi.scanning-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Package.class,"48-osgi.wiring.package-com.sun.jersey.server.impl.model.method.dispatch-0.0.0")
                .dependsOn(Bundle.class,"48-com.sun.jersey.jersey-server");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.component-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform.stream-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.factory-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.util-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.namespace-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.impl-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs.ext-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.reflection-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.naming-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.osgi-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs.core-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.transform.sax-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.xml.sax-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet-2.5.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi.service-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.header.reader-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.bind-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.bind.annotation.adapters-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.scanning-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.annotation-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"14-osgi.wiring.package-javax.servlet.http-2.5.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.api.representation-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.api.uri-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi.inject-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.parsers-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.header-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.component.ioc-0.0.0");

        infra.resource(Bundle.class,"48-com.sun.jersey.jersey-server")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.xml.bind.annotation-0.0.0.1_007_JavaSE");

        infra.resource(Package.class,"55-osgi.wiring.package-fr.liglab.adele.icasa.application-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"55-application.api");

        infra.resource(Bundle.class,"55-application.api")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"55-application.api")
                .dependsOn(Package.class,"39-osgi.wiring.package-fr.liglab.adele.icasa.common-1.0.1.SNAPSHOT");

        infra.resource(Package.class,"54-osgi.wiring.package-fr.liglab.adele.icasa.device.manager-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"54-device.manager.api");

        infra.resource(Bundle.class,"54-device.manager.api")
                .dependsOn(Package.class,"55-osgi.wiring.package-fr.liglab.adele.icasa.application-1.0.1.SNAPSHOT");

        infra.resource(Package.class,"53-osgi.wiring.package-fr.liglab.adele.rondo.cloner-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"53-fr.liglab.adele.rondo.cloner");

        infra.resource(Package.class,"53-osgi.wiring.package-fr.liglab.adele.rondo.infra.annotations-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"53-fr.liglab.adele.rondo.cloner");

        infra.resource(Package.class,"53-osgi.wiring.package-fr.liglab.adele.rondo.infra.model-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"53-fr.liglab.adele.rondo.cloner");

        infra.resource(Package.class,"53-osgi.wiring.package-fr.liglab.adele.rondo.infra.impl-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"53-fr.liglab.adele.rondo.cloner");

        infra.resource(Bundle.class,"53-fr.liglab.adele.rondo.cloner")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework.wiring-1.0.0");

        infra.resource(Bundle.class,"53-fr.liglab.adele.rondo.cloner")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.impl-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"53-fr.liglab.adele.rondo.cloner")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"53-fr.liglab.adele.rondo.cloner")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"53-fr.liglab.adele.rondo.cloner")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"53-fr.liglab.adele.rondo.cloner")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"53-fr.liglab.adele.rondo.cloner")
                .dependsOn(Package.class,"12-osgi.wiring.package-org.apache.felix.service.command-0.10.0");

        infra.resource(Bundle.class,"53-fr.liglab.adele.rondo.cloner")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"53-fr.liglab.adele.rondo.cloner")
                .dependsOn(Package.class,"45-osgi.wiring.package-org.apache.commons.io-1.4.9999");

        infra.resource(Bundle.class,"53-fr.liglab.adele.rondo.cloner")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.services-1.0.0.SNAPSHOT");

        infra.resource(Package.class,"52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"52-fr.liglab.adele.rondo.deployer");

        infra.resource(Package.class,"52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.util-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"52-fr.liglab.adele.rondo.deployer");

        infra.resource(Package.class,"52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.transaction.impl-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"52-fr.liglab.adele.rondo.deployer");

        infra.resource(Package.class,"52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.impl-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"52-fr.liglab.adele.rondo.deployer");

        infra.resource(Package.class,"52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.transaction-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"52-fr.liglab.adele.rondo.deployer");

        infra.resource(Package.class,"52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.processor.impl-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"52-fr.liglab.adele.rondo.deployer");

        infra.resource(Package.class,"52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.processor-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"52-fr.liglab.adele.rondo.deployer");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.impl-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"57-osgi.wiring.package-fr.liglab.adele.rondo.infra.impl-0.0.0");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"57-osgi.wiring.package-fr.liglab.adele.rondo.infra.annotations-0.0.0");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"45-osgi.wiring.package-org.apache.commons.io-1.4.9999");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"57-osgi.wiring.package-fr.liglab.adele.rondo.infra.model-0.0.0");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.services-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.util-1.10.0");

        infra.resource(Bundle.class,"52-fr.liglab.adele.rondo.deployer")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework.wiring-1.0.0");

        infra.resource(Bundle.class,"59-clock.system.impl")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"59-clock.system.impl")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"59-clock.system.impl")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"59-clock.system.impl")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.clock-1.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"59-clock.system.impl")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device.util-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device.power-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device.motion-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device.presence-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device.temperature-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.clock.util-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.clock-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device.bathroomscale-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device.gasSensor-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.listener-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device.light-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device.settopbox-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.device.sound-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa.location-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"58-context.api");

        infra.resource(Bundle.class,"58-context.api")
                .dependsOn(Package.class,"7-osgi.wiring.package-org.json-0.0.0");

        infra.resource(Package.class,"57-osgi.wiring.package-fr.liglab.adele.rondo.infra.impl-0.0.0")
                .dependsOn(Bundle.class,"57-fr.liglab.adele.rondo.core");

        infra.resource(Package.class,"57-osgi.wiring.package-fr.liglab.adele.rondo.infra.model-0.0.0")
                .dependsOn(Bundle.class,"57-fr.liglab.adele.rondo.core");

        infra.resource(Package.class,"57-osgi.wiring.package-fr.liglab.adele.rondo.infra.annotations-0.0.0")
                .dependsOn(Bundle.class,"57-fr.liglab.adele.rondo.core");

        infra.resource(Bundle.class,"57-fr.liglab.adele.rondo.core")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"57-fr.liglab.adele.rondo.core")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"57-fr.liglab.adele.rondo.core")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"57-fr.liglab.adele.rondo.core")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Package.class,"56-osgi.wiring.package-com.sun.jersey.api.client-0.0.0")
                .dependsOn(Bundle.class,"56-com.sun.jersey.jersey-client");

        infra.resource(Package.class,"56-osgi.wiring.package-com.sun.jersey.client.impl.async-0.0.0")
                .dependsOn(Bundle.class,"56-com.sun.jersey.jersey-client");

        infra.resource(Package.class,"56-osgi.wiring.package-com.sun.jersey.client.proxy-0.0.0")
                .dependsOn(Bundle.class,"56-com.sun.jersey.jersey-client");

        infra.resource(Package.class,"56-osgi.wiring.package-com.sun.ws.rs.ext-0.0.0")
                .dependsOn(Bundle.class,"56-com.sun.jersey.jersey-client");

        infra.resource(Package.class,"56-osgi.wiring.package-com.sun.jersey.client.impl-0.0.0")
                .dependsOn(Bundle.class,"56-com.sun.jersey.jersey-client");

        infra.resource(Package.class,"56-osgi.wiring.package-com.sun.jersey.api.client.filter-0.0.0")
                .dependsOn(Bundle.class,"56-com.sun.jersey.jersey-client");

        infra.resource(Package.class,"56-osgi.wiring.package-com.sun.jersey.api.client.config-0.0.0")
                .dependsOn(Bundle.class,"56-com.sun.jersey.jersey-client");

        infra.resource(Package.class,"56-osgi.wiring.package-com.sun.jersey.api.client.async-0.0.0")
                .dependsOn(Bundle.class,"56-com.sun.jersey.jersey-client");

        infra.resource(Package.class,"56-osgi.wiring.package-com.sun.jersey.client.osgi-0.0.0")
                .dependsOn(Bundle.class,"56-com.sun.jersey.jersey-client");

        infra.resource(Package.class,"56-osgi.wiring.package-com.sun.jersey.client.urlconnection-0.0.0")
                .dependsOn(Bundle.class,"56-com.sun.jersey.jersey-client");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.reflection-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.component-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"0-osgi.wiring.package-javax.net.ssl-0.0.0.1_007_JavaSE");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.header-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi.service-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.factory-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.util-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi.inject-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.spi-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs.core-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-javax.ws.rs.ext-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.provider-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"46-osgi.wiring.package-com.sun.jersey.core.spi.component.ioc-0.0.0");

        infra.resource(Bundle.class,"56-com.sun.jersey.jersey-client")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Package.class,"63-osgi.wiring.package-org.apache.felix.ipojo.everest.system.mx-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"63-org.apache.felix.ipojo.everest-system");

        infra.resource(Package.class,"63-osgi.wiring.package-org.apache.felix.ipojo.everest.system-1.0.0.SNAPSHOT")
                .dependsOn(Bundle.class,"63-org.apache.felix.ipojo.everest-system");

        infra.resource(Bundle.class,"63-org.apache.felix.ipojo.everest-system")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"63-org.apache.felix.ipojo.everest-system")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.services-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"63-org.apache.felix.ipojo.everest-system")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.impl-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"63-org.apache.felix.ipojo.everest-system")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"63-org.apache.felix.ipojo.everest-system")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"63-org.apache.felix.ipojo.everest-system")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Package.class,"62-osgi.wiring.package-fr.liglab.adele.icasa.service.zone.size.calculator-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"62-technical.services.api");

        infra.resource(Package.class,"62-osgi.wiring.package-fr.liglab.adele.icasa.service.scheduler-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"62-technical.services.api");

        infra.resource(Package.class,"62-osgi.wiring.package-fr.liglab.adele.icasa.service.preferences-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"62-technical.services.api");

        infra.resource(Package.class,"62-osgi.wiring.package-fr.liglab.adele.icasa.service.zone.dimension.calculator-1.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"62-technical.services.api");

        infra.resource(Bundle.class,"62-technical.services.api")
                .dependsOn(Package.class,"58-osgi.wiring.package-fr.liglab.adele.icasa-1.0.1.SNAPSHOT");

        infra.resource(Package.class,"61-osgi.wiring.package-fr.liglab.adele.rondo.infra.system-0.0.1.SNAPSHOT")
                .dependsOn(Bundle.class,"61-fr.liglab.adele.rondo.system-extension");

        infra.resource(Bundle.class,"61-fr.liglab.adele.rondo.system-extension")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.services-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"61-fr.liglab.adele.rondo.system-extension")
                .dependsOn(Package.class,"32-osgi.wiring.package-org.apache.felix.ipojo.everest.impl-1.0.0.SNAPSHOT");

        infra.resource(Bundle.class,"61-fr.liglab.adele.rondo.system-extension")
                .dependsOn(Package.class,"52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.processor-0.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"61-fr.liglab.adele.rondo.system-extension")
                .dependsOn(Package.class,"52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment-0.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"61-fr.liglab.adele.rondo.system-extension")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"61-fr.liglab.adele.rondo.system-extension")
                .dependsOn(Package.class,"57-osgi.wiring.package-fr.liglab.adele.rondo.infra.impl-0.0.0");

        infra.resource(Bundle.class,"61-fr.liglab.adele.rondo.system-extension")
                .dependsOn(Package.class,"52-osgi.wiring.package-fr.liglab.adele.rondo.infra.deployment.transaction-0.0.1.SNAPSHOT");

        infra.resource(Bundle.class,"61-fr.liglab.adele.rondo.system-extension")
                .dependsOn(Package.class,"57-osgi.wiring.package-fr.liglab.adele.rondo.infra.model-0.0.0");

        infra.resource(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose.introspect-1.2.2.SNAPSHOT")
                .dependsOn(Bundle.class,"60-rose-core");

        infra.resource(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose-1.2.2.SNAPSHOT")
                .dependsOn(Bundle.class,"60-rose-core");

        infra.resource(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose.registry-1.2.2.SNAPSHOT")
                .dependsOn(Bundle.class,"60-rose-core");

        infra.resource(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose.util-1.2.2.SNAPSHOT")
                .dependsOn(Bundle.class,"60-rose-core");

        infra.resource(Package.class,"60-osgi.wiring.package-org.ow2.chameleon.rose.api-1.2.2.SNAPSHOT")
                .dependsOn(Bundle.class,"60-rose-core");

        infra.resource(Bundle.class,"60-rose-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.framework>1.6.0");

        infra.resource(Bundle.class,"60-rose-core")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo-1.10.0");

        infra.resource(Bundle.class,"60-rose-core")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.cm-1.4.0");

        infra.resource(Bundle.class,"60-rose-core")
                .dependsOn(Package.class,"25-osgi.wiring.package-org.osgi.service.remoteserviceadmin-1.0.1");

        infra.resource(Bundle.class,"60-rose-core")
                .dependsOn(Package.class,"11-osgi.wiring.package-org.osgi.service.log-1.3.0");

        infra.resource(Bundle.class,"60-rose-core")
                .dependsOn(Package.class,"16-osgi.wiring.package-org.apache.felix.ipojo.architecture-1.10.0");

        infra.resource(Bundle.class,"60-rose-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.service.packageadmin-1.2.0");

        infra.resource(Bundle.class,"60-rose-core")
                .dependsOn(Package.class,"0-osgi.wiring.package-org.osgi.util.tracker-1.5.0");

        infra.resource(Bundle.class,"28-slf4j.log4j12")
                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j-1.2.16");

        infra.resource(Bundle.class,"27-slf4j.api-active")
                .dependsOn(Bundle.class,"27-slf4j.api-installed");

        infra.resource(Bundle.class,"28-slf4j.log4j12")
                .dependsOn(Bundle.class,"27-slf4j.api-installed");

        infra.resource(Bundle.class,"27-slf4j.api-active")
                .dependsOn(Package.class,"28-osgi.wiring.package-org.slf4j.impl-1.6.4");

//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"27-osgi.wiring.package-org.slf4j.helpers-1.6.4");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"27-osgi.wiring.package-org.slf4j.spi-1.6.4");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"27-osgi.wiring.package-org.slf4j-1.6.4");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.nt-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.jmx-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.varia-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.or.jms-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.net-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.jdbc-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.spi-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.or.sax-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.config-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5.config-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5.util-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.pattern-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.helpers-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5.viewer.categoryexplorer-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.nt-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.lf5.viewer.images-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.xml-1.2.16");
//
//        infra.resource(Bundle.class,"28-slf4j.log4j12")
//                .dependsOn(Package.class,"3-osgi.wiring.package-org.apache.log4j.or-1.2.16");

    }
}
;