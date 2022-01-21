package cn.wsg.commons.data.validator;

import cn.wsg.commons.data.InvalidValueException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Validates strings.
 *
 * @author Kingen
 */
@Slf4j
public class StringValidator extends AbstractValidator<String> {

    @Override
    public String convert(Object value) throws InvalidValueException {
        if (!(value instanceof String)) {
            throw new InvalidValueException("Not a string");
        }
        return (String)value;
    }

    /**
     * Counts blank texts.
     */
    @Override
    public void describe(List<String> texts) {
        if (!texts.isEmpty()) {
            long count = texts.stream().filter(String::isBlank).count();
            log.info("Count of blank texts: {}", count);
            long notStripped = texts.stream().filter(s -> s.length() != s.strip().length()).count();
            log.info("Count of not stripped texts: {}", notStripped);
        }
    }
}
