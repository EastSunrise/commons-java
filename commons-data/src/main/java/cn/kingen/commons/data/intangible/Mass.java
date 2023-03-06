package cn.kingen.commons.data.intangible;

/**
 * A quantity of mass, e.g. '3kg'.
 *
 * @author Kingen
 */
public class Mass extends Measurement<MassUnit> {

    public Mass(double value, MassUnit unit) {
        super(value, unit);
    }
}
