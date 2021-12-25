package cn.wsg.commons.data;


/**
 * Represents a function that converts an object of unknown type to the target type.
 *
 * @param <R> target type that the value is converted to
 * @author Kingen
 */
public interface Converter<R> {

    /**
     * Converts the value to target type.
     *
     * @param value the value to be converted, must not be null
     * @return converted value of target type
     * @throws InvalidValueException if the given value is invalid
     */
    R convert(Object value) throws InvalidValueException;
}
