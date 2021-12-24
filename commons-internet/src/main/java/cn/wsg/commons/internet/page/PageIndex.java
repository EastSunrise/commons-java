package cn.wsg.commons.internet.page;

import java.util.Optional;

/**
 * Interface for pagination information.
 *
 * @author Kingen
 */
public interface PageIndex {

    /**
     * Creates a new page index.
     *
     * @param current zero-based page index, must not be negative.
     * @return the page index
     */
    static PageIndex of(int current) {
        return new PageIndexImpl(current);
    }

    /**
     * Creates the first page index.
     *
     * @return the first page index
     */
    static PageIndex first() {
        return new PageIndexImpl(0);
    }

    /**
     * Returns the specified page index, or the first page index if the specified page index is
     * {@literal null}.
     *
     * @param pageIndex the specified page index to be returned
     * @return the specified page index
     */
    static PageIndex orFirst(PageIndex pageIndex) {
        return Optional.ofNullable(pageIndex).orElse(first());
    }

    /**
     * Returns the page to be returned.
     *
     * @return the page to be returned
     */
    int getCurrent();

    /**
     * Returns the request requesting the next result.
     *
     * @return the next request
     */
    PageIndex next();

    /**
     * Returns the request requesting the previous result.
     *
     * @return the previous request, or the first request if the current one already is the first
     * one
     */
    PageIndex previous();
}
