package cn.wsg.commons.lang;

import cn.wsg.commons.lang.function.CodeSupplier;
import cn.wsg.commons.lang.function.IntCodeSupplier;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Utility for Java enums.
 *
 * @author Kingen
 * @see org.apache.commons.lang3.EnumUtils
 */
public final class EnumUtilExt {

    private EnumUtilExt() {
    }

    /**
     * Returns the enum constant of the specified enum type that matches the predicate.
     *
     * @param predicate function to test whether the key matches one enum constant of the enum type
     */
    public static <K, E extends Enum<E>> E valueOf(Class<E> clazz, K key, BiPredicate<K, E> predicate) {
        for (E e : clazz.getEnumConstants()) {
            if (predicate.test(key, e)) {
                return e;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown key '%s' for '%s'", key, clazz));
    }

    /**
     * Returns the enum constant of the specified enum type with the specified code. This method is
     * a special case of {@link #valueOf(Class, Object, BiPredicate)}
     *
     * @see #valueOf(Class, Object, BiPredicate)
     * @see CodeSupplier
     */
    public static <E extends Enum<E> & CodeSupplier> E valueOfCode(Class<E> clazz, String code) {
        return valueOf(clazz, code, (s, e) -> Objects.equals(s, e.getCode()));
    }

    /**
     * Returns the enum constant of the specified enum type with the specified code. This method is
     * a special case of {@link #valueOf(Class, Object, BiPredicate)}.
     *
     * @see #valueOf(Class, Object, BiPredicate)
     * @see IntCodeSupplier
     */
    public static <E extends Enum<E> & IntCodeSupplier> E valueOfIntCode(Class<E> clazz, int code) {
        return valueOf(clazz, code, (c, e) -> c == e.getIntCode());
    }
}
