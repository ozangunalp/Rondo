package fr.liglab.adele.rondo.parser.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.StaticServiceProperty;

import fr.liglab.adele.rondo.RondoParser;
import fr.liglab.adele.rondo.model.ObjectFactory;
import fr.liglab.adele.rondo.model.Rondo;

@Component
@Provides
@Instantiate
@StaticServiceProperty(name = "parser-type", value = "jaxb", type = "string")
public class RondoJAXBParserImpl implements RondoParser {

	private final String NAMESPACE = "fr.liglab.adele.rondo.model";

	JAXBContext jc;

	public RondoJAXBParserImpl() {
		try {
			ClassLoader cl = ObjectFactory.class.getClassLoader();
			jc = JAXBContext.newInstance(NAMESPACE, cl);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Rondo parse(File file) {
		try {
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Rondo como = (Rondo) unmarshaller.unmarshal(file);
			return como;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

}
