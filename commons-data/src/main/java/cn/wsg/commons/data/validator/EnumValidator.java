package cn.wsg.commons.data.validator;

import cn.wsg.commons.data.Descriptors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

/**
 * Validates whether the values are enumerable.
 *
 * @author Kingen
 */
@Slf4j
public class EnumValidator extends AbstractValidator<Object> {

    @Override
    public Object convert(Object value) {
        return value;
    }

    @Override
    public void describe(List<Object> values) {
        Descriptors.enumerate(values, Function.identity());
    }
}
