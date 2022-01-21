package cn.wsg.commons.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Extension of {@link org.apache.commons.collections4.CollectionUtils}.
 *
 * @author Kingen
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CollectionUtilsExt {

    /**
     * Converts a collection of {@code Integer} to an unboxed array.
     */
    public static int[] toIntArray(Collection<Integer> collection) {
        int[] arr = new int[collection.size()];
        int i = 0;
        for (int value : collection) {
            arr[i++] = value;
        }
        return arr;
    }

    /**
     * Searches the specified list within the specified range for the specified object using the binary
     * search algorithm.The list must be sorted into ascending order according to the specified comparator, prior to
     * making this call.
     *
     * @param fromIndex the index of the first element (inclusive) to be searched
     * @param toIndex   the index of the last element (exclusive) to be searched
     * @return the index of the search key, if it is contained in the list;
     * otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.
     * @see java.util.Collections#binarySearch(List, Object, Comparator)
     */
    public static <T> int binarySearch(List<? extends T> list, int fromIndex, int toIndex, T key,
        Comparator<? super T> comparator) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > list.size()) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }

        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            T midVal = list.get(mid);
            int cmp = comparator.compare(midVal, key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -(low + 1);
    }
}
