package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.RdfClass;
import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * A dataset in downloadable form.
 *
 * @author Kingen
 */
@RdfClass(equivalentClass = {"http://www.w3.org/ns/dcat#Distribution"})
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_DatasetClass"})
public interface DataDownload extends MediaObject {
}