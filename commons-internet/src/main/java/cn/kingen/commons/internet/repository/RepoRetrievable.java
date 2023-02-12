package cn.kingen.commons.internet.repository;

import cn.kingen.commons.internet.support.NotFoundException;

/**
 * Indicates that the repository is retrievable. The core function is to retrieve an entity by a
 * given identifier.
 *
 * @param <ID> the type of identifiers of entities
 * @param <T>  the type of entities in the repository
 * @author Kingen
 */
public interface RepoRetrievable<ID, T> {

    /**
     * Retrieves an entity by its identifier.
     *
     * @param id must not be {@literal null}
     * @return the entity
     * @throws NullPointerException if the specified identifier is null
     * @throws NotFoundException    if the entity of the specified identifier is not found
     */
    T findById(ID id) throws NotFoundException;
}
