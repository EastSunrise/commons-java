package cn.kingen.commons.lang.jackson;

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
 * Deserialize a joined string into a list of objects.
 *
 * @author Kingen
 * @see JsonJoinedValue
 */
public class JoinedValueDeserializer extends FromStringDeserializer<List<?>> implements ContextualDeserializer {

    static final String DEFAULT_SEPARATOR = ",";

    private final String separator;
    private final JavaType targetType;

    protected JoinedValueDeserializer() {
        this(DEFAULT_SEPARATOR, null);
    }

    protected JoinedValueDeserializer(JavaType targetType) {
        this(DEFAULT_SEPARATOR, targetType);
    }

    protected JoinedValueDeserializer(String separator, JavaType targetType) {
        super(List.class);
        this.separator = separator;
        this.targetType = targetType;
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
}
