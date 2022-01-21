package cn.wsg.commons.data;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class consists exclusively of static methods that provides common descriptors.
 *
 * @author Kingen
 */
@Slf4j
public final class Descriptors {

    private Descriptors() {
    }

    /**
     * Describes the range of the values.
     */
    public static <T extends Comparable<? super T>> void range(Collection<T> values) {
        if (!values.isEmpty()) {
            log.info("Min of the values: {}", values.stream().min(T::compareTo).orElseThrow());
            log.info("Max of the values: {}", values.stream().max(T::compareTo).orElseThrow());
        }
    }

    /**
     * Enumerates distinct keys of the values.
     *
     * @param classifier the classifier function mapping values to keys
     */
    public static <T, K> void enumerate(Collection<T> values, Function<T, K> classifier) {
        Map<K, List<T>> map = values.stream().collect(Collectors.groupingBy(classifier));
        if (!map.isEmpty()) {
            log.info("Enumerating {} distinct keys...", map.size());
            map.entrySet().stream().sorted(Comparator.comparing(entry -> entry.getValue().size()))
                .forEach(entry -> log.info("Key: {}, count: {}", entry.getKey(), entry.getValue().size()));
        }
    }

    /**
     * Tests whether each element of the values matches the predicate.
     */
    public static <T, K> void test(Collection<T> values, Predicate<T> predicate, Function<T, K> identifier) {
        values.stream().filter(predicate).map(identifier).forEach(id -> log.info("{}", id));
    }

    /**
     * Lists stationary points of a list of values which are monotonous in most sections.
     */
    public static <T, K> void stationaryPoints(Iterable<T> values, Comparator<? super T> comparator,
        Function<T, K> identifier) {
        Iterator<T> iterator = values.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        T previous = iterator.next();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (comparator.compare(previous, current) > 0) {
                log.info("{} -> {}", identifier.apply(previous), identifier.apply(current));
            }
            previous = current;
        }
    }

    /**
     * Calculates the distribution of the given list of integer identifiers.
     *
     * @param step the step size to divide the list
     * @return the array of distribution, each element representing the count of ids in each part
     * divided by the step
     */
    public static int[] distribution(Collection<Integer> ids, int step) {
        int max = ids.stream().mapToInt(i -> i).max().orElseThrow();
        int[] array = new int[max + 1];
        for (Integer id : ids) {
            array[id] = 1;
        }
        int[] destinies = new int[max / step + 1];
        for (int i = 0; i < destinies.length; i++) {
            int start = i * step + 1;
            int end = Math.min(start + step, array.length);
            for (int j = start; j < end; j++) {
                destinies[i] += array[j];
            }
        }
        return destinies;
    }
}
