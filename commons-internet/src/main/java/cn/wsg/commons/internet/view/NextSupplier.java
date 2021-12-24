package cn.wsg.commons.internet.view;

import cn.wsg.commons.internet.EntityProperty;

/**
 * Represents a supplier of the identifier of next entity.
 *
 * @author Kingen
 */
@EntityProperty
public interface NextSupplier<ID> {

    /**
     * Returns the identifier of next entity.
     *
     * @return the identifier
     */
    ID getNextId();
}
