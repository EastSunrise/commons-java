package cn.wsg.commons.jackson;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

/**
 * Deserializes '0'/'1' to {@link Boolean}.
 *
 * @author Kingen
 */
public class IntStringBooleanDeserializer extends FromStringDeserializer<Boolean> {

    private static final String FALSE = "0";
    private static final String TRUE = "1";

    public IntStringBooleanDeserializer() {
        super(Boolean.class);
    }

    @Override
    protected Boolean _deserialize(String value, DeserializationContext ctxt) {
        if (FALSE.equals(value)) {
            return false;
        }
        if (TRUE.equals(value)) {
            return true;
        }
        throw new IllegalArgumentException(String.format("Can't parse %s to a bool.", value));
    }
}
