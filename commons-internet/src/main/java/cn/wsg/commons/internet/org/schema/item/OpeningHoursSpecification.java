package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * A structured value providing information about the opening hours of a place or a certain service inside a place.\n\n
 * The place is __open__ if the [[opens]] property is specified, and __closed__ otherwise.\n\nIf the value for the [[closes]] property is less than the value
 * for the [[opens]] property then the hour range is assumed to span over the next day.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsClass"})
public interface OpeningHoursSpecification extends StructuredValue {
}