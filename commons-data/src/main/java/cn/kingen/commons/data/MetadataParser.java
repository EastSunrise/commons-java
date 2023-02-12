package cn.kingen.commons.data;

import cn.kingen.commons.lang.util.AssertUtils;
import cn.kingen.commons.lang.util.EnumUtilExt;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * This class helps extract metadata from a table.
 *
 * @author Kingen
 */
@Slf4j
public final class MetadataParser {

    private final Map<String, List<String>> metadata;

    private String last;

    private MetadataParser(int capacity) {
        this.metadata = new HashMap<>(capacity);
    }

    public static MetadataParser create(int capacity) {
        return new MetadataParser(capacity);
    }

    /**
     * Associates the specified value with the specified key (or the last key). If the map previously contained a
     * mapping for the key, merge the two values.
     */
    public MetadataParser put(String key, String value) {
        AssertUtils.requireNotBlank(value);
        if (StringUtils.isBlank(key)) {
            if (last == null) {
                throw new UnsupportedOperationException("No previous key");
            }
            key = last;
        }
        List<String> preValue = metadata.get(key);
        if (preValue == null) {
            metadata.put(key, new ArrayList<>(List.of(value)));
        } else {
            preValue.add(value);
        }
        last = key;
        return this;
    }

    /**
     * Checks whether there is any unhandled metadata left.
     *
     * @param keys array of keys to be ignored when checking, each key must not be null
     * @throws IllegalArgumentException if there is any unexpected metadata unhandled
     */
    public void check(String... keys) {
        for (String key : keys) {
            metadata.remove(key);
        }
        if (!metadata.isEmpty()) {
            log.error("Unhandled metadata: {}", metadata.keySet());
        }
    }

    /**
     * Gets the value associated with one of the keys, converted with the specified function.
     */
    public <T> T getValue(Function<String, T> func, String... keys) {
        for (String key : keys) {
            List<String> values = metadata.remove(key);
            if (values.size() > 1) {
                throw new UnsupportedOperationException("More than one values are found. Use 'getValues' instead.");
            }
            String value = values.get(0);
            if (StringUtils.isNotBlank(value)) {
                try {
                    return func.apply(value);
                } catch (IllegalArgumentException | DateTimeException e) {
                    log.error("Cannot get value of {}: {}", key, e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * Gets the value as a string associated with one of the keys.
     */
    public String getString(String... keys) {
        return getValue(Function.identity(), keys);
    }

    /**
     * Gets the value as an enum associated with one of the keys.
     */
    public <E extends Enum<E>> E getEnum(Class<E> eClass, BiPredicate<String, E> p, String... keys) {
        return getValue(s -> EnumUtilExt.valueOf(eClass, s, p), keys);
    }

    /**
     * Gets values associated with the keys, ignoring {@code null}.
     */
    public <T> List<T> getValues(Function<String, T> func, String... keys) {
        List<T> ts = new ArrayList<>();
        for (String key : keys) {
            List<String> values = metadata.remove(key);
            for (String value : values) {
                if (StringUtils.isNotBlank(value)) {
                    try {
                        CollectionUtils.addIgnoreNull(ts, func.apply(value));
                    } catch (IllegalArgumentException | DateTimeException e) {
                        log.error("Cannot get values of {}: {}", key, e.getMessage());
                    }
                }
            }
        }
        return ts.isEmpty() ? null : ts;
    }

    /**
     * Gets values as strings associated with the keys, ignoring {@code null}.
     */
    public List<String> getStrings(String... keys) {
        return getValues(Function.identity(), keys);
    }

    /**
     * Gets values associated with the keys, separated with the specified chars.
     */
    public <T> List<T> getSeparatedValues(Function<? super String, T> func, String separatorChars, String... keys) {
        List<T> ts = new ArrayList<>();
        for (String key : keys) {
            List<String> values = metadata.remove(key);
            for (String value : values) {
                if (StringUtils.isNotBlank(value)) {
                    String[] parts = StringUtils.split(value, separatorChars);
                    for (String part : parts) {
                        try {
                            CollectionUtils.addIgnoreNull(ts, func.apply(part));
                        } catch (IllegalArgumentException | DateTimeException e) {
                            log.error("Cannot get separated values of {}: {}", key, e.getMessage());
                        }
                    }
                }
            }
        }
        return ts.isEmpty() ? null : ts;
    }

    /**
     * Gets values as strings associated with the keys, separated with the specified chars.
     */
    public List<String> getSeparatedStrings(String separatorChars, String... keys) {
        return getSeparatedValues(Function.identity(), separatorChars, keys);
    }
}
