package cn.kingen.commons.lang;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The root class of common results.
 *
 * @author Kingen
 **/
@Getter
@ToString
@EqualsAndHashCode
public class Result<T> implements Serializable {

    public static final String OK = "OK";

    private final boolean success;
    private final String message;
    private final T data;

    protected Result(boolean success, String message, T data) {
        this.success = success;
        if (message == null || message.strip().length() == 0) {
            throw new IllegalArgumentException("Message of a result must not be blank");
        }
        this.message = message;
        this.data = data;
    }

    /**
     * Returns an ok result without data.
     *
     * @return an ok result
     */
    public static <T> Result<T> ok() {
        return new Result<>(true, OK, null);
    }

    /**
     * Returns an ok result with non-null data.
     *
     * @param data data within the result, must not be {@literal null}
     * @param <T>  type of data
     * @return an ok result with data
     */
    public static <T> Result<T> ok(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data of an ok result must not be null");
        }
        return new Result<>(true, OK, data);
    }

    /**
     * Returns an error result with a customized message.
     *
     * @param message customized message, must not be blank
     * @return an error result with a customized message
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(false, message, null);
    }

    /**
     * If this result is successful, apply the provided mapping function to it.
     *
     * @param <U>    The type of the result of the mapping function
     * @param mapper a mapping function to apply to the value, if successful
     * @return the result of applying a mapping function to the data of this result, if it is successful, otherwise an
     * error result
     * @throws NullPointerException if the mapping function is null
     */
    public <U> Result<? extends U> map(Function<? super T, Result<? extends U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isSuccess()) {
            return Result.error(message);
        } else {
            return mapper.apply(data);
        }
    }
}
