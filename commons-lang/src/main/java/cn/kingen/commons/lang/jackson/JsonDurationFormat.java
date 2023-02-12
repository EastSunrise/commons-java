package cn.kingen.commons.lang.jackson;

import cn.kingen.commons.lang.util.TimeUtils;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to configure the format of a {@link java.time.Duration}.
 *
 * @author Kingen
 * @see FormattedDurationDeserializer
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonDeserialize(using = FormattedDurationDeserializer.class)
public @interface JsonDurationFormat {

    /**
     * the format of the annotated property
     */
    Format format() default Format.DEFAULT;

    enum Format {

        /**
         * a number as seconds
         */
        NUMBER_AS_SECONDS,
        /**
         * a number as milliseconds
         */
        NUMBER_AS_MILLIS,
        /**
         * the format like '12:44:66'
         *
         * @see TimeUtils#parseDuration(String)
         */
        DURATION,
        /**
         * the default format
         *
         * @see java.time.Duration#parse(CharSequence)
         */
        DEFAULT
    }
}
