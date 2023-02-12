package cn.kingen.commons.data.validate;

/**
 * Exceptions thrown when the target validator is not found.
 *
 * @author Kingen
 */
public class ValidatorNotFoundException extends Exception {

    public ValidatorNotFoundException(String message) {
        super(message);
    }
}
