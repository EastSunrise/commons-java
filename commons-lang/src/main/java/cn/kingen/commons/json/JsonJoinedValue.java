package cn.kingen.commons.json;

import cn.kingen.commons.lang.Constants;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate that the annotated property is a joined string.
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
     * the delimiter to be used between each element
     */
    String delimiter() default Constants.COMMA;
}
