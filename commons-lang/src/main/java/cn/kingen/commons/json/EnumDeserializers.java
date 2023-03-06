package cn.kingen.commons.json;

import cn.kingen.commons.lang.CodeSupplier;
import cn.kingen.commons.util.EnumUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Provides common deserializers for Java {@code Enum}s.
 *
 * @author Kingen
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnumDeserializers {

    public static <E extends Enum<E> & CodeSupplier> CodeEnumDeserializer<E> ofCode(Class<E> clazz) {
        return new CodeEnumDeserializer<>(clazz);
    }

    public static class CodeEnumDeserializer<E extends Enum<E> & CodeSupplier> extends StdScalarDeserializer<E> {

        protected CodeEnumDeserializer(Class<E> t) {
            super(t);
        }

        @Override
        @SuppressWarnings("unchecked")
        public E deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (p.hasToken(JsonToken.VALUE_NULL)) {
                return null;
            }
            Integer code = null;
            if (p.hasToken(JsonToken.VALUE_STRING)) {
                try {
                    code = Integer.parseInt(p.getText());
                } catch (NumberFormatException ignored) {
                }
            } else if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                code = p.getValueAsInt();
            }
            if (code == null) {
                return (E) ctxt.handleUnexpectedToken(getValueType(), p);
            }
            try {
                return EnumUtils.ofCode((Class<E>) handledType(), code);
            } catch (IllegalArgumentException e) {
                return (E) ctxt.handleWeirdNumberValue(handledType(), code, e.getMessage());
            }
        }

        @Override
        public LogicalType logicalType() {
            return LogicalType.Enum;
        }
    }
}
