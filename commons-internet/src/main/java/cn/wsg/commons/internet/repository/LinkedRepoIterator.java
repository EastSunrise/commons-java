package cn.wsg.commons.internet.repository;

import cn.wsg.commons.internet.support.NotFoundException;
import cn.wsg.commons.internet.support.OtherResponseException;

import java.util.NoSuchElementException;
import java.util.Optional;

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
     * Note that the cursor won't be moved.
     *
     * @return the next identifier, or {@code Optional#empty()} if there are no more identifiers
     */
    Optional<ID> nextIdentifier();

    /**
     * Retrieves the next entity in the repository and advances the cursor position.
     *
     * @return the next entity
     * @throws NoSuchElementException if the repository has no next entity
     * @throws NotFoundException      if the next entity is not found
     * @throws OtherResponseException if an unexpected error occurs when requesting
     */
    @Override
    T next() throws NotFoundException, OtherResponseException;
}
