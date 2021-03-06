package cn.wsg.commons.internet.repository;

import cn.wsg.commons.internet.support.NotFoundException;

import java.util.NoSuchElementException;

/**
 * An iterator for a singly-linked repository that allows to traverse the repository in one
 * directions.
 *
 * @param <ID> type of identifiers managed by the iterator
 * @author Kingen
 * @see RepoIterator
 * @see LinkedRepository
 * @see LinkedRepository#linkedRepoIterator
 */
public interface LinkedRepoIterator<ID, T> extends RepoIterator<T> {

    /**
     * Returns {@code true} if the repository has more identifiers and entities when traversing the
     * repository.
     *
     * @return {@code true} if the iteration has more identifiers and entities
     */
    @Override
    boolean hasNext();

    /**
     * Returns the next identifier in the repository.
     * <p>
     * Notes that the cursor won't be moved.
     *
     * @return the next identifier
     * @throws NoSuchElementException if the repository has no next identifier
     */
    ID nextIdentifier();

    /**
     * Retrieves the next entity in the repository and advances the cursor position.
     *
     * @return the next entity
     * @throws NoSuchElementException if the repository has no next entity
     * @throws NotFoundException      if the next entity is not found
     */
    @Override
    T next() throws NotFoundException;
}
