package cn.wsg.commons.data.validate.validator;

import cn.wsg.commons.data.Descriptors;

import java.util.List;
import java.util.function.Function;

/**
 * Validates the type of each element of values.
 *
 * @author Kingen
 */
public class TypeValidator extends AbstractValidator<Class<?>> {

    @Override
    public Class<?> convert(Object value) {
        return value.getClass();
    }

    @Override
    public void describe(List<Class<?>> classes) {
        Descriptors.enumerate(classes, Function.identity());
    }
}
