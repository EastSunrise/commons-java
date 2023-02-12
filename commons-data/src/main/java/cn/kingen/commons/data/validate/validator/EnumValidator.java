package cn.kingen.commons.data.validate.validator;

import cn.kingen.commons.data.Descriptors;
import java.util.List;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

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
