package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A single, identifiable product instance (e.g. a laptop with a particular serial number).
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsClass"})
public interface IndividualProduct extends Product {
}