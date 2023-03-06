package cn.kingen.commons.data.intangible;

/**
 * A quantity of length, e.g. '4m'.
 *
 * @author Kingen
 */
public class Length extends Measurement<LengthUnit> {

    public Length(double value, LengthUnit unit) {
        super(value, unit);
    }
}
