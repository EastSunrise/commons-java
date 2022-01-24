package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.RdfClass;
import cn.wsg.commons.data.schema.common.Source;

/**
 * A person (alive, dead, undead, or fictional).
 *
 * @author Kingen
 */
@RdfClass(equivalentClass = {"http://xmlns.com/foaf/0.1/Person"})
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews"})
public interface Person extends Thing {
}