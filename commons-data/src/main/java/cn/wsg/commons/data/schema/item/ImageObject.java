package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.RdfClass;

/**
 * An image file.
 *
 * @author Kingen
 */
@RdfClass(equivalentClass = {"http://purl.org/dc/dcmitype/Image"})
public interface ImageObject extends MediaObject {
}