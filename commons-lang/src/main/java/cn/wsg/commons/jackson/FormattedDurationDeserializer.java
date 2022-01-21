package cn.wsg.commons.jackson;

import cn.wsg.commons.util.TimeUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.datatype.jsr310.DecimalUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * The deserializer that deserializes a token to a {@link Duration} in some formats.
 *
 * @author Kingen
 * @see JsonDurationFormat
 */
public class FormattedDurationDeserializer extends StdScalarDeserializer<Duration> implements ContextualDeserializer {

    private final JsonDurationFormat.Format format;

    protected FormattedDurationDeserializer() {
        this(JsonDurationFormat.Format.DEFAULT);
    }

    protected FormattedDurationDeserializer(JsonDurationFormat.Format format) {
        super(Duration.class);
        this.format = Objects.requireNonNull(format);
    }

    @Override
    public Duration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (JsonDurationFormat.Format.NUMBER_AS_SECONDS == format) {
            if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return Duration.ofSeconds(p.getLongValue());
            }
            if (p.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
                return DecimalUtils.extractSecondsAndNanos(p.getDecimalValue(), Duration::ofSeconds);
            }
        }
        if (JsonDurationFormat.Format.NUMBER_AS_MILLIS == format) {
            if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return Duration.ofMillis(p.getLongValue());
            }
            if (p.hasToken(JsonToken.VALUE_NUMBER_FLOAT)) {
                BigDecimal seconds = p.getDecimalValue().scaleByPowerOfTen(-3);
                return DecimalUtils.extractSecondsAndNanos(seconds, Duration::ofSeconds);
            }
        }
        if (p.hasToken(JsonToken.VALUE_STRING)) {
            String text = p.getText().trim();
            try {
                if (JsonDurationFormat.Format.NUMBER_AS_SECONDS == format) {
                    return DecimalUtils.extractSecondsAndNanos(new BigDecimal(text), Duration::ofSeconds);
                }
                if (JsonDurationFormat.Format.NUMBER_AS_MILLIS == format) {
                    BigDecimal seconds = new BigDecimal(text).scaleByPowerOfTen(-3);
                    return DecimalUtils.extractSecondsAndNanos(seconds, Duration::ofSeconds);
                }
                if (JsonDurationFormat.Format.DURATION == format) {
                    return TimeUtils.parseDuration(text);
                }
                if (JsonDurationFormat.Format.DEFAULT == format) {
                    return Duration.parse(text);
                }
            } catch (NumberFormatException | DateTimeParseException e) {
                return (Duration)ctxt.handleWeirdStringValue(Duration.class, text, e.getMessage());
            }
        }

        String msg = String.format("Cannot deserialize duration of format %s from %s", format, p.currentToken());
        return (Duration)ctxt.handleUnexpectedToken(Duration.class, p.currentToken(), p, msg);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty bp) {
        JsonDurationFormat jdf = bp.getAnnotation(JsonDurationFormat.class);
        if (null == jdf) {
            return new FormattedDurationDeserializer();
        }
        return new FormattedDurationDeserializer(jdf.format());
    }
}
