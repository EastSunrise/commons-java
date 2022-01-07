package cn.wsg.commons.lang;

import cn.wsg.commons.lang.function.CodeSupplier;
import cn.wsg.commons.lang.function.IntCodeSupplier;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.function.Function;

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
     * Returns the enum constant of the specified enum type with the specified key.
     *
     * @param keyMapper function to generate a <strong>unique</strong> key for each enum
     */
    public static <K, E extends Enum<E>> E valueOfKey(Class<E> clazz, K key, Function<E, K> keyMapper) {
        for (E e : clazz.getEnumConstants()) {
            if (Objects.equals(keyMapper.apply(e), key)) {
                return e;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown key '%s' for '%s'", key, clazz));
    }

    /**
     * Returns the enum constant of the specified enum type with the specified ignore-case name.
     * This method is a special case of {@link #valueOfKey(Class, Object, Function)}.
     *
     * @see #valueOfKey(Class, Object, Function)
     */
    public static <E extends Enum<E>> E valueOfIgnoreCase(Class<E> clazz, String target) {
        for (E e : clazz.getEnumConstants()) {
            if (StringUtils.equalsIgnoreCase(e.name(), target)) {
                return e;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown name '%s' for '%s'", target, clazz));
    }

    /**
     * Returns the enum constant of the specified enum type with the specified code. This method is
     * a special case of {@link #valueOfKey(Class, Object, Function)}.
     *
     * @see #valueOfKey(Class, Object, Function)
     * @see CodeSupplier
     */
    public static <E extends Enum<E> & CodeSupplier> E valueOfCode(Class<E> clazz, String code) {
        return valueOfKey(clazz, code, CodeSupplier::getCode);
    }

    /**
     * Returns the enum constant of the specified enum type with the specified code. This method is
     * a special case of {@link #valueOfKey(Class, Object, Function)}.
     *
     * @see #valueOfKey(Class, Object, Function)
     * @see IntCodeSupplier
     */
    public static <E extends Enum<E> & IntCodeSupplier> E valueOfIntCode(Class<E> clazz, int code) {
        return valueOfKey(clazz, code, IntCodeSupplier::getIntCode);
    }
}
