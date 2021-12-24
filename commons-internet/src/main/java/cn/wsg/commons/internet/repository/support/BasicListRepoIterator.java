package cn.wsg.commons.internet.repository.support;

import cn.wsg.commons.internet.repository.ListRepoIterator;
import cn.wsg.commons.internet.repository.RepoRetrievable;
import cn.wsg.commons.internet.support.NotFoundException;
import cn.wsg.commons.internet.support.OtherResponseException;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Base implementation of {@link ListRepoIterator}.
 *
 * @author Kingen
 */
class BasicListRepoIterator<ID, T> implements ListRepoIterator<ID, T> {

    private final RepoRetrievable<ID, T> retrievable;

    private final ListIterator<ID> idListIterator;

    BasicListRepoIterator(RepoRetrievable<ID, T> retrievable, ListIterator<ID> idListIterator) {
        this.retrievable = Objects.requireNonNull(retrievable);
        this.idListIterator = Objects.requireNonNull(idListIterator);
    }

    @Override
    public boolean hasNext() {
        return idListIterator.hasNext();
    }

    @Override
    public T next() throws NotFoundException, OtherResponseException {
        if (!hasNext()) {
            throw new NoSuchElementException("Doesn't have next entity.");
        }
        return retrievable.findById(idListIterator.next());
    }

    @Override
    public int nextIndex() {
        return idListIterator.nextIndex();
    }

    @Override
    public boolean hasPrevious() {
        return idListIterator.hasPrevious();
    }

    @Override
    public T previous() throws NotFoundException, OtherResponseException {
        if (!hasPrevious()) {
            throw new NoSuchElementException("Doesn't have previous entity.");
        }
        return retrievable.findById(idListIterator.previous());
    }

    @Override
    public int previousIndex() {
        return idListIterator.previousIndex();
    }
}
