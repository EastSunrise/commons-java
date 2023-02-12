package cn.kingen.commons.lang.util;

import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * Extension of utilities for number operations.
 *
 * @author Kingen
 * @see org.apache.commons.lang3.math.NumberUtils
 */
public final class NumberUtilsExt {

    private static final Map<Character, Integer> SYMBOLS =
        Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

    private NumberUtilsExt() {
    }

    /**
     * Parses a char-separated string as a signed long.
     *
     * @param text      the char-separated string, e.g. '21,324'
     * @param separator the separator
     * @return the {@code long} represented by the argument
     * @throws NumberFormatException if the string does not contain a parsable long.
     */
    public static long parseSeparatedNumber(String text, char separator) {
        Objects.requireNonNull(text, "Text can't be null");
        return Long.parseLong(StringUtils.remove(text, separator));
    }

    /**
     * Converts a string of a Roman number to an integer.
     */
    public static int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int ret = 0;
        int last = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            char ch = chars[i];
            int value = SYMBOLS.get(ch);
            if (value < last) {
                ret -= value;
            } else {
                ret += value;
            }
            last = value;
        }
        return ret;
    }
}
