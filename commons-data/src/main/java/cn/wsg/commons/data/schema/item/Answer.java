package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * An answer offered to a question; perhaps correct, perhaps opinionated or wrong.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_QAStackExchange"})
public interface Answer extends Comment {
}