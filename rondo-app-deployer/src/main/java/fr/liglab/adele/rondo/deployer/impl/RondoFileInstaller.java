package fr.liglab.adele.rondo.deployer.impl;

import java.io.File;

import org.apache.felix.fileinstall.ArtifactInstaller;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.liglab.adele.rondo.RondoFileManager;

/**
 * Hello world!
 * 
 */
@Component
@Provides
@Instantiate
public class RondoFileInstaller implements ArtifactInstaller {

	protected static Logger logger = LoggerFactory.getLogger("rondo.fileinstall.deployer");

	BundleContext context;

	@Requires
	RondoFileManager fileManager;

	public RondoFileInstaller(BundleContext context) {
		this.context = context;
	}

	@Override
	public boolean canHandle(File file) {
		if (file.getName().endsWith(".rondo")) {
			return true;
		}
		return false;
	}

	@Override
	public void install(File file) throws Exception {
		logger.info("Installing file " + file.getName());
		System.out.println("Installing file " + file.getName());
		fileManager.loadFile(file);
	}

	@Override
	public void uninstall(File file) throws Exception {
		logger.info("Uninstalling file " + file.getName());
		System.out.println("Uninstalling file " + file.getName());
		fileManager.unloadFile(file);
	}

	@Override
	public void update(File file) throws Exception {
		fileManager.updateFile(file);
	}

}
