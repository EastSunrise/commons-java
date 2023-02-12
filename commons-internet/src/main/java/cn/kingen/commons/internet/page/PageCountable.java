package cn.kingen.commons.internet.page;

/**
 * Indicates that the number of total pages is available.
 *
 * @author Kingen
 */
public interface PageCountable {

    /**
     * Returns the number of total pages.
     *
     * @return the number of total pages
     */
    int getTotalPages();
}
