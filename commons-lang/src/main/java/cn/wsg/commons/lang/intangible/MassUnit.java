package cn.wsg.commons.lang.intangible;

import lombok.Getter;

/**
 * Mass units.
 *
 * @author Kingen
 */
@Getter
public enum MassUnit implements MeasurementUnit {

    KILOGRAM(UnitSystem.INTERNATIONAL, 1, "kg"),
    GRAM(UnitSystem.INTERNATIONAL, 10E-3, "g"),
    MILLIGRAM(UnitSystem.INTERNATIONAL, 10E-6, "mg"),
    ;

    private final UnitSystem system;
    private final double rate;
    private final String sign;

    MassUnit(UnitSystem system, double rate, String sign) {
        this.system = system;
        this.rate = rate;
        this.sign = sign;
    }
}
