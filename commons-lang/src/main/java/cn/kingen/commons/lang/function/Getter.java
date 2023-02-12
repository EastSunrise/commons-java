package cn.kingen.commons.lang.function;

/**
 * Represents a getter to get the value of a property of a bean.
 *
 * @param <T> type of the bean from which the value is gotten
 * @param <V> type of the gotten value
 * @author Kingen
 */
@FunctionalInterface
public interface Getter<T, V> {

    /**
     * Gets the value of a property of the bean.
     *
     * @param bean the bean from which the value is gotten
     * @return gotten value
     */
    V get(T bean);
}
