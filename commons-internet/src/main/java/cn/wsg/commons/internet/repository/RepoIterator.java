package cn.wsg.commons.internet.repository;

import cn.wsg.commons.internet.support.NotFoundException;
import cn.wsg.commons.internet.support.OtherResponseException;

import java.util.NoSuchElementException;

/**
 * A variant of {@code Iterator} over entities of a repository.
 *
 * @param <T> the type of entities returned by this iterator
 * @author Kingen
 * @see RepoIterable
 */
public interface RepoIterator<T> {

    /**
     * Returns {@code true} if the repository has more entities.
     *
     * @return {@code true} if the iteration has more entities
     */
    boolean hasNext();

    /**
     * Retrieves the next entity in the repository and moves the cursor position.
     *
     * @return the next entity
     * @throws NoSuchElementException if the iteration has no next entity
     * @throws NotFoundException      if the next entity is not found
     * @throws OtherResponseException if an unexpected error occurs when requesting
     */
    T next() throws NotFoundException, OtherResponseException;
}
