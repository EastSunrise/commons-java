package cn.kingen.commons.internet.repository.support;

import cn.kingen.commons.internet.page.Page;
import cn.kingen.commons.internet.page.PageIndex;
import cn.kingen.commons.internet.repository.RepoPageable;
import cn.kingen.commons.internet.repository.RepoRetrievable;
import cn.kingen.commons.internet.support.NotFoundException;
import cn.kingen.commons.internet.view.SiblingSupplier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Utility for operations of repositories.
 *
 * @author Kingen
 */
public final class RepositoryHelper {

    private RepositoryHelper() {
    }

    /**
     * Collects doubly-linked entities recursively.
     * <p>
     * Notes that the type of {@code ID} must override {@link Object#equals(Object)}, otherwise a
     * stack overflow exception may occurs.
     *
     * @param retrievable the function to retrieve an entity by an identifier
     * @param first       the identifier of the first entity
     * @param <ID>        type of identifiers that should override {@link #equals(Object)}
     * @param <T>         type of entities
     * @return a linked list of entities
     */
    public static <ID, T extends SiblingSupplier<ID>> List<T> collectSiblingEntities(RepoRetrievable<ID, T> retrievable,
        ID first) throws NotFoundException {
        List<T> entities = new LinkedList<>();
        find(first, retrievable, new HashSet<>(), entities);
        return entities;
    }

    /**
     * Traverses the pages in a pageable repository.
     *
     * @param pageable the function to retrieve a page by a given request with pagination
     *                 information
     * @param firstReq the first request with pagination information
     * @param action   action to be performed for the content of each page
     * @param <T>      type of the content of each page
     * @param <P>      type of requests with pagination information
     * @throws NotFoundException if any page is not found
     */
    @SuppressWarnings("unchecked")
    public static <T, P extends PageIndex> void forEachPage(RepoPageable<P, ? extends Page<T>> pageable, P firstReq,
        Consumer<List<T>> action) throws NotFoundException {
        P req = firstReq;
        while (true) {
            Page<T> result = pageable.findPage(req);
            action.accept(result.getContent());
            if (!result.hasNext()) {
                break;
            }
            req = (P)result.nextPageReq();
        }
    }

    /**
     * Traverses the pages in a pageable repository until an index matches the specified predicate.
     * This method requires that the indices found by page are ordered.
     *
     * @param pageable  the function to retrieve a page by a given request with pagination
     *                  information
     * @param firstReq  the first request with pagination information
     * @param action    action to be performed for content of each page
     * @param predicate predicate that ends the traversal
     * @param <T>       type of the content of each page
     * @param <P>       type of requests with pagination information
     * @throws NotFoundException if any page is not found
     */
    @SuppressWarnings("unchecked")
    public static <T, P extends PageIndex> void forEachPageUntil(RepoPageable<P, ? extends Page<T>> pageable,
        P firstReq, Consumer<T> action, Predicate<T> predicate) throws NotFoundException {
        P req = firstReq;
        boolean dead = false;
        while (true) {
            Page<T> page = pageable.findPage(req);
            for (T t : page.getContent()) {
                if (predicate.test(t)) {
                    dead = true;
                    break;
                }
                action.accept(t);
            }
            if (dead || !page.hasNext()) {
                break;
            }
            req = (P)page.nextPageReq();
        }
    }

    /**
     * Collects all indices by traversing the pages in a pageable repository.
     *
     * @param pageable the function to retrieve a page by a given request with pagination
     *                 information
     * @param firstReq the first request with pagination information
     * @param <T>      type of the content of each page
     * @param <P>      type of requests with pagination information
     * @return list of all indices found by pages
     * @throws NotFoundException if any page is not found
     */
    public static <T, P extends PageIndex> List<T> collectPage(RepoPageable<P, ? extends Page<T>> pageable, P firstReq)
        throws NotFoundException {
        List<T> indices = new ArrayList<>();
        forEachPage(pageable, firstReq, indices::addAll);
        return indices;
    }

    private static <ID, T extends SiblingSupplier<ID>> void find(ID id, RepoRetrievable<ID, T> retrievable, Set<ID> ids,
        List<T> entities) throws NotFoundException {
        if (id == null) {
            return;
        }
        if (ids.contains(id)) {
            return;
        }
        T t = retrievable.findById(id);
        entities.add(t);
        ids.add(id);
        find(t.getNextId(), retrievable, ids, entities);
        find(t.getPreviousId(), retrievable, ids, entities);
    }
}
