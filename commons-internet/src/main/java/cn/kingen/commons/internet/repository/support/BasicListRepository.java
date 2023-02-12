package cn.kingen.commons.internet.repository.support;

import cn.kingen.commons.internet.repository.ListRepoIterator;
import cn.kingen.commons.internet.repository.ListRepository;
import cn.kingen.commons.internet.repository.RepoIterator;
import cn.kingen.commons.internet.repository.RepoRetrievable;
import cn.kingen.commons.internet.support.NotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * This class provides a skeletal implementation of {@code ListRepository} which manages an
 * unmodifiable list of identifiers that can be mapped to the entities.
 *
 * @author Kingen
 */
class BasicListRepository<ID, T> implements ListRepository<ID, T> {

    private final RepoRetrievable<ID, T> retrievable;

    private final List<ID> identifiers;

    BasicListRepository(RepoRetrievable<ID, T> retrievable, List<ID> identifiers) {
        this.retrievable = Objects.requireNonNull(retrievable);
        this.identifiers = Collections.unmodifiableList(Objects.requireNonNull(identifiers));
    }

    @Override
    public boolean isEmpty() {
        return identifiers.isEmpty();
    }

    @Override
    public int size() {
        return identifiers.size();
    }

    @Override
    public boolean containsIdentifier(ID id) {
        return identifiers.contains(id);
    }

    @Override
    public RepoIterator<T> repoIterator() {
        return new BasicListRepoIterator<>(this, identifiers.listIterator());
    }

    @Override
    public ID getIdentifier(int index) {
        return identifiers.get(index);
    }

    @Override
    public int indexOf(ID id) {
        return identifiers.indexOf(id);
    }

    @Override
    public int lastIndexOf(ID id) {
        return identifiers.lastIndexOf(id);
    }

    @Override
    public ListRepoIterator<ID, T> listRepoIterator() {
        return new BasicListRepoIterator<>(this, identifiers.listIterator());
    }

    @Override
    public ListRepoIterator<ID, T> listRepoIterator(int index) {
        return new BasicListRepoIterator<>(this, identifiers.listIterator(index));
    }

    @Override
    public ListRepository<ID, T> subRepository(int fromInclusive, int toExclusive) {
        List<ID> subList = identifiers.subList(fromInclusive, toExclusive);
        return new BasicListRepository<>(retrievable, subList);
    }

    @Override
    public List<ID> indices() {
        return identifiers;
    }

    @Override
    public List<T> entities() throws NotFoundException {
        List<T> entities = new ArrayList<>(size());
        for (ID identifier : identifiers) {
            T byId = findById(identifier);
            entities.add(byId);
        }
        return entities;
    }

    @Override
    public T findById(ID id) throws NotFoundException {
        return retrievable.findById(id);
    }
}
