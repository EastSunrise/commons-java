package cn.wsg.commons.internet.view;

import cn.wsg.commons.internet.EntityProperty;

/**
 * Represents a supplier of the subtype to which the item belongs in the repository.
 *
 * @author Kingen
 */
@EntityProperty
public interface SubtypeSupplier<E extends Enum<E>> {

    /**
     * Returns the subtype to which the item belongs.
     *
     * @return the subtype
     */
    E getSubtype();
}
