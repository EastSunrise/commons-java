package cn.wsg.commons.lang;

import java.util.Collection;

/**
 * Extension of {@link org.apache.commons.collections4.CollectionUtils}.
 *
 * @author Kingen
 */
public final class CollectionUtilsExt {

    public static int[] toIntArray(Collection<Integer> collection) {
        int[] arr = new int[collection.size()];
        int i = 0;
        for (int value : collection) {
            arr[i++] = value;
        }
        return arr;
    }
}
