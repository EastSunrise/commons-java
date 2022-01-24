package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * An accommodation is a place that can accommodate human beings, e.g. a hotel room, a camping pitch, or a meeting room.
 * Many accommodations are for overnight
 * stays, but this is not a mandatory requirement.
 * For more specific types of accommodations not defined in schema.org, one can use additionalType with external
 * vocabularies.
 * <br /><br />
 * See also the <a href=\"/docs/hotels.html\">dedicated document on the use of schema.org for marking up hotels and
 * other forms of accommodations</a>.
 *
 * @author Kingen
 */
@Source({"https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#STI_Accommodation_Ontology"})
public interface Accommodation extends Place {
}