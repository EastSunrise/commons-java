package cn.wsg.commons.internet.view;

import cn.wsg.commons.internet.EntityProperty;

/**
 * Represents a supplier of the identifier of next entity as well as the identifier of previous
 * entity.
 *
 * @author Kingen
 */
@EntityProperty
public interface SiblingSupplier<ID> extends NextSupplier<ID> {

    /**
     * Returns the identifier of the previous entity.
     *
     * @return the previous identifier
     */
    ID getPreviousId();
}
