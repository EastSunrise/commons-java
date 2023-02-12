package cn.kingen.commons.internet.repository.support;

import cn.kingen.commons.internet.repository.LinkedRepoIterator;
import cn.kingen.commons.internet.repository.LinkedRepository;
import cn.kingen.commons.internet.repository.RepoIterator;
import cn.kingen.commons.internet.repository.RepoRetrievable;
import cn.kingen.commons.internet.support.NotFoundException;
import java.util.Objects;
import java.util.function.Function;

/**
 * This class provides a skeletal implementation of {@code LinkedRepository} whose first entity is
 * mapped by {@link #first}, the first identifier. Another field {@link #next} supplies the method
 * to get the next identifier by current entity.
 *
 * @author Kingen
 */
class BasicLinkedRepository<ID, T> implements LinkedRepository<ID, T> {

    private final RepoRetrievable<ID, T> retrievable;

    private final ID first;

    private final Function<T, ID> next;

    BasicLinkedRepository(RepoRetrievable<ID, T> retrievable, ID first, Function<T, ID> next) {
        this.retrievable = Objects.requireNonNull(retrievable);
        this.first = first;
        this.next = Objects.requireNonNull(next);
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public ID firstIdentifier() {
        return first;
    }

    @Override
    public RepoIterator<T> repoIterator() {
        return new BasicLinkedRepoIterator<>(this, next, first);
    }

    @Override
    public LinkedRepoIterator<ID, T> linkedRepoIterator() {
        return new BasicLinkedRepoIterator<>(this, next, first);
    }

    @Override
    public LinkedRepoIterator<ID, T> linkedRepoIterator(ID id) {
        return new BasicLinkedRepoIterator<>(this, next, id);
    }

    @Override
    public T findById(ID id) throws NotFoundException {
        return retrievable.findById(id);
    }
}
