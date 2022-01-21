package cn.wsg.commons.jackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Deserialize a joined string into a list of objects.
 *
 * @author Kingen
 * @see JsonJoinedValue
 */
public class JoinedValueDeserializer extends FromStringDeserializer<List<?>> implements ContextualDeserializer {

    static final String DEFAULT_SEPARATOR = ",";

    private final String separator;
    private final JavaType targetType;

    protected JoinedValueDeserializer(JavaType targetType) {
        this(DEFAULT_SEPARATOR, targetType);
    }

    protected JoinedValueDeserializer(String separator, JavaType targetType) {
        super(List.class);
        this.separator = separator;
        this.targetType = targetType;
    }

    @Override
    protected List<?> _deserialize(String text, DeserializationContext ctxt) {
        String[] values = StringUtils.splitByWholeSeparator(text, separator);
        ObjectMapper mapper = (ObjectMapper)ctxt.getParser().getCodec();
        List<?> collection = new ArrayList<>(values.length);
        for (String value : values) {
            collection.add(mapper.convertValue(value.strip(), targetType.getContentType()));
        }
        return collection;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty bp) {
        JsonJoinedValue joinedValue = bp.getAnnotation(JsonJoinedValue.class);
        if (null != joinedValue) {
            return new JoinedValueDeserializer(joinedValue.separator(), bp.getType());
        } else {
            return new JoinedValueDeserializer(bp.getType());
        }
    }
}
