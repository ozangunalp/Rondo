package fr.liglab.adele.rondo;

import java.io.File;

import fr.liglab.adele.rondo.model.Rondo;

public interface RondoParser {

	Rondo parse(File file);

}
