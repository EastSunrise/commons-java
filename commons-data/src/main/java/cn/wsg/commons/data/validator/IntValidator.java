package cn.wsg.commons.data.validator;

/**
 * Validates whether the values are valid integers.
 *
 * @author Kingen
 */
public class IntValidator extends AbstractNumberValidator<Integer> {

    public IntValidator() {
        super(Integer.class);
    }

    @Override
    protected Integer parseValue(String text) {
        return Integer.parseInt(text);
    }
}
