package fr.liglab.adele.rondo.groovy.script;

import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Validate;
import org.osgi.framework.BundleContext;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 4/24/13
 * Time: 11:11 PM
 */

@Component
@Instantiate
public class ScriptEngine {

    private final BundleContext m_context;

    private GroovyScriptEngine gse;

    public ScriptEngine(BundleContext context) {
        m_context = context;
    }

    @Validate
    public void starting() {
        ClassLoader bundleClassloader = getClass().getClassLoader();
        String[] roots = new String[]{"scripts"};
        try {
            gse = new GroovyScriptEngine(roots, bundleClassloader);
            Binding binding = new Binding();
            binding.setVariable("input", "world");
            Script s = gse.createScript("hello.groovy", binding);
            s.run();
            System.out.println(binding.getVariable("output"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ResourceException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ScriptException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Invalidate
    public void stopping() {

    }

}
