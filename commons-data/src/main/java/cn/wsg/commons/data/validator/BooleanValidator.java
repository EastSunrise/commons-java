package cn.wsg.commons.data.validator;

import cn.wsg.commons.data.Descriptors;
import java.util.List;
import java.util.function.Function;

/**
 * Validates whether the values are valid booleans.
 *
 * @author Kingen
 */
public class BooleanValidator extends AbstractParsableValidator<Boolean> {

    public BooleanValidator() {
        super(Boolean.class);
    }

    @Override
    protected Boolean parseText(String text) {
        return Boolean.parseBoolean(text);
    }

    @Override
    public void describe(List<Boolean> values) {
        Descriptors.enumerate(values, Function.identity());
    }
}
