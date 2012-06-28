package fr.liglab.adele.rondo.deployer.impl;

import java.io.File;

import org.apache.felix.fileinstall.ArtifactInstaller;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.osgi.framework.BundleContext;

import fr.liglab.adele.rondo.RondoFileManager;

/**
 * Hello world!
 * 
 */
@Component
@Provides
@Instantiate
public class RondoFileInstaller implements ArtifactInstaller {

	BundleContext context;

	@Requires
	RondoFileManager fileManager;

	public RondoFileInstaller(BundleContext context) {
		this.context = context;
	}

	public boolean canHandle(File file) {
		if (file.getName().endsWith(".rondo")) {
			return true;
		}
		return false;
	}

	public void install(File file) throws Exception {
		fileManager.loadFile(file);
	}

	public void uninstall(File file) throws Exception {
		fileManager.unloadFile(file);

	}

	public void update(File file) throws Exception {
		fileManager.updateFile(file);
	}

}
