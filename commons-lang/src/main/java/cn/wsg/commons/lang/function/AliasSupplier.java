package cn.wsg.commons.lang.function;

/**
 * Represents a supplier of the alias of an object.
 *
 * @author Kingen
 */
@FunctionalInterface
public interface AliasSupplier {

    /**
     * Returns the alias.
     *
     * @return the alias
     */
    String[] getAlias();
}
