package cn.wsg.commons.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * Extension of utilities for number operations.
 *
 * @author Kingen
 * @see org.apache.commons.lang3.math.NumberUtils
 */
public final class NumberUtilsExt {

    private static final char NUMBER_SEPARATOR = ',';
    private static final Map<Character, Integer> SYMBOLS =
        Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

    private NumberUtilsExt() {
    }

    public static long parseCommaSeparatedNumber(String text) {
        Objects.requireNonNull(text, "Text can't be null");
        return Long.parseLong(StringUtils.remove(text, NUMBER_SEPARATOR));
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