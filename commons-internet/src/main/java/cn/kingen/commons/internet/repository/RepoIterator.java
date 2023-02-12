package cn.kingen.commons.internet.repository;

import cn.kingen.commons.internet.support.NotFoundException;
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
     */
    T next() throws NotFoundException;
}
