package cn.wsg.commons.internet.repository.support;

import cn.wsg.commons.internet.repository.LinkedRepository;
import cn.wsg.commons.internet.repository.ListRepository;
import cn.wsg.commons.internet.repository.RepoRetrievable;
import cn.wsg.commons.internet.view.NextSupplier;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Common repositories.
 *
 * @author Kingen
 */
public final class Repositories {

    private Repositories() {
    }

    /**
     * Constructs a linked repository starting with the given first identifier.
     *
     * @param retrievable the core function of the repository to retrieve an entity by an
     *                    identifier, must not be null
     * @param first       the first identifier in the repository, may be null if the repository is
     *                    empty
     */
    public static <ID, T extends NextSupplier<ID>> LinkedRepository<ID, T> linked(RepoRetrievable<ID, T> retrievable, ID first) {
        Objects.requireNonNull(retrievable);
        return new BasicLinkedRepository<>(retrievable, first, NextSupplier::getNextId);
    }

    /**
     * Constructs a linked repository starting with the given first identifier.
     *
     * @param retrievable the core function of the repository to retrieve an entity by an
     *                    identifier, must not be null
     * @param getNext     the function to get next identifier by current entity, must not be null
     * @param first       the first identifier in the repository, may be null if the repository is
     *                    empty
     */
    public static <ID, T> LinkedRepository<ID, T> linked(RepoRetrievable<ID, T> retrievable, ID first, Function<T, ID> getNext) {
        Objects.requireNonNull(retrievable);
        Objects.requireNonNull(getNext);
        return new BasicLinkedRepository<>(retrievable, first, getNext);
    }

    /**
     * Constructs a list repository with a given list of identifiers.
     *
     * @param retrievable the core function of the repository to retrieve an entity by an
     *                    identifier, must not be null
     * @param ids         the list of all identifiers, must not be null but may empty
     */
    public static <ID, T> ListRepository<ID, T> list(RepoRetrievable<ID, T> retrievable, List<ID> ids) {
        Objects.requireNonNull(retrievable);
        Objects.requireNonNull(ids);
        return new BasicListRepository<>(retrievable, ids);
    }
}
