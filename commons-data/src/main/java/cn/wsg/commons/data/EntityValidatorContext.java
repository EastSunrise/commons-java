package cn.wsg.commons.data;

/**
 * The context when validating entities.
 *
 * @author Kingen
 */
public interface EntityValidatorContext {

    /**
     * Gets a validator for this context by the specific property and its type.
     *
     * @param property name of the property
     * @param clazz    type of the property
     * @return the validator if found or null if not found
     * @throws ValidatorNotFoundException if no validator is found
     */
    Validator<?> getValidator(String property, Class<?> clazz) throws ValidatorNotFoundException;

    /**
     * Handles when no validator is found in this context.
     *
     * @param clazz        type of entities
     * @param property     the unexpected property
     * @param propertyType type of the unexpected property
     * @return the validator
     */
    Validator<?> handleException(Class<?> clazz, String property, Class<?> propertyType);
}
