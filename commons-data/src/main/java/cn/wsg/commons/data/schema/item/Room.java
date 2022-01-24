package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A room is a distinguishable space within a structure, usually separated from other spaces by interior walls. (Source:
 * Wikipedia, the free encyclopedia, see
 * <a href=\"http://en.wikipedia.org/wiki/Room\">http://en.wikipedia.org/wiki/Room</a>).
 * <br /><br />
 * See also the <a href=\"/docs/hotels.html\">dedicated document on the use of schema.org for marking up hotels and
 * other forms of accommodations</a>.
 *
 * @author Kingen
 */
@Source({"https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#STI_Accommodation_Ontology"})
public interface Room extends Accommodation {
}