package cn.kingen.commons.data.validate.validator;

import cn.kingen.commons.data.InvalidValueException;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

/**
 * Validates whether the string values matches a pattern.
 *
 * @author Kingen
 */
@Slf4j
public class PatternValidator extends AbstractParsableValidator<MatchResult> {

    private final Pattern pattern;
    private final MatcherType type;

    public PatternValidator(Pattern pattern, MatcherType type) {
        super(MatchResult.class);
        this.pattern = pattern;
        this.type = type;
    }

    public PatternValidator(Pattern pattern) {
        this(pattern, MatcherType.MATCHES);
    }

    @Override
    public void describe(List<MatchResult> results) {
    }

    @Override
    protected MatchResult parseText(String text) throws InvalidValueException {
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
