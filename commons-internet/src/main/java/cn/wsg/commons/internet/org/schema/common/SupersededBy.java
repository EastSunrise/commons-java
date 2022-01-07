package cn.wsg.commons.internet.org.schema.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Relates a term (i.e. a property, class or enumeration) to one that supersedes it.
 *
 * @author Kingen
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface SupersededBy {

    Class<?> value();
}
