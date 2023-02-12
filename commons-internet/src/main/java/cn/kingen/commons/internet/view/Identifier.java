package cn.kingen.commons.internet.view;

import cn.kingen.commons.internet.EntityProperty;

/**
 * Indicates that the entity has an identifier.
 *
 * @param <ID> type of the identifiers
 * @author Kingen
 */
@EntityProperty
public interface Identifier<ID> {

    /**
     * Returns the identifier
     *
     * @return id
     */
    ID getId();
}
