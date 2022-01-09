package cn.wsg.commons.lang.jackson;

import cn.wsg.commons.lang.EnumUtilExt;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

import java.util.function.BiPredicate;

/**
 * Provides common deserializers to deserialize enums.
 *
 * @author Kingen
 */
public final class EnumDeserializers {

    private EnumDeserializers() {
    }

    /**
     * Returns an instance of {@link JsonDeserializer} that resolves a string key into the corresponding enum object.
     *
     * @see EnumUtilExt#valueOf(Class, Object, BiPredicate)
     */
    public static <E extends Enum<E>> JsonDeserializer<E> match(Class<E> eClass, BiPredicate<String, E> predicate) {
        return new FromStringDeserializer<>(eClass) {
            @Override
            protected E _deserialize(String value, DeserializationContext ctxt) {
                return EnumUtilExt.valueOf(eClass, value, predicate);
            }
        };
    }
}
