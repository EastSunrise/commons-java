package cn.wsg.commons.data.validate;

import cn.wsg.commons.data.Converter;
import cn.wsg.commons.data.Descriptor;
import cn.wsg.commons.data.validate.validator.AbstractValidator;

import java.util.List;

/**
 * Validates the given values and describes their features.
 *
 * @param <E> target type to which the values are converted to
 * @author Kingen
 * @see AbstractValidator
 */
public interface Validator<E> extends Converter<E>, Descriptor<E> {

    /**
     * Validates given values of unknown type.
     *
     * @param values values to be validated, may contain null values
     */
    void validate(List<?> values);
}
