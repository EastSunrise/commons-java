package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.Source;
import cn.wsg.commons.internet.org.schema.common.SupersededBy;

/**
 * UserInteraction and its subtypes is an old way of talking about users interacting with pages. It is generally better
 * to use [[Action]]-based vocabulary,
 * alongside types such as [[Comment]].
 *
 * @author Kingen
 */
@SupersededBy(InteractionCounter.class)
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews"})
public interface UserComments extends UserInteraction {
}