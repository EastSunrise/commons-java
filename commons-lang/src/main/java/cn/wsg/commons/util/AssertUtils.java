package cn.wsg.commons.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Utility to check objects or conditions before operations.
 *
 * @author Kingen
 */
public final class AssertUtils {

    private AssertUtils() {
    }

    public static <E extends Comparable<? super E>> boolean isMonotonous(Iterable<E> iterable) {
        return isMonotonous(iterable, Comparator.comparing(Function.identity()));
    }

    /**
     * Tests whether the given iterator is monotonous by the specified comparator.
     *
     * @param iterable the iterable object to be tested
     * @return {@code false} if any element is <b>larger</b> (by the specified comparator) than the
     * next one, otherwise {@code true}
     */
    public static <E> boolean isMonotonous(Iterable<E> iterable, Comparator<? super E> comparator) {
        Iterator<E> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return true;
        }
        E previous = iterator.next();
        while (iterator.hasNext()) {
            E current = iterator.next();
            if (0 < comparator.compare(previous, current)) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    /**
     * Checks that the specified object reference is not {@code null} and throws a customized {@link
     * IllegalArgumentException} if it is.
     *
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }

    /**
     * Validate range of an object, [fromInclusive, toExclusive)
     */
    public static <T extends Comparable<T>> T requireRange(T target, T fromInclusive, T toExclusive) {
        Objects.requireNonNull(target);
        if (null == fromInclusive && null == toExclusive) {
            return target;
        }
        if (null == fromInclusive) {
            if (0 <= target.compareTo(toExclusive)) {
                throw new IllegalArgumentException("Target must be less than " + toExclusive);
            }
            return target;
        }
        if (null == toExclusive) {
            if (0 > target.compareTo(fromInclusive)) {
                throw new IllegalArgumentException("Target mustn't be less than " + fromInclusive);
            }
            return target;
        }
        if (0 > target.compareTo(fromInclusive) || 0 <= target.compareTo(toExclusive)) {
            throw new IllegalArgumentException(
                "Target must be within range fromInclusive " + fromInclusive + " toExclusive " + toExclusive);
        }
        return target;
    }

    /**
     * Test whether the object equals to another one.
     *
     * @param t1 first object
     * @param t2 another object
     * @throws IllegalArgumentException if test is failed
     */
    public static <T> void requireEquals(T t1, T t2) {
        if (!Objects.equals(t1, t2)) {
            throw new IllegalArgumentException("'" + t1 + "' doesn't equal to '" + t2 + "'");
        }
    }

    /**
     * Test an object.
     *
     * @param t         given object
     * @param predicate function to test the object
     * @return the object
     * @throws IllegalArgumentException if test is failed
     */
    public static <T> T require(T t, Predicate<? super T> predicate, String message) {
        if (predicate.test(t)) {
            return t;
        }
        throw new IllegalArgumentException(message);
    }

    /**
     * Validate args to be not blank.
     */
    public static String requireNotBlank(String arg) {
        if (StringUtils.isBlank(arg)) {
            throw new IllegalArgumentException("Arg mustn't be blank.");
        }
        return arg;
    }

    /**
     * Validate a string to be not blank.
     */
    public static String requireNotBlank(String arg, String message) {
        if (StringUtils.isBlank(arg)) {
            throw new IllegalArgumentException(message);
        }
        return arg;
    }

    /**
     * Validate a collection to be not empty.
     */
    public static <E, T extends Collection<E>> T requireNotEmpty(T arg, String message) {
        if (CollectionUtils.isEmpty(arg)) {
            throw new IllegalArgumentException(message);
        }
        return arg;
    }

    /**
     * Validate a map to be not empty.
     */
    public static <K, V, T extends Map<K, V>> T requireNotEmpty(T arg, String message) {
        if (MapUtils.isEmpty(arg)) {
            throw new IllegalArgumentException(message);
        }
        return arg;
    }

    /**
     * Validate args to be not blank.
     */
    public static String requireNotBlankElse(String arg, String defaultStr) {
        if (StringUtils.isNotBlank(arg)) {
            return arg;
        }
        return requireNotBlank(defaultStr, "defaultStr");
    }
}
