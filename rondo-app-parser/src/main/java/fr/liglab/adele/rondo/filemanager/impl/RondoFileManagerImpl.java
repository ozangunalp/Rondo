package fr.liglab.adele.rondo.filemanager.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.liglab.adele.rondo.RondoApplicationContext;
import fr.liglab.adele.rondo.RondoFileManager;
import fr.liglab.adele.rondo.RondoParser;
import fr.liglab.adele.rondo.exception.InvalidConfigurationException;
import fr.liglab.adele.rondo.exception.RondoParserException;
import fr.liglab.adele.rondo.manager.ApplicationContextFactory;
import fr.liglab.adele.rondo.model.Application;
import fr.liglab.adele.rondo.model.Rondo;

/**
 * 
 * File Manager model obtained from Cilia
 * 
 * @author gunalp (ozan.gunalp<at>imag.fr)
 * 
 */
@Component
@Provides
@Instantiate
public class RondoFileManagerImpl implements RondoFileManager {

	BundleContext context;

	@Requires
	RondoParser parser;

	@Requires
	ApplicationContextFactory rAppManager;

	protected static Logger logger = LoggerFactory.getLogger("rondo.app.parser");

	private final CreatorThread creator;

	private final Map<File, List<Application>> handledFiles;

	public RondoFileManagerImpl(BundleContext context) {
		this.context = context;
		handledFiles = Collections.synchronizedMap(new HashMap<File, List<Application>>());
		creator = new CreatorThread();
	}

	@Validate
	public void start() {
		new Thread(creator).start();
	}

	@Invalidate
	public void stop() {
		creator.stop();
		for (File f : handledFiles.keySet()) {
			stopManagementFor(f);
		}
	}

	@Override
	public void loadFile(File apps) {
		creator.addFile(apps);
	}

	@Override
	public void unloadFile(File apps) {
		creator.removeFile(apps);
		stopManagementFor(apps);
	}

	@Override
	public void updateFile(File apps) {
		creator.removeFile(apps);
		stopManagementFor(apps);
		creator.addFile(apps);
	}

	public void startManagementFor(File file) throws InvalidConfigurationException {
		System.out.println("Starting Management for " + file.getName());
		List<Application> appsList = new ArrayList<Application>();
		Rondo rondo = null;
		try {
			rondo = parser.parse(file);
		} catch (RondoParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (rondo != null && rondo.getApplication().size() > 0) {
			for (Application application : rondo.getApplication()) {
				RondoApplicationContext appContext = rAppManager.createApplication(application);
				appsList.add(application);
				appContext.start();
			}
			handledFiles.put(file, appsList);
		} else {
			logger.debug("Doesn't have any applications to process");
		}
	}

	public void stopManagementFor(File file) {
		System.out.println("Stopping Management for " + file.getName());
		List<Application> appList = null;
		appList = handledFiles.remove(file);
		if (appList != null) {
			for (Application application : appList) {
				rAppManager.destroyApplication(application);
			}
			appList.clear();
		}
	}

	/**
	 * The creator thread analyzes arriving files to create Rondo Applications
	 * Obtained from iPOJO
	 */
	private class CreatorThread implements Runnable {

		/**
		 * Is the creator thread started?
		 */
		private boolean m_started = true;

		/**
		 * The list of bundle that are going to be analyzed.
		 */
		private final List<File> appFiles = new ArrayList<File>();

		/**
		 * A bundle is arriving. This method is synchronized to avoid concurrent
		 * modification of the waiting list.
		 * 
		 * @param file
		 *            the new bundle
		 */
		public synchronized void addFile(File file) {
			appFiles.add(file);
			notifyAll(); // Notify the thread to force the process.
			logger.debug("Creator thread is going to analyze the file " + file.getName() + " List : " + appFiles);
		}

		/**
		 * A bundle is leaving. If the bundle was not already processed, the
		 * bundle is remove from the waiting list. This method is synchronized
		 * to avoid concurrent modification of the waiting list.
		 * 
		 * @param bundle
		 *            the leaving bundle.
		 */
		public synchronized void removeFile(File file) {
			appFiles.remove(file);
		}

		/**
		 * Stops the creator thread.
		 */
		public synchronized void stop() {
			m_started = false;
			appFiles.clear();
			notifyAll();
		}

		@Override
		public void run() {
			// logger.debug("Creator thread is starting");
			boolean started;
			synchronized (this) {
				started = m_started;
			}
			while (started) {
				File file;
				synchronized (this) {
					while (m_started && appFiles.isEmpty()) {
						try {
							logger.debug("Creator thread is waiting - Nothing to do");
							wait();
						} catch (InterruptedException e) {
							// Interruption, re-check the condition
						}
					}
					if (!m_started) {
						logger.debug("Creator thread is stopping");
						return; // The thread must be stopped immediately.
					} else {
						// The bundle list is not empty, get the bundle.
						// The bundle object is collected inside the
						// synchronized block to avoid
						// concurrent modification. However the real process is
						// made outside the
						// mutual exclusion area
						file = appFiles.remove(0);
					}
				}
				// Process ...
				logger.debug("Creator thread is processing " + file.getName());
				try {
					startManagementFor(file);
				} catch (Throwable e) {
					// To be sure to not kill the thread, we catch all
					// exceptions and errors
					logger.error(
							"An error occurs when analyzing the content or starting the management of "
									+ file.getName(), e.getStackTrace());
				}
				synchronized (this) {
					started = m_started;
				}
			}
		}

	}
}
