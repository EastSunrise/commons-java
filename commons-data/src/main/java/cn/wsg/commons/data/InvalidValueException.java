package cn.wsg.commons.data;

/**
 * Exceptions thrown when a value is invalid.
 *
 * @author Kingen
 */
public class InvalidValueException extends Exception {

    private static final long serialVersionUID = -6632541504929469456L;

    public InvalidValueException(String message) {
        super(message);
    }
}
