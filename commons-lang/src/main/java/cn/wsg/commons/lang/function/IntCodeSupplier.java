package cn.wsg.commons.lang.function;

/**
 * Represents a supplier of an integer code.
 *
 * @author Kingen
 */
@FunctionalInterface
public interface IntCodeSupplier {

    /**
     * Returns the integer code.
     *
     * @return the integer code
     */
    int getIntCode();
}
