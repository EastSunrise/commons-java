package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * An entity holding detailed information about the available bed types, e.g. the quantity of twin beds for a hotel
 * room. For the single case of just one bed of
 * a certain type, you can use bed directly with a text. See also [[BedType]] (under development).
 *
 * @author Kingen
 */
@Source({"https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#STI_Accommodation_Ontology"})
public interface BedDetails extends Intangible {
}