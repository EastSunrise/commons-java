package cn.wsg.commons.data;

import cn.wsg.commons.data.validator.*;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * A basic context which includes common validators and is able to add extended validators.
 *
 * @author Kingen
 */
public class BasicEntityValidatorContext implements EntityValidatorContext {

    private final Map<String, Validator<?>> propertyValidators = new HashMap<>(0);
    private final Map<Class<?>, Validator<?>> typeValidators = new HashMap<>(0);

    public BasicEntityValidatorContext() {
    }

    /**
     * Creates a basic context.
     */
    public static BasicEntityValidatorContext create() {
        return new BasicEntityValidatorContext();
    }

    /**
     * Sets a specific validator for the specific property.
     *
     * @param property  name of the property
     * @param validator the validator to be used
     * @return current context
     */
    public BasicEntityValidatorContext setValidator(String property, Validator<?> validator) {
        propertyValidators.put(property, validator);
        return this;
    }

    /**
     * Sets a specific validator for the specific type.
     *
     * @param type      type whose validator is to be set
     * @param validator the validator to be used
     * @return current context
     */
    public BasicEntityValidatorContext setValidator(Class<?> type, Validator<?> validator) {
        typeValidators.put(type, validator);
        return this;
    }

    @Override
    public Validator<?> getValidator(String property, Class<?> clazz) throws ValidatorNotFoundException {
        Validator<?> validator = propertyValidators.get(property);
        if (validator != null) {
            return validator;
        }
        validator = typeValidators.get(clazz);
        if (validator != null) {
            return validator;
        }
        validator = Lazy.DEFAULT_VALIDATORS.get(clazz);
        if (validator != null) {
            return validator;
        }
        throw new ValidatorNotFoundException("No validator is found for " + property + " or " + clazz);
    }

    @Override
    public Validator<?> handleException(Class<?> clazz, String property, Class<?> propertyType) {
        return new TypeValidator();
    }

    private static final class Lazy {

        private static final Map<Class<?>, Validator<?>> DEFAULT_VALIDATORS = new HashMap<>(16);

        static {
            DEFAULT_VALIDATORS.put(String.class, new StringValidator());
            DEFAULT_VALIDATORS.put(boolean.class, new BooleanValidator());
            DEFAULT_VALIDATORS.put(Boolean.class, new BooleanValidator());
            DEFAULT_VALIDATORS.put(int.class, new IntValidator());
            DEFAULT_VALIDATORS.put(Integer.class, new IntValidator());
            DEFAULT_VALIDATORS.put(long.class, new LongValidator());
            DEFAULT_VALIDATORS.put(Long.class, new LongValidator());
            DEFAULT_VALIDATORS.put(float.class, new FloatValidator());
            DEFAULT_VALIDATORS.put(Float.class, new FloatValidator());
            DEFAULT_VALIDATORS.put(double.class, new DoubleValidator());
            DEFAULT_VALIDATORS.put(Double.class, new DoubleValidator());
            DEFAULT_VALIDATORS.put(Duration.class, new DurationValidator());
            DEFAULT_VALIDATORS.put(LocalTime.class, new LocalTimeValidator());
            DEFAULT_VALIDATORS.put(LocalDate.class, new LocalDateValidator());
            DEFAULT_VALIDATORS.put(LocalDateTime.class, new LocalDateTimeValidator());
            DEFAULT_VALIDATORS.put(URL.class, new URLValidator());
        }
    }
}
