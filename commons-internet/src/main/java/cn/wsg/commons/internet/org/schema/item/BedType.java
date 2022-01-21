package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * A type of bed. This is used for indicating the bed or beds available in an accommodation.
 *
 * @author Kingen
 */
@Source({
    "https://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#STI_Accommodation_Ontology",
    "https://github.com/schemaorg/schemaorg/issues/1262"
})
public interface BedType extends QualitativeValue {
}