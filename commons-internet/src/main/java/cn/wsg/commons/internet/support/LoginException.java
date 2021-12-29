package cn.wsg.commons.internet.support;

/**
 * Exceptions thrown when logging in a site.
 *
 * @author Kingen
 */
public class LoginException extends Exception {

    private static final long serialVersionUID = 4200733299395702719L;

    public LoginException(String message) {
        super(message);
    }
}
