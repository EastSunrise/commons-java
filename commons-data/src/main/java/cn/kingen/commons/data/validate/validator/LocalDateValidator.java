package cn.kingen.commons.data.validate.validator;

import cn.kingen.commons.data.Descriptors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Validates whether the values are valid {@link LocalDate}.
 *
 * @author Kingen
 */
public class LocalDateValidator extends AbstractTimeValidator<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateValidator() {
        super(LocalDate.class);
        this.formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    public LocalDateValidator(DateTimeFormatter formatter) {
        super(LocalDate.class);
        this.formatter = formatter;
    }

    @Override
    public void describe(List<LocalDate> values) {
        Descriptors.range(values);
    }

    @Override
    protected LocalDate parse(String text) {
        return LocalDate.parse(text, formatter);
    }
}
