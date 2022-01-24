package cn.wsg.commons.data.validate.validator;

import cn.wsg.commons.data.Descriptors;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Validates whether the values are valid {@link LocalTime}.
 *
 * @author Kingen
 */
public class LocalTimeValidator extends AbstractTimeValidator<LocalTime> {

    private final DateTimeFormatter formatter;

    public LocalTimeValidator() {
        super(LocalTime.class);
        this.formatter = DateTimeFormatter.ISO_LOCAL_TIME;
    }

    public LocalTimeValidator(DateTimeFormatter formatter) {
        super(LocalTime.class);
        this.formatter = formatter;
    }

    @Override
    public void describe(List<LocalTime> values) {
        Descriptors.range(values);
    }

    @Override
    protected LocalTime parse(String text) {
        return LocalTime.parse(text, formatter);
    }
}
