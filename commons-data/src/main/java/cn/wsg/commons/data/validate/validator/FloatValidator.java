package cn.wsg.commons.data.validate.validator;

/**
 * Validates whether the values are valid floats.
 *
 * @author Kingen
 */
public class FloatValidator extends AbstractNumberValidator<Float> {

    public FloatValidator() {
        super(Float.class);
    }

    @Override
    protected Float parseValue(String text) {
        return Float.parseFloat(text);
    }
}
