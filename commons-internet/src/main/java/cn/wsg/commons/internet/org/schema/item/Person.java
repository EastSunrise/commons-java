package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.RdfClass;
import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * A person (alive, dead, undead, or fictional).
 *
 * @author Kingen
 */
@RdfClass(equivalentClass = {"http://xmlns.com/foaf/0.1/Person"})
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_rNews"})
public interface Person extends Thing {
}