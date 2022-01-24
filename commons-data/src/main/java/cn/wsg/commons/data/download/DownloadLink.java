package cn.wsg.commons.data.download;

import cn.wsg.commons.data.schema.common.SchemaProperty;
import cn.wsg.commons.data.schema.item.Thing;

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
