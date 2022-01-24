package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * An intangible item that describes an alignment between a learning resource and a node in an educational framework.
 * <p>
 * Should not be used where the nature of the alignment can be described using a simple property, for example to express
 * that a resource [[teaches]] or
 * [[assesses]] a competency.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_LRMIClass"})
public interface AlignmentObject extends Intangible {
}