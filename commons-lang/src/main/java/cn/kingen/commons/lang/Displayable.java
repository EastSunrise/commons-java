package cn.kingen.commons.lang;

/**
 * Indicates that an object is displayable such as on Web pages.
 *
 * @author Kingen
 */
@FunctionalInterface
public interface Displayable {

    /**
     * Returns the display name of the object.
     *
     * @return the display name
     */
    String getDisplayName();
}
