package cn.wsg.commons.internet.repository;

import cn.wsg.commons.internet.page.Page;
import cn.wsg.commons.internet.page.PageIndex;
import cn.wsg.commons.internet.support.NotFoundException;
import cn.wsg.commons.internet.support.OtherResponseException;

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
     * @throws NullPointerException   if the specified request is null
     * @throws NotFoundException      if the page of the specified request is not found
     * @throws OtherResponseException if an unexpected error occurs when requesting
     */
    R findPage(P req) throws NotFoundException, OtherResponseException;
}
