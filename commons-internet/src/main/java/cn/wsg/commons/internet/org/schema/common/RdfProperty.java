package cn.wsg.commons.internet.org.schema.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated type is a RDF property.
 *
 * @author Kingen
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface RdfProperty {

    /**
     * Relates a property to a class that is (one of) the type(s) the property is expected to be used on.
     */
    Class<?>[] domainIncludes() default {};

    /**
     * Relates a property to a class that constitutes (one of) the expected type(s) for values of the property.
     */
    Class<?>[] rangeIncludes() default {};

    /**
     * Relates a property to a property that is its inverse. Inverse properties relate the same pairs of items to each other, but in reversed direction. For
     * example, the 'alumni' and 'alumniOf' properties are inverseOf each other. Some properties don't have explicit inverses; in these situations RDFa and
     * JSON-LD syntax for reverse properties can be used.
     */
    Class<?> inverseOf() default Object.class;

    /**
     * The property that determines that two given properties are equivalent.
     */
    String equivalentProperty() default "";
}
