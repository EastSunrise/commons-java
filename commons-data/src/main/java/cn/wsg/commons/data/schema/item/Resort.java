package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A resort is a place used for relaxation or recreation, attracting visitors for holidays or vacations. Resorts are
 * places, towns or sometimes commercial
 * establishment operated by a single company (Source: Wikipedia, the free encyclopedia, see
 * <a href=\"http://en.wikipedia.org/wiki/Resort\">http://en.wikipedia.org/wiki/Resort</a>).
 * <br /><br />
 * See also the <a href=\"/docs/hotels.html\">dedicated document on the use of schema.org for marking up hotels and
 * other forms of accommodations</a>.
 *
 * @author Kingen
 */
@Source({"https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#STI_Accommodation_Ontology"})
public interface Resort extends LodgingBusiness {
}