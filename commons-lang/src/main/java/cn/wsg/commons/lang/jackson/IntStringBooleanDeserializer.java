package cn.wsg.commons.lang.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

/**
 * Deserializes '0'/'1' to {@link Boolean}.
 *
 * @author Kingen
 */
public class IntStringBooleanDeserializer extends AbstractStringDeserializer<Boolean> {

    private static final String FALSE = "0";
    private static final String TRUE = "1";

    protected IntStringBooleanDeserializer() {
        super(Boolean.class);
    }

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String text = p.getText().trim();
            if (FALSE.equals(text)) {
                return false;
            }
            if (TRUE.equals(text)) {
                return true;
            }
        }
        return (Boolean) ctxt.handleUnexpectedToken(getValueType(), p);
    }

    @Override
    protected Boolean valueOfString(JsonParser p, Class<Boolean> clazz, String text)
        throws JsonParseException {
        text = text.trim();
        if (FALSE.equals(text)) {
            return false;
        }
        if (TRUE.equals(text)) {
            return true;
        }
        throw new JsonParseException(p, String.format("Can't parse %s to a bool.", text));
    }
}
