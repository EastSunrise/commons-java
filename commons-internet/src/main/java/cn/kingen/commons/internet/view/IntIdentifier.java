package cn.kingen.commons.internet.view;

import cn.kingen.commons.internet.EntityProperty;

/**
 * Indicates that the entity has an integer identifier.
 * <p>
 * Note that this interface is conflict with {@link Identifier} or {@link LongIdentifier}.
 *
 * @author Kingen
 */
@EntityProperty
public interface IntIdentifier {

    /**
     * Returns the integer identifier
     *
     * @return the identifier
     */
    int getId();
}
