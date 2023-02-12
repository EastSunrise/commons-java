package cn.kingen.commons.data.validate.validator;

import cn.kingen.commons.data.InvalidValueException;

/**
 * This class provides a skeletal implementation to validate the values that can be parsed from
 * strings.
 *
 * @author Kingen
 */
public abstract class AbstractParsableValidator<T> extends AbstractValidator<T> {

    private final Class<T> clazz;

    protected AbstractParsableValidator(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T convert(Object value) throws InvalidValueException {
        if (clazz.isInstance(value)) {
            return (T)value;
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
    protected abstract T parseText(String text) throws InvalidValueException;
}
