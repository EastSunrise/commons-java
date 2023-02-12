package cn.kingen.commons.data.validate.validator;

import cn.kingen.commons.data.Descriptors;
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
    public void describe(List<Boolean> values) {
        Descriptors.enumerate(values, Function.identity());
    }

    @Override
    protected Boolean parseText(String text) {
        return Boolean.parseBoolean(text);
    }
}
