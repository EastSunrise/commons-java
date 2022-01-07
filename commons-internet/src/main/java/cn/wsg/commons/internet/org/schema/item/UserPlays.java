package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.SupersededBy;

/**
 * UserInteraction and its subtypes is an old way of talking about users interacting with pages. It is generally better to use [[Action]]-based vocabulary,
 * alongside types such as [[Comment]].
 *
 * @author Kingen
 */
@SupersededBy(InteractionCounter.class)
public interface UserPlays extends UserInteraction {
}