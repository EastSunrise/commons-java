package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * A NewsArticle is an article whose content reports news, or provides background context and supporting materials for
 * understanding the news.
 * <p>
 * A more detailed overview of [schema.org News markup](/docs/news.html) is also available.
 *
 * @author Kingen
 */
@Source({
    "https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#TP-draws",
    "http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews"
})
public interface NewsArticle extends Article {
}