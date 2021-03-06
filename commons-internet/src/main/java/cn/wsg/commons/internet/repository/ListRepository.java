package cn.wsg.commons.internet.repository;

import cn.wsg.commons.internet.support.NotFoundException;

import java.util.List;

/**
 * A repository that manages an <strong>unmodifiable</strong> list of identifiers mapped to the
 * entities.
 *
 * @author Kingen
 * @see ListRepoIterator
 */
public interface ListRepository<ID, T> extends Repository<ID, T>, RepoRetrievable<ID, T>, RepoIterable<T> {

    /**
     * Returns {@code true} if this repository contains no identifiers.
     *
     * @return {@code true} if this repository contains no identifiers
     */
    boolean isEmpty();

    /**
     * Returns the number of identifiers in this repository.
     *
     * @return the number of identifiers
     */
    int size();

    /**
     * Returns {@code true} if this repository contains the specified identifier.
     *
     * @param id the identifier whose presence in this repository is to be tested
     * @return {@code true} if this repository contains the specified identifier
     * @throws NullPointerException if the specified identifier is null
     */
    boolean containsIdentifier(ID id);

    /**
     * Returns an iterator over the entities in this repository in proper sequence.
     *
     * @return an iterator in proper sequence
     */
    @Override
    RepoIterator<T> repoIterator();

    /**
     * Returns the identifier at the specified position in this repository.
     *
     * @param index index of the identifier to return
     * @return the identifier at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >=
     *                                   size()})
     * @see List#get(int)
     */
    ID getIdentifier(int index);

    /**
     * Retrieves the entity at the specified position in this repository.
     *
     * @param index index of the entity to retrieve
     * @return the entity at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >=
     *                                   size()})
     * @throws NotFoundException         if the entity at the specified position is not found
     */
    default T get(int index) throws NotFoundException {
        return findById(getIdentifier(index));
    }

    /**
     * Returns the index of the first occurrence of the specified identifier in this repository, or
     * -1 if this repository does not contain the identifier.
     *
     * @param id identifier to search for
     * @return the index of the first occurrence of the specified element, or -1 if this repository
     * does not contain the identifier
     * @throws NullPointerException if the specified identifier is null
     * @see List#indexOf(Object)
     */
    int indexOf(ID id);

    /**
     * Returns the index of the last occurrence of the specified identifier in this repository, or
     * -1 if this repository does not contain the identifier.
     *
     * @param id identifier to search for
     * @return the index of the last occurrence of the specified identifier, or -1 if this
     * repository does not contain the identifier
     * @throws NullPointerException if the specified identifier is null
     * @see List#lastIndexOf(Object)
     */
    int lastIndexOf(ID id);

    /**
     * Returns a list iterator over the identifiers and entities in this repository (in proper
     * sequence).
     *
     * @return a list iteration in proper sequence
     * @see List#listIterator()
     */
    ListRepoIterator<ID, T> listRepoIterator();

    /**
     * Returns a list iterator over the identifiers and entities in this repository (in proper
     * sequence), starting at the specified position in the repository.
     *
     * @param index index of the first entity to be returned from the repository iterator (by a call
     *              to {@link ListRepoIterator#next})
     * @return a list iteration in proper sequence
     * @throws IndexOutOfBoundsException if the index is out of range ({@code index < 0 || index >
     *                                   size()})
     * @see List#listIterator(int)
     */
    ListRepoIterator<ID, T> listRepoIterator(int index);

    /**
     * Returns a view of the portion of this repository between the specified {@code fromInclusive},
     * inclusive, and {@code toExclusive}, exclusive. (If {@code fromInclusive} and {@code
     * toExclusive} are equal, the returned repository is empty.)
     *
     * @param fromInclusive low endpoint (inclusive) of the subRepository
     * @param toExclusive   high endpoint (exclusive) of the subRepository
     * @return a view of the specified range within this repository
     * @throws IndexOutOfBoundsException for an illegal endpoint index value ({@code fromIndex < 0
     *                                   || toIndex > size || fromIndex > toIndex})
     */
    ListRepository<ID, T> subRepository(int fromInclusive, int toExclusive);

    /**
     * Returns an unmodifiable list of indices contained in this repository.
     *
     * @return an unmodifiable list of indices
     */
    List<ID> indices();

    /**
     * Retrieves the list of all entities contained in this repository.
     *
     * @return the list of all entities
     * @throws NotFoundException if any entity is not found
     */
    List<T> entities() throws NotFoundException;
}
