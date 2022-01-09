package cn.wsg.commons.internet.util;

/**
 * Extends mappings for enum constants.
 * <p>
 * todo use {@link com.fasterxml.jackson.databind.util.EnumResolver}
 *
 * @author Kingen
 */
public interface EnumMapping<E extends Enum<E>> {

    /**
     * Returns the corresponding enum object.
     *
     * @return the corresponding enum object
     */
    E getEnum();

    /**
     * Returns whether the enum object matches the value.
     *
     * @param value the value to be checked
     * @return {@code true} if matched
     */
    boolean match(String value);
}
