package cn.wsg.commons.internet.download;

/**
 * A download link that is encrypted with a password.
 *
 * @author Kingen
 */
public interface EncryptedLink extends DownloadLink {

    /**
     * Returns the password of the link.
     *
     * @return the password
     */
    String getPassword();
}
