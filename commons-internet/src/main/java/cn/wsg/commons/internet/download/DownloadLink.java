package cn.wsg.commons.internet.download;

import cn.wsg.commons.internet.org.schema.common.SchemaProperty;
import cn.wsg.commons.internet.org.schema.item.Thing;

/**
 * A download link.
 *
 * @author Kingen
 */
public interface DownloadLink extends Thing {

    /**
     * Returns the URL of the link.
     *
     * @return the URL
     */
    @SchemaProperty("url")
    String getUrl();

    /**
     * Returns a description of the link.
     *
     * @return a description
     */
    @SchemaProperty("description")
    String getDescription();
}
