package cn.wsg.commons.internet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated type is a property of an entity.
 *
 * @author Kingen
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface EntityProperty {

}
