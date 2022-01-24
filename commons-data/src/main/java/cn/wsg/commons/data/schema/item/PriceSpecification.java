package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A structured value representing a price or price range. Typically, only the subclasses of this type are used for
 * markup. It is recommended to use
 * [[MonetaryAmount]] to describe independent amounts of money such as a salary, credit card limits, etc.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsClass"})
public interface PriceSpecification extends StructuredValue {
}