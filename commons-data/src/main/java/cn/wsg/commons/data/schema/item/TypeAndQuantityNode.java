package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A structured value indicating the quantity, unit of measurement, and business function of goods included in a bundle
 * offer.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsClass"})
public interface TypeAndQuantityNode extends StructuredValue {
}