package cn.wsg.commons.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.function.Function;

/**
 * Provides common serializers to serialize enums.
 *
 * @author Kingen
 */
public final class EnumSerializers {

    private EnumSerializers() {
    }

    /**
     * Serializes an enum object to a string key.
     */
    public static <E extends Enum<E>> JsonSerializer<E> asString(Class<E> eClass, Function<E, String> keyMapper) {
        return new StdSerializer<>(eClass) {
            @Override
            public void serialize(E value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                gen.writeString(keyMapper.apply(value));
            }
        };
    }

    /**
     * Serializes an enum object to a string key.
     */
    public static <E extends Enum<E>> JsonSerializer<E> asInteger(Class<E> eClass, Function<E, Integer> keyMapper) {
        return new StdSerializer<>(eClass) {
            @Override
            public void serialize(E value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                gen.writeNumber(keyMapper.apply(value));
            }
        };
    }
}
