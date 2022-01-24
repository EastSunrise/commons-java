package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * An action performed by a direct agent and indirect participants upon a direct object. Optionally happens at a
 * location with the help of an inanimate
 * instrument. The execution of the action may produce a result. Specific action sub-type documentation specifies the
 * exact expectation of each
 * argument/role.\n\nSee also [blog post](http://blog.schema.org/2014/04/announcing-schemaorg-actions.html) and [Actions
 * overview
 * document](https://schema.org/docs/actions.html).
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_ActionCollabClass"})
public interface Action extends Thing {
}