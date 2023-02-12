package cn.kingen.commons.internet.repository.support;

import cn.kingen.commons.internet.repository.LinkedRepoIterator;
import cn.kingen.commons.internet.repository.RepoRetrievable;
import cn.kingen.commons.internet.support.NotFoundException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

/**
 * Base implementation of {@link LinkedRepoIterator}.
 *
 * @author Kingen
 */
class BasicLinkedRepoIterator<ID, T> implements LinkedRepoIterator<ID, T> {

    private final RepoRetrievable<ID, T> retrievable;

    private final Function<T, ID> next;

    private ID cursor;

    BasicLinkedRepoIterator(RepoRetrievable<ID, T> retrievable, Function<T, ID> next, ID first) {
        this.retrievable = Objects.requireNonNull(retrievable);
        this.next = Objects.requireNonNull(next);
        this.cursor = first;
    }

    @Override
    public boolean hasNext() {
        return cursor != null;
    }

    @Override
    public ID nextIdentifier() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more identifier.");
        }
        return cursor;
    }

    @Override
    public T next() throws NotFoundException {
        if (!hasNext()) {
            throw new NoSuchElementException("No more entity.");
        }
        T t = retrievable.findById(cursor);
        try {
            cursor = next.apply(t);
        } catch (NoSuchElementException e) {
            cursor = null;
        }
        return t;
    }
}
