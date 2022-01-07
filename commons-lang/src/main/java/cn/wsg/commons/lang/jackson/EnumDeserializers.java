package cn.wsg.commons.lang.jackson;

import cn.wsg.commons.lang.EnumUtilExt;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import java.io.IOException;
import java.util.function.Function;

/**
 * Provides common deserializers to deserialize enums.
 *
 * @author Kingen
 */
public final class EnumDeserializers {

    private EnumDeserializers() {
    }

    public static <E extends Enum<E>> JsonDeserializer<E> ignoreCase(Class<E> eClass) {
        return new AbstractStringDeserializer<>(eClass) {
            @Override
            protected E valueOfString(JsonParser p, Class<E> clazz, String text) {
                return EnumUtilExt.valueOfIgnoreCase(clazz, text);
            }
        };
    }

    /**
     * Deserializes a string key to the corresponding enum object.
     *
     * @param keyMapper the key must be unique for each enum
     */
    public static <E extends Enum<E>> JsonDeserializer<E> ofStringKey(Class<E> eClass, Function<E, String> keyMapper) {
        return new AbstractStringDeserializer<>(eClass) {
            @Override
            protected E valueOfString(JsonParser p, Class<E> clazz, String value) {
                return EnumUtilExt.valueOfKey(clazz, value, keyMapper);
            }
        };
    }

    /**
     * Deserializes an integer key to the corresponding enum object.
     *
     * @param keyMapper the key must be unique for each enum
     */
    public static <E extends Enum<E>> JsonDeserializer<E> ofIntegerKey(Class<E> eClass, Function<E, Integer> keyMapper) {
        return new AbstractIntEnumDeserializer<>(eClass) {
            @Override
            E valueOfInt(Class<E> clazz, int value) {
                return EnumUtilExt.valueOfKey(clazz, value, keyMapper);
            }
        };
    }

    private abstract static class AbstractIntEnumDeserializer<E extends Enum<E>> extends StdScalarDeserializer<E> {

        private final Class<E> clazz;

        protected AbstractIntEnumDeserializer(Class<E> clazz) {
            super(clazz);
            this.clazz = clazz;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (p.hasToken(JsonToken.VALUE_NULL)) {
                return null;
            }
            if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return valueOfInt(clazz, p.getIntValue());
            }
            return (E)ctxt.handleUnexpectedToken(int.class, p.currentToken(), p, "Unexpected token as an integer value to be deserialized to an enum.");
        }

        /**
         * Deserializes an integer value to an enum object.
         *
         * @param clazz the type of target enum
         * @param value the integer value to be deserialized
         * @return the enum object
         */
        abstract E valueOfInt(Class<E> clazz, int value);
    }
}
