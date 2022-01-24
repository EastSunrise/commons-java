package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A compound price specification is one that bundles multiple prices that all apply in combination for different
 * dimensions of consumption. Use the name
 * property of the attached unit price specification for indicating the dimension of a price component (e.g.
 * \"electricity\" or \"final cleaning\").
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsClass"})
public interface CompoundPriceSpecification extends PriceSpecification {
}