package cn.kingen.commons.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Provides common serializers for Java {@code Enum}s.
 *
 * @author Kingen
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnumSerializers {

    /**
     * Serializes an enum object to a string key.
     */
    public static <E extends Enum<E>> JsonSerializer<E> asString(Class<E> clazz, Function<E, String> keyMapper) {
        return new StdSerializer<>(clazz) {
            @Override
            public void serialize(E value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                gen.writeString(keyMapper.apply(value));
            }
        };
    }

    /**
     * Serializes an enum object to an integer key.
     */
    public static <E extends Enum<E>> JsonSerializer<E> asInteger(Class<E> clazz, Function<E, Integer> keyMapper) {
        return new StdSerializer<>(clazz) {
            @Override
            public void serialize(E value, JsonGenerator gen, SerializerProvider provider) throws IOException {
                gen.writeNumber(keyMapper.apply(value));
            }
        };
    }
}
