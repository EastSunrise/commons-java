package cn.kingen.commons.web;

/**
 * Indicates that an object is displayable such as on Web Pages.
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
