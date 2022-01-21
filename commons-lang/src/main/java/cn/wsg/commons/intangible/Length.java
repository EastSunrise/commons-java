package cn.wsg.commons.intangible;

/**
 * A quantity of length, e.g. '1 m'.
 *
 * @author Kingen
 */
public class Length extends Measurement<LengthUnit> {

    public Length(double value, LengthUnit unit) {
        super(value, unit);
    }
}
