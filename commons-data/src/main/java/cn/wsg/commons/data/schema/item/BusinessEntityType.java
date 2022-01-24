package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A business entity type is a conceptual entity representing the legal form, the size, the main line of business, the
 * position in the value chain, or any
 * combination thereof, of an organization or business person.\n\nCommonly used values:\n\n*
 * http://purl.org/goodrelations/v1#Business\n*
 * http://purl.org/goodrelations/v1#Enduser\n* http://purl.org/goodrelations/v1#PublicInstitution\n*
 * http://purl.org/goodrelations/v1#Reseller
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsClass"})
public interface BusinessEntityType extends Enumeration {
}