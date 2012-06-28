package fr.liglab.adele.rondo;

import java.io.File;

public interface RondoFileManager {

	public void loadFile(File apps);

	public void unloadFile(File apps);

	public void updateFile(File apps);

}
