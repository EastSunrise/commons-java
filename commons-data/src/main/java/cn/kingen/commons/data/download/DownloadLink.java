package cn.kingen.commons.data.download;

/**
 * A download link.
 *
 * @author Kingen
 */
public interface DownloadLink {

    /**
     * Returns the URL of the link.
     *
     * @return the URL
     */
    String getUrl();

    /**
     * Returns a description of the link.
     *
     * @return a description
     */
    String getDescription();
}
