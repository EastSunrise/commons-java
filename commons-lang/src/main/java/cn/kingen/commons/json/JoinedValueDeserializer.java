package cn.kingen.commons.json;

import cn.kingen.commons.lang.Constants;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Deserializes elements from a joined string, separated by the specified delimiter.
 *
 * @author Kingen
 * @see JsonJoinedValue
 */
public class JoinedValueDeserializer extends FromStringDeserializer<List<?>> implements ContextualDeserializer {

    private final String delimiter;
    private final JavaType targetType;

    protected JoinedValueDeserializer() {
        this(Constants.COMMA, null);
    }

    protected JoinedValueDeserializer(JavaType targetType) {
        this(Constants.COMMA, targetType);
    }

    protected JoinedValueDeserializer(String delimiter, JavaType targetType) {
        super(List.class);
        this.delimiter = delimiter;
        this.targetType = targetType;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty bp) {
        JsonJoinedValue joinedValue = bp.getAnnotation(JsonJoinedValue.class);
        if (null != joinedValue) {
            return new JoinedValueDeserializer(joinedValue.delimiter(), bp.getType());
        } else {
            return new JoinedValueDeserializer(bp.getType());
        }
    }

    @Override
    protected List<?> _deserialize(String text, DeserializationContext ctxt) {
        String[] values = StringUtils.splitByWholeSeparator(text, delimiter);
        ObjectMapper mapper = (ObjectMapper) ctxt.getParser().getCodec();
        List<?> collection = new ArrayList<>(values.length);
        for (String value : values) {
            collection.add(mapper.convertValue(value.strip(), targetType.getContentType()));
        }
        return collection;
    }
}
