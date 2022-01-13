package cn.wsg.commons.internet.download;

/**
 * Exception thrown when the target is not a valid link.
 *
 * @author Kingen
 */
public class InvalidLinkException extends IllegalArgumentException {

    private static final long serialVersionUID = -6242451143908523308L;

    public InvalidLinkException(String s) {
        super(s);
    }

    public InvalidLinkException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * If the url is not a valid link of the given type {@code clazz}.
     */
    public InvalidLinkException(Class<? extends DownloadLink> clazz, String url) {
        super(String.format("'%s' is not a valid url of %s", url, clazz));
    }

    /**
     * If the url is not a valid link of the given type {@code clazz}.
     */
    public InvalidLinkException(Class<? extends DownloadLink> clazz, String url, Throwable cause) {
        super(String.format("'%s' is not a valid url of %s", url, clazz), cause);
    }
}
