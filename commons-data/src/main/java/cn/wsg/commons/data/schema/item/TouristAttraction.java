package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A tourist attraction.  In principle any Thing can be a [[TouristAttraction]], from a [[Mountain]] and
 * [[LandmarksOrHistoricalBuildings]] to a
 * [[LocalBusiness]].  This Type can be used on its own to describe a general [[TouristAttraction]], or be used as an
 * [[additionalType]] to add tourist
 * attraction properties to any other type.  (See examples below)
 *
 * @author Kingen
 */
@Source({
    "http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Tourism",
    "http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#IIT-CNR.it"
})
public interface TouristAttraction extends Place {
}