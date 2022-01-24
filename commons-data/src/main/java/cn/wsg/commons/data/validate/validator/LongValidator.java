package cn.wsg.commons.data.validate.validator;

/**
 * Validates whether the values are valid longs.
 *
 * @author Kingen
 */
public class LongValidator extends AbstractNumberValidator<Long> {

    public LongValidator() {
        super(Long.class);
    }

    @Override
    protected Long parseValue(String text) {
        return Long.parseLong(text);
    }
}
