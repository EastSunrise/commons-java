package cn.kingen.commons.lang;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
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

    public static final int SUCCESS_CODE = 0;

    private final int code;
    private final String message;
    private final T data;

    protected Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * Returns an ok result without data.
     *
     * @return an ok result
     */
    public static <T> Result<T> ok() {
        return new Result<>(SUCCESS_CODE, "", null);
    }

    /**
     * Returns an ok result with non-null data.
     *
     * @param data data within the result
     * @param <T>  type of data
     * @return an ok result with data
     */
    public static <T> Result<T> ok(@NonNull T data) {
        return new Result<>(SUCCESS_CODE, "", data);
    }

    /**
     * Returns an error result with a specific code and a customized message.
     *
     * @return an error result
     */
    public static <T> Result<T> error(int code, String message) {
        if (code == SUCCESS_CODE) {
            throw new IllegalArgumentException("code must not be zero.");
        }
        return new Result<>(code, message, null);
    }

    /**
     * Returns an error result copying the source result.
     *
     * @return a copied result
     */
    public static <T> Result<T> error(Result<?> source) {
        return Result.error(source.getCode(), source.getMessage());
    }
}
