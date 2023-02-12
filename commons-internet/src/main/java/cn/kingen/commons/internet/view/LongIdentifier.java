package cn.kingen.commons.internet.view;

import cn.kingen.commons.internet.EntityProperty;

/**
 * Indicates that the entity has an long identifier.
 * <p>
 * Note that this interface is conflict with {@link Identifier} or {@link IntIdentifier}.
 *
 * @author Kingen
 */
@EntityProperty
public interface LongIdentifier {

    /**
     * Returns the integer identifier
     *
     * @return the identifier
     */
    long getId();
}
