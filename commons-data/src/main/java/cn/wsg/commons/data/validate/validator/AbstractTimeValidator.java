package cn.wsg.commons.data.validate.validator;

import cn.wsg.commons.data.InvalidValueException;

import java.time.format.DateTimeParseException;

/**
 * This class is a skeletal implementation that validates {@link java.time} objects.
 *
 * @author Kingen
 */
abstract class AbstractTimeValidator<R> extends AbstractParsableValidator<R> {

    protected AbstractTimeValidator(Class<R> clazz) {
        super(clazz);
    }

    /**
     * @throws InvalidValueException if the text cannot be parsed to a {@link java.time} object
     */
    @Override
    protected R parseText(String text) throws InvalidValueException {
        try {
            return parse(text);
        } catch (DateTimeParseException e) {
            throw new InvalidValueException(e);
        }
    }

    /**
     * Parses the text to a {@link java.time} object.
     *
     * @param text the text to be parsed
     * @return a {@link java.time} object
     * @throws DateTimeParseException if the text cannot be parsed to a {@link java.time} object
     */
    protected abstract R parse(String text) throws DateTimeParseException;
}
