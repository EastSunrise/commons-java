package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A property-value pair, e.g. representing a feature of a product or place. Use the 'name' property for the name of the
 * property. If there is an additional
 * human-readable version of the value, put that into the 'description' property.\n\n Always use specific schema.org
 * properties when a) they exist and b) you
 * can populate them. Using PropertyValue as a substitute will typically not trigger the same effect as using the
 * original, specific property.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsClass"})
public interface PropertyValue extends StructuredValue {
}