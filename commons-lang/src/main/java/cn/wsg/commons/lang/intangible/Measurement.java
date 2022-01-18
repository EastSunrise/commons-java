package cn.wsg.commons.lang.intangible;

import cn.wsg.commons.lang.AssertUtils;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * The skeletal implementation of quantities of measurement units.
 *
 * @author Kingen
 */
@Getter
public abstract class Measurement<U extends MeasurementUnit>
    implements Quantity, Serializable, Comparable<Measurement<? extends U>> {

    private static final long serialVersionUID = 1L;

    private final double value;

    private final U unit;

    protected Measurement(double value, U unit) {
        this.value = AssertUtils.requireRange(value, 0D, null);
        this.unit = Objects.requireNonNull(unit, "the measurement unit");
    }

    @Override
    public String toString() {
        return value + " " + unit.getSign();
    }

    @Override
    public int compareTo(Measurement<? extends U> o) {
        return Double.compare(value * unit.getRate(), o.value * o.unit.getRate());
    }
}
