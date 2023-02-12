package cn.kingen.commons.data.download;

/**
 * Exception thrown when a given password is invalid.
 *
 * @author Kingen
 */
public class InvalidPasswordException extends InvalidLinkException {

    private static final long serialVersionUID = 1487936973372538799L;

    public InvalidPasswordException(String s) {
        super(s);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * If the given password is invalid.
     */
    public InvalidPasswordException(Class<? extends DownloadLink> clazz, String password) {
        super(String.format("'%s' is not a valid password for %s", password, clazz));
    }
}
