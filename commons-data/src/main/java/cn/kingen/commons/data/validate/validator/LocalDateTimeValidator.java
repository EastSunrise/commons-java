package cn.kingen.commons.data.validate.validator;

import cn.kingen.commons.data.Descriptors;
import cn.kingen.commons.lang.DatetimeConsts;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Validates whether the values are valid {@link LocalDateTime}.
 *
 * @author Kingen
 */
public class LocalDateTimeValidator extends AbstractTimeValidator<LocalDateTime> {

    private final DateTimeFormatter formatter;

    public LocalDateTimeValidator() {
        super(LocalDateTime.class);
        this.formatter = DatetimeConsts.DTF_YYYY_MM_DD_HH_MM_SS;
    }

    public LocalDateTimeValidator(DateTimeFormatter formatter) {
        super(LocalDateTime.class);
        this.formatter = formatter;
    }

    @Override
    public void describe(List<LocalDateTime> values) {
        Descriptors.range(values);
    }

    @Override
    protected LocalDateTime parse(String text) {
        return LocalDateTime.parse(text, formatter);
    }
}
