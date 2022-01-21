package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * The geographic shape of a place. A GeoShape can be described using several properties whose values are based on
 * latitude/longitude pairs. Either whitespace
 * or commas can be used to separate latitude and longitude; whitespace should be used when writing a list of several
 * such points.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews"})
public interface GeoShape extends StructuredValue {
}