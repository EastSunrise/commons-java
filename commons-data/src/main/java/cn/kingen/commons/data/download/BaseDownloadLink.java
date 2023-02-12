package cn.kingen.commons.data.download;

/**
 * A base implementation of {@link DownloadLink}.
 *
 * @author Kingen
 */
public abstract class BaseDownloadLink implements DownloadLink {

    private final String description;

    protected BaseDownloadLink(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getUrl();
    }
}
