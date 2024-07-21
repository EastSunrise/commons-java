package cn.kingen.commons.convert;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

/**
 * Deserialize a string with all leading and trailing whitespaces stripped. If the string is empty, return
 * {@code null}.
 *
 * @author Kingen
 **/
public class StrippedStringDeserializer extends FromStringDeserializer<String> {

    protected StrippedStringDeserializer() {
        super(String.class);
    }

    @Override
    protected String _deserialize(String value, DeserializationContext ctxt) {
        return value;
    }
}
