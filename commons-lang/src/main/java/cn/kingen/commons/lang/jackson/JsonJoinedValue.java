package cn.kingen.commons.lang.jackson;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate that the annotated property is deserialized from a joined string.
 *
 * @author Kingen
 * @see JoinedValueDeserializer
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonDeserialize(using = JoinedValueDeserializer.class)
public @interface JsonJoinedValue {

    /**
     * separator which deserialized string is joined with.
     */
    String separator() default JoinedValueDeserializer.DEFAULT_SEPARATOR;
}
