package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.SupersededBy;

/**
 * A media season e.g. tv, radio, video game etc.
 *
 * @author Kingen
 */
@SupersededBy(CreativeWorkSeason.class)
public interface Season extends CreativeWork {
}