package cn.kingen.commons.system.csv;

import cn.kingen.commons.lang.function.Setter;

/**
 * A function to convert a string and set it as a property of the bean.
 *
 * @param <T> type of the property to which the string is converted
 * @author Kingen
 */
@FunctionalInterface
public interface CsvSetter<T> extends Setter<T, String> {

    /**
     * Converts the given string and sets it as a property of the given bean.
     *
     * @param bean  the bean to which the value is set
     * @param value the value to be converted
     */
    @Override
    void set(T bean, String value);
}
