package cn.wsg.commons.data.validator;

import cn.wsg.commons.data.InvalidValueException;

/**
 * This class provides a skeletal implementation to validate the values that can be parsed from
 * strings.
 *
 * @author Kingen
 */
public abstract class AbstractParsableValidator<E> extends AbstractValidator<E> {

    private final Class<E> clazz;

    protected AbstractParsableValidator(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E convert(Object value) throws InvalidValueException {
        if (clazz.isInstance(value)) {
            return (E) value;
        }
        return parseText(value.toString());
    }

    /**
     * Parses the specified string as a target object.
     *
     * @param text string to be parsed
     * @return the specified value
     * @throws InvalidValueException if the text is invalid
     */
    protected abstract E parseText(String text) throws InvalidValueException;
}
