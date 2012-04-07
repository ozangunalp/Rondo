package fr.liglab.adele.rondo;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;

@Component
@Instantiate
public class HelloComponent {

	public HelloComponent() {
		System.out.println("Hello rondo-ipojo-runtime");
	}

}
