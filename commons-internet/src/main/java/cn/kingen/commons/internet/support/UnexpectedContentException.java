package cn.kingen.commons.internet.support;

/**
 * Exceptions thrown when getting unexpected content.
 *
 * @author Kingen
 */
public class UnexpectedContentException extends RuntimeException {

    private static final long serialVersionUID = -4807529841430634898L;

    public UnexpectedContentException(String message) {
        super(message);
    }
}
