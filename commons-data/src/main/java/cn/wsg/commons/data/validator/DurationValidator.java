package cn.wsg.commons.data.validator;

import cn.wsg.commons.data.Descriptors;

import java.time.Duration;
import java.util.List;

/**
 * Validates whether the values are valid durations.
 *
 * @author Kingen
 */
public class DurationValidator extends AbstractTimeValidator<Duration> {

    public DurationValidator() {
        super(Duration.class);
    }

    @Override
    public void describe(List<Duration> values) {
        Descriptors.range(values);
    }

    @Override
    protected Duration parse(String text) {
        return Duration.parse(text);
    }
}
