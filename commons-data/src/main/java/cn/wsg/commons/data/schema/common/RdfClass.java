package cn.wsg.commons.data.schema.common;

/**
 * Indicates that the annotated type is a RDF class.
 *
 * @author Kingen
 */
public @interface RdfClass {

    /**
     * The property that determines that two given classes are equivalent, and that is used to specify datatype
     * definitions.
     */
    String[] equivalentClass() default {};
}
