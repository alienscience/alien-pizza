package uk.org.alienscience.alien_pizza.base.annotation;

import java.lang.annotation.*;

/**
 * TODO: document
 */
@Target(value= ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
@Documented
public @interface WebServlet {
    String[] value() default {};
}
