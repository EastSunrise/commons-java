package cn.wsg.commons.web;

/**
 * Indicates that an object is displayable in Chinese and English.
 *
 * @author Kingen
 */
public interface BilingualDisplayable {

    /**
     * Returns the Chinese display name of the object.
     *
     * @return the Chinese display name
     */
    String getZhDisplayName();

    /**
     * Returns the English display name of the object.
     *
     * @return the English display name
     */
    String getEnDisplayName();
}
