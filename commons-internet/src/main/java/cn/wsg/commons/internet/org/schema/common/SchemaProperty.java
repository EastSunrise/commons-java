package cn.wsg.commons.internet.org.schema.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated type is a property on schema.org.
 *
 * @author Kingen
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface SchemaProperty {

    /**
     * The name of property. It's same as the name of the field if not set.
     */
    String value() default "";
}
