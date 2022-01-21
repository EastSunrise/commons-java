package cn.wsg.commons.data.validator;

import cn.wsg.commons.data.Descriptors;
import cn.wsg.commons.data.InvalidValueException;

import java.util.List;

/**
 * Validates whether the values are valid numbers of the specified type.
 *
 * @param <N> type of numbers
 * @author Kingen
 */
abstract class AbstractNumberValidator<N extends Number & Comparable<N>> extends AbstractParsableValidator<N> {

    protected AbstractNumberValidator(Class<N> clazz) {
        super(clazz);
    }

    @Override
    public void describe(List<N> values) {
        Descriptors.range(values);
    }

    @Override
    protected N parseText(String text) throws InvalidValueException {
        try {
            return this.parseValue(text);
        } catch (NumberFormatException e) {
            throw new InvalidValueException(e.getMessage());
        }
    }

    /**
     * Parses the specified string as a number.
     *
     * @param text string to be parsed
     * @return the specified value
     */
    protected abstract N parseValue(String text);
}
