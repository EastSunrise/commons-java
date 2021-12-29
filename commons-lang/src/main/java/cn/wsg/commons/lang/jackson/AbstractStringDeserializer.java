package cn.wsg.commons.lang.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/**
 * Deserializes a string to an object of target type.
 *
 * @author Kingen
 */
public abstract class AbstractStringDeserializer<T> extends StdScalarDeserializer<T> {

    private final Class<T> clazz;

    protected AbstractStringDeserializer(Class<T> clazz) {
        super(clazz);
        this.clazz = clazz;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.hasToken(JsonToken.VALUE_NULL)) {
            return null;
        }
        if (p.hasToken(JsonToken.VALUE_STRING)) {
            String text = p.getText();
            if (ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                && StringUtils.isEmpty(text)) {
                return null;
            }
            return valueOfString(p, clazz, text);
        }
        return (T) ctxt.handleUnexpectedToken(String.class, p.currentToken(), p,
            String.format("Unexpected token as a string to be deserialized to %s.", clazz));
    }

    /**
     * Deserializes a non-empty string to an enum object.
     *
     * @param p     parser used for reading JSON content
     * @param clazz the type of target enum
     * @param text  the string to be deserialized
     * @return the enum object
     * @throws JsonParseException when parsing problems are encountered
     */
    protected abstract T valueOfString(JsonParser p, Class<T> clazz, String text)
        throws JsonParseException;
}
