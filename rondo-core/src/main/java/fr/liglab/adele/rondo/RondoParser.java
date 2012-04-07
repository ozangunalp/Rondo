package fr.liglab.adele.rondo;

import java.io.File;

import fr.liglab.adele.rondo.model.Application;

public interface RondoParser {

	Application parse(File file);

}
