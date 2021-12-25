package cn.wsg.commons.data.validator;


/**
 * Validates whether the values are valid doubles.
 *
 * @author Kingen
 */
public class DoubleValidator extends AbstractNumberValidator<Double> {

    public DoubleValidator() {
        super(Double.class);
    }

    @Override
    protected Double parseValue(String text) {
        return Double.parseDouble(text);
    }
}
