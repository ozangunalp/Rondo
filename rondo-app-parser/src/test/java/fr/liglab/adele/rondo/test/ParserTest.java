package fr.liglab.adele.rondo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import fr.liglab.adele.rondo.RondoParser;
import fr.liglab.adele.rondo.exception.RondoParserException;
import fr.liglab.adele.rondo.model.Application;
import fr.liglab.adele.rondo.model.Rondo;
import fr.liglab.adele.rondo.parser.impl.RondoJAXBParserImpl;

public class ParserTest {

	@Test
	public void testXMLLoading() throws RondoParserException {
		File f = new File("src/test/resources/rondo-application.xml");
		assertNotNull(f);
		RondoParser parser = new RondoJAXBParserImpl();
		Rondo r = parser.parse(f);
		assertNotNull(r);
		List<Application> as = r.getApplication();
		assertTrue(as.size() > 0);
		Application application = as.get(0);
		assertNotNull(application);
		assertEquals(application.getName(), "app1");
	}
}
