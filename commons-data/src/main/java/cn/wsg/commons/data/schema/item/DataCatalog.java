package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.RdfClass;
import cn.wsg.commons.data.schema.common.Source;

/**
 * A collection of datasets.
 *
 * @author Kingen
 */
@RdfClass(equivalentClass = {"http://www.w3.org/ns/dcat#Catalog"})
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_DatasetClass"})
public interface DataCatalog extends CreativeWork {
}