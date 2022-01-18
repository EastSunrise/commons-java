package cn.wsg.commons.lang.intangible;

/**
 * A quantity of mass, e.g. '1 kg'.
 *
 * @author Kingen
 */
public class Mass extends Measurement<MassUnit> {

    public Mass(double value, MassUnit unit) {
        super(value, unit);
    }
}
