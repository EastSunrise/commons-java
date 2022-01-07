package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.RdfClass;
import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * A part of a successively published publication such as a periodical or publication volume, often numbered, usually containing a grouping of works such as
 * articles.\n\nSee also [blog post](http://blog.schema.org/2014/09/schemaorg-support-for-bibliographic_2.html).
 *
 * @author Kingen
 */
@RdfClass(equivalentClass = {"http://purl.org/ontology/bibo/Issue"})
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex"})
public interface PublicationIssue extends CreativeWork {
}