package cn.kingen.commons.lang.function;

/**
 * Indicates that an object is displayable in Chinese and English.
 *
 * @author Kingen
 */
public interface BilingualDisplayable {

    /**
     * Returns the Chinese name of the object.
     *
     * @return the Chinese name
     */
    String getZhName();

    /**
     * Returns the English name of the object.
     *
     * @return the English name
     */
    String getEnName();
}
