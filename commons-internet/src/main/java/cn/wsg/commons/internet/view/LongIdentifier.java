package cn.wsg.commons.internet.view;

import cn.wsg.commons.internet.EntityProperty;

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
