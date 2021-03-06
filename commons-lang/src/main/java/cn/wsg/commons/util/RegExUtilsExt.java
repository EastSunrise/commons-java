package cn.wsg.commons.util;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helpers to process Strings using regular expressions.
 *
 * @author Kingen
 * @see org.apache.commons.lang3.RegExUtils
 */
public final class RegExUtilsExt {

    private RegExUtilsExt() {
    }

    /**
     * Check if the text is full-matched for the pattern.
     *
     * @return an matcher if full-matched.
     * @throws IllegalArgumentException if not full-matched
     */
    public static Matcher matchesOrElseThrow(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            return matcher;
        }
        String msg = String.format("Not matched string: '%s' for pattern: '%s'", text, pattern.pattern());
        throw new IllegalArgumentException(msg);
    }

    /**
     * If the pattern is found in the text, performs the given action with the value, otherwise does
     * nothing.
     */
    public static void ifFind(Pattern pattern, CharSequence text, Consumer<Matcher> consumer) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            consumer.accept(matcher);
        }
    }

    /**
     * Check if the pattern is found in the text.
     *
     * @return an matcher if found
     * @throws IllegalArgumentException if not found
     */
    public static Matcher findOrElseThrow(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher;
        }
        String msg = String.format("Not found pattern: '%s' in the text: '%s'", pattern.pattern(), text);
        throw new IllegalArgumentException(msg);
    }

    /**
     * Check if the text starts with the pattern.
     *
     * @return an matcher if starting with
     * @throws IllegalArgumentException if not start
     */
    public static Matcher lookingAtOrElseThrow(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.lookingAt()) {
            return matcher;
        }
        String msg = String.format("Not start with pattern: '%s' do the text: '%s'", pattern.pattern(), text);
        throw new IllegalArgumentException(msg);
    }
}
