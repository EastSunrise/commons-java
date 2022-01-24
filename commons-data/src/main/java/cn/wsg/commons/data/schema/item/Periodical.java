package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.RdfClass;
import cn.wsg.commons.data.schema.common.Source;

/**
 * A publication in any medium issued in successive parts bearing numerical or chronological designations and intended,
 * such as a magazine, scholarly journal,
 * or newspaper to continue indefinitely.\n\nSee also [blog post](http://blog.schema
 * .org/2014/09/schemaorg-support-for-bibliographic_2.html).
 *
 * @author Kingen
 */
@RdfClass(equivalentClass = {"http://purl.org/ontology/bibo/Periodical"})
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_bibex"})
public interface Periodical extends CreativeWorkSeries {
}