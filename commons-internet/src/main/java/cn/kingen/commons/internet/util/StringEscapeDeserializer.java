package cn.kingen.commons.internet.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import java.io.IOException;
import org.apache.commons.text.StringEscapeUtils;

/**
 * @author Kingen
 */
public class StringEscapeDeserializer extends StringDeserializer {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // todo can't unescape '&apos;'
        return StringEscapeUtils.unescapeHtml4(super.deserialize(p, ctxt));
    }
}
