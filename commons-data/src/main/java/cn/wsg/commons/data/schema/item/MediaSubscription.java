package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A subscription which allows a user to access media including audio, video, books, etc.
 *
 * @author Kingen
 */
@Source({"https://github.com/schemaorg/schemaorg/issues/1741"})
public interface MediaSubscription extends Intangible {
}