package cn.wsg.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import org.apache.commons.lang3.math.Fraction;

/**
 * The deserializer that deserializes a string to a {@link Fraction} as a number or a ratio.
 *
 * @author Kingen
 */
public class FractionDeserializer extends FromStringDeserializer<Fraction> {

    public static final int RATIO_SEPARATOR = ':';
    private static final String ZERO = "0/0";

    public FractionDeserializer() {
        super(Fraction.class);
    }

    @Override
    protected Fraction _deserialize(String value, DeserializationContext ctxt) {
        if (ZERO.equals(value)) {
            return Fraction.ZERO;
        }
        int pos = value.indexOf(RATIO_SEPARATOR);
        if (0 <= pos) {
            int numerator = Integer.parseInt(value.substring(0, pos));
            int denominator = Integer.parseInt(value.substring(pos + 1));
            return Fraction.getFraction(numerator, denominator);
        }
        return Fraction.getFraction(value);
    }
}
