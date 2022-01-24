package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A SpeakableSpecification indicates (typically via [[xpath]] or [[cssSelector]]) sections of a document that are
 * highlighted as particularly [[speakable]].
 * Instances of this type are expected to be used primarily as values of the [[speakable]] property.
 *
 * @author Kingen
 */
@Source({"https://github.com/schemaorg/schemaorg/issues/1389"})
public interface SpeakableSpecification extends Intangible {
}