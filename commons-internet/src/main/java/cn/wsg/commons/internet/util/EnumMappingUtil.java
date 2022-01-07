package cn.wsg.commons.internet.util;

import cn.wsg.commons.lang.EnumUtilExt;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

/**
 * Utility of adapting enum constants.
 *
 * @author Kingen
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnumMappingUtil {

    /**
     * Deserializes the specified value to an enum constant of the specified enum type through the key generator or extra mappings.
     *
     * @param eClass the type of target enum constant
     * @param aClass the type of extra mappings between the target enum type and the value
     * @param value  the value to be deserialized
     * @see EnumMapping
     */
    public static <E extends Enum<E>, A extends Enum<A> & EnumMapping<E>> E valueOf(Class<E> eClass, Function<E, String> keyMapper, Class<A> aClass,
        String value) {
        try {
            return EnumUtilExt.valueOfKey(eClass, value, keyMapper);
        } catch (IllegalArgumentException e) {
            return valueOfAlias(eClass, aClass, value);
        }
    }

    /**
     * Deserializes the specified value to an enum constant of the specified enum type through extra mappings.
     *
     * @param eClass the type of target enum constant
     * @param aClass the type of extra mappings between the target enum type and the value
     * @param value  the value to be deserialized
     * @see EnumMapping
     */
    public static <E extends Enum<E>, A extends Enum<A> & EnumMapping<E>> E valueOfAlias(Class<E> eClass, Class<A> aClass, String value) {
        for (A a : aClass.getEnumConstants()) {
            if (a.contains(value)) {
                return a.getEnum();
            }
        }
        throw new IllegalArgumentException(String.format("Unknown alias '%s' for '%s' by '%s'", value, eClass, aClass));
    }
}
