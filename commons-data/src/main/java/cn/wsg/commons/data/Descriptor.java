package cn.wsg.commons.data;

import java.util.List;

/**
 * Represents a function to describe the features of given values.
 *
 * @param <T> type of values to be described
 * @author Kingen
 */
public interface Descriptor<T> {

    /**
     * Describes the features of the given values.
     *
     * @param values values to be described
     */
    void describe(List<T> values);
}
