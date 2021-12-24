package cn.wsg.commons.internet.view;

import cn.wsg.commons.internet.EntityProperty;

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
