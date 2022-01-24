package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A structured value representing the duration and scope of services that will be provided to a customer free of charge
 * in case of a defect or malfunction of a
 * product.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsClass"})
public interface WarrantyPromise extends StructuredValue {
}