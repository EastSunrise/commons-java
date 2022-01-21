package cn.wsg.commons.intangible;

/**
 * A group of measurement units, e.g. 'kg', 'g', 'mg', ...
 *
 * @author Kingen
 */
public interface MeasurementUnit {

    /**
     * Returns the system to which the unit belongs.
     *
     * @return the system
     */
    UnitSystem getSystem();

    /**
     * Returns the rate when the unit is converted to the basic unit. e.g. the rate from 'g' to 'kg' is 0.001.
     *
     * @return the rate
     */
    double getRate();

    /**
     * Returns the sign of the unit.
     *
     * @return the sign
     */
    String getSign();
}
