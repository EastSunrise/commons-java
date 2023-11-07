package cn.kingen.commons.util;

import cn.kingen.commons.lang.AbbrSupplier;
import cn.kingen.commons.lang.CodeSupplier;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Helper methods for Java enums.
 *
 * @author Kingen
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnumUtils {

    /**
     * Resolves target enum from the specified predicate.
     *
     * @param matcher function to test whether an enum is matched
     * @return matched enum
     * @throws IllegalArgumentException if no enum is matched
     */
    public static <E extends Enum<E>> E resolve(Class<E> clazz, Predicate<E> matcher) throws IllegalArgumentException {
        for (E e : clazz.getEnumConstants()) {
            if (matcher.test(e)) {
                return e;
            }
        }
        throw new IllegalArgumentException(String.format("No enum of '%s' is matched", clazz.getName()));
    }

    /**
     * Resolves the enum constant of the specified enum type matching the specified key.
     *
     * @throws IllegalArgumentException if no enum of the specified type matches the key
     */
    public static <K, E extends Enum<E>> E ofKey(Class<E> clazz, K key, BiPredicate<K, E> predicate) {
        for (E e : clazz.getEnumConstants()) {
            if (predicate.test(key, e)) {
                return e;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown key '%s' for '%s'", key, clazz));
    }

    /**
     * Resolves the enum constant of the specified enum type matching the specified integer code.
     *
     * @throws IllegalArgumentException if no enum of the specified type matches the code
     * @see CodeSupplier
     */
    public static <E extends Enum<E> & CodeSupplier> E ofCode(Class<E> clazz, int code) {
        for (E e : clazz.getEnumConstants()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown code '%d' for '%s'", code, clazz));
    }

    /**
     * Resolves the enum constant of the specified enum type matching the specified abbreviation.
     *
     * @throws IllegalArgumentException if no enum of the specified type matches the abbreviation
     * @see AbbrSupplier
     */
    public static <E extends Enum<E> & AbbrSupplier> E ofAbbr(Class<E> clazz, String abbr) {
        for (E e : clazz.getEnumConstants()) {
            if (Objects.equals(e.getAbbr(), abbr)) {
                return e;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown abbreviation '%s' for '%s'", abbr, clazz));
    }
}
