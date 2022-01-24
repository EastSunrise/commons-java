package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A structured value providing information about when a certain organization or person owned a certain product.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsClass"})
public interface OwnershipInfo extends StructuredValue {
}