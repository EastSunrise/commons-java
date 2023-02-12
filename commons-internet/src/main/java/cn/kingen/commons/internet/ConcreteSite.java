package cn.kingen.commons.internet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the annotated type is a site. This annotation should be used on a concrete
 * implementation of sites.
 *
 * @author Kingen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConcreteSite {

    /**
     * Marks the status of annotated site, normal by default
     */
    SiteStatus status() default SiteStatus.NORMAL;

    /**
     * Indicates the status of a site.
     */
    enum SiteStatus {
        /**
         * If the site is normal to access
         */
        NORMAL,
        /**
         * If the site is blocked by GFW.
         */
        BLOCKED,
        /**
         * If the site is accessible but part of resources is restricted by security verification or
         * else.
         */
        RESTRICTED,
        /**
         * If the site is invalid.
         */
        INVALID
    }
}
