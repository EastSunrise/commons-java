package cn.kingen.commons.convert;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;

/**
 * Deserialize an empty string as {@code null}.
 *
 * @author Kingen
 **/
public class EmptyStringAsNullDeserializer extends StdScalarDeserializer<String> {

    public static final EmptyStringAsNullDeserializer INSTANCE = new EmptyStringAsNullDeserializer();

    protected EmptyStringAsNullDeserializer() {
        super(String.class);
    }

    @Override
    public LogicalType logicalType() {
        return LogicalType.Textual;
    }

    @Override
    public boolean isCachable() {
        return true;
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) {
        return null;
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.hasToken(JsonToken.VALUE_STRING)) {
            String text = p.getText();
            return text.isEmpty() ? null : text;
        }
        if (p.hasToken(JsonToken.START_ARRAY)) {
            return _deserializeFromArray(p, ctxt);
        }
        return _parseString(p, ctxt, this);
    }

    @Override
    public String deserializeWithType(JsonParser p, DeserializationContext ctxt,
        TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }
}
