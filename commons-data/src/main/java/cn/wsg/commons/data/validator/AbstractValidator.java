package cn.wsg.commons.data.validator;

import cn.wsg.commons.data.Descriptor;
import cn.wsg.commons.data.InvalidValueException;
import cn.wsg.commons.data.Validator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a skeletal implementation of {@link Validator}.
 *
 * @author Kingen
 */
@Slf4j
public abstract class AbstractValidator<E> implements Validator<E> {

    private Descriptor<E>[] descriptors;

    protected AbstractValidator() {
        this.descriptors = null;
    }

    @Override
    public void validate(List<?> values) {
        int total = values.size();
        log.info("Total Amount: {}", total);
        List<Object> nonNulls = new ArrayList<>(values.size());
        for (Object value : values) {
            if (value != null) {
                nonNulls.add(value);
            }
        }
        log.warn("Count of nulls: {}", total - nonNulls.size());
        List<E> targets = new ArrayList<>(nonNulls.size());
        for (int i = 0; i < nonNulls.size(); i++) {
            Object nonNull = nonNulls.get(i);
            try {
                targets.add(convert(nonNull));
            } catch (InvalidValueException e) {
                log.error("{}. {}: {}", i, e.getMessage(), nonNull);
            }
        }
        log.warn("Count of invalid values: {}", nonNulls.size() - targets.size());
        log.info("Count of valid values: {}", targets.size());
        describe(targets);
        if (descriptors != null) {
            for (Descriptor<E> descriptor : descriptors) {
                descriptor.describe(targets);
            }
        }
    }

    /**
     * Default descriptor. Sets {@link #descriptors} if extra descriptors are required.
     *
     * @param values values to be described
     * @see #descriptors
     */
    @Override
    public abstract void describe(List<E> values);

    public Descriptor<E>[] getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(Descriptor<E>[] descriptors) {
        this.descriptors = descriptors;
    }
}
