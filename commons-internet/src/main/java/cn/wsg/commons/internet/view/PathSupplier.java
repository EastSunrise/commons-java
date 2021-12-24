package cn.wsg.commons.internet.view;

/**
 * Represents a supplier of a path used to build a url.
 *
 * @author Kingen
 */
public interface PathSupplier {

    /**
     * Returns the path.
     *
     * @return the path
     */
    String getAsPath();
}
