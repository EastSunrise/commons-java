package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * Information about the engine of the vehicle. A vehicle can have multiple engines represented by multiple engine
 * specification entities.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#Automotive_Ontology_Working_Group"})
public interface EngineSpecification extends StructuredValue {
}