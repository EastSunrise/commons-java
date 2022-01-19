package cn.wsg.commons.internet.repository;

import cn.wsg.commons.internet.support.NotFoundException;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * An iterator for a list repository that allows to traverse the repository in either direction and
 * obtain the iterator's current position in the repository.
 *
 * @author Kingen
 * @see RepoIterator
 * @see ListRepository
 * @see ListRepository#listRepoIterator
 */
public interface ListRepoIterator<ID, T> extends RepoIterator<T> {

    /**
     * Returns {@code true} if the repository has more identifiers and entities when traversing the
     * repository.
     *
     * @return {@code true} if the iteration has more identifiers and entities
     */
    @Override
    boolean hasNext();

    /**
     * Returns the next identifier in the repository and advances the cursor position.
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

    /**
     * Returns the index of the identifier that would be returned by a subsequent call to {@link
     * #next}. (Returns repository size if the repository iterator is at the end of the
     * repository.)
     *
     * @return the index of the identifier that would be returned by a subsequent call to {@code
     * next}, or repository size if the repository iterator is at the end of the repository
     * @see ListIterator#nextIndex()
     */
    int nextIndex();

    /**
     * Returns {@code true} if the repository has more identifiers and entities when traversing the
     * repository in the reverse direction.
     *
     * @return {@code true} if the iteration has more identifiers and entities in the reverse
     * direction.
     */
    boolean hasPrevious();

    /**
     * Returns the previous identifier in the repository and moves the cursor position backwards.
     * <p>
     * Notes that the cursor won't be moved.
     *
     * @return the previous identifier
     * @throws NoSuchElementException if the repository has no previous identifier
     */
    ID previousIdentifier();

    /**
     * Retrieves the previous entity in the repository and moves the cursor position backwards.
     *
     * @return the previous entity
     * @throws NoSuchElementException if the repository has no previous entity
     * @throws NotFoundException      if the previous entity is not found
     */
    T previous() throws NotFoundException;

    /**
     * Returns the index of the identifier that would be returned by a subsequent call to {@link
     * #previous}. (Returns -1 if the repository iterator is at the beginning of the repository.)
     *
     * @return the index of the identifier that would be returned by a subsequent call to {@code
     * previous}, or -1 if the repository iterator is at the beginning of the repository
     * @see ListIterator#previousIndex()
     */
    int previousIndex();
}
