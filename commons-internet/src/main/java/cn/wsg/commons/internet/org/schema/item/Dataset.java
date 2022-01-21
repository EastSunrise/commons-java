package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.RdfClass;
import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * A body of structured information describing some topic(s) of interest.
 *
 * @author Kingen
 */
@RdfClass(equivalentClass = {
    "http://www.w3.org/ns/dcat#Dataset", "http://rdfs.org/ns/void#Dataset", "http://purl.org/dc/dcmitype/Dataset"
})
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_DatasetClass"})
public interface Dataset extends CreativeWork {
}