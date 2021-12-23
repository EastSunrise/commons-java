package cn.wsg.commons.lang.jackson;

import cn.wsg.commons.lang.function.CodeSupplier;
import cn.wsg.commons.lang.function.IntCodeSupplier;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 * Provides common serializers to serialize enums.
 *
 * @author Kingen
 */
public final class EnumSerializers {

    private EnumSerializers() {
    }

    public static <E extends Enum<E> & CodeSupplier> JsonSerializer<E> ofCode(Class<E> eClass) {
        return new StdSerializer<>(eClass) {
            @Override
            public void serialize(E value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
                gen.writeString(value.getCode());
            }
        };
    }

    public static <E extends Enum<E> & IntCodeSupplier>
    JsonSerializer<E> ofIntCode(Class<E> eClass) {
        return new StdSerializer<>(eClass) {
            @Override
            public void serialize(E value, JsonGenerator gen, SerializerProvider provider)
                throws IOException {
                gen.writeNumber(value.getIntCode());
            }
        };
    }
}
