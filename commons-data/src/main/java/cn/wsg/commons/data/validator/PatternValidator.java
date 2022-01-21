package cn.wsg.commons.data.validator;

import cn.wsg.commons.data.InvalidValueException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates whether the string values matches a pattern.
 *
 * @author Kingen
 */
@Slf4j
public class PatternValidator extends AbstractValidator<MatchResult> {

    private final Pattern pattern;
    private final MatcherType type;

    public PatternValidator(Pattern pattern, MatcherType type) {
        this.pattern = pattern;
        this.type = type;
    }

    public PatternValidator(Pattern pattern) {
        this(pattern, MatcherType.MATCHES);
    }

    @Override
    public MatchResult convert(Object value) throws InvalidValueException {
        if (value instanceof MatchResult) {
            return (MatchResult)value;
        }
        String text = value.toString();
        Matcher matcher = pattern.matcher(text);
        switch (type) {
            case MATCHES:
                if (matcher.matches()) {
                    return matcher.toMatchResult();
                }
                break;
            case FIND:
                if (matcher.find()) {
                    return matcher.toMatchResult();
                }
                break;
            case LOOKING_AT:
                if (matcher.lookingAt()) {
                    return matcher.toMatchResult();
                }
                break;
            default:
                throw new InvalidValueException("Unknown type of matcher");
        }
        throw new InvalidValueException("Mismatch text");
    }

    @Override
    public void describe(List<MatchResult> results) {
    }

    public enum MatcherType {
        /**
         * @see Matcher#matches()
         */
        MATCHES,
        /**
         * @see Matcher#find()
         */
        FIND,
        /**
         * @see Matcher#lookingAt()
         */
        LOOKING_AT
    }
}
