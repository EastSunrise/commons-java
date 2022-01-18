package cn.wsg.commons.lang.intangible;

import lombok.Getter;

/**
 * Length units.
 *
 * @author Kingen
 */
@Getter
public enum LengthUnit implements MeasurementUnit {

    KILOMETER(UnitSystem.INTERNATIONAL, 1000, "km"),
    METER(UnitSystem.INTERNATIONAL, 1, "m"),
    DECIMETER(UnitSystem.INTERNATIONAL, 10E-1, "dm"),
    CENTIMETER(UnitSystem.INTERNATIONAL, 10E-2, "cm"),
    MILLIMETER(UnitSystem.INTERNATIONAL, 10E-3, "mm"),
    MICROMETER(UnitSystem.INTERNATIONAL, 10E-6, "μm"),
    ;

    private final UnitSystem system;
    private final double rate;
    private final String sign;

    LengthUnit(UnitSystem system, double rate, String sign) {
        this.system = system;
        this.rate = rate;
        this.sign = sign;
    }
}
