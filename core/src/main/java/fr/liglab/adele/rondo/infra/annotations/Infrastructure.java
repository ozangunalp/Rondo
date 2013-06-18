package fr.liglab.adele.rondo.infra.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 6/14/13
 * Time: 12:09 PM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Infrastructure {

    String name() default "";

    boolean immediate() default true;

}

