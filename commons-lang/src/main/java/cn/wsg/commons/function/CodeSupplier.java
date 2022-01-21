package cn.wsg.commons.function;

/**
 * Represents a supplier of a string code.
 *
 * @author Kingen
 */
@FunctionalInterface
public interface CodeSupplier {

    /**
     * Returns the code.
     *
     * @return the code
     */
    String getCode();
}
