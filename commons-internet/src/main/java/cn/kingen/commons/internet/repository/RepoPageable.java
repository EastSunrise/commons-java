package cn.kingen.commons.internet.repository;

import cn.kingen.commons.internet.page.Page;
import cn.kingen.commons.internet.page.PageIndex;
import cn.kingen.commons.internet.support.NotFoundException;

/**
 * Indicates that the repository is pageable, including a method to retrieve a paged result of
 * indices by given pagination information.
 *
 * @param <P> type of pagination information
 * @param <R> type of paged result returned by the repository
 * @author Kingen
 */
@FunctionalInterface
public interface RepoPageable<P extends PageIndex, R extends Page<?>> {

    /**
     * Retrieves a paged result of the indices by the given pagination information.
     *
     * @param req the pagination information
     * @return a paged result of the indices
     * @throws NullPointerException if the specified request is null
     * @throws NotFoundException    if the page of the specified request is not found
     */
    R findPage(P req) throws NotFoundException;
}
