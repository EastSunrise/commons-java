package cn.kingen.commons.lang;

/**
 * Represents a supplier of an alphabetic abbreviation.
 *
 * @author Kingen
 */
@FunctionalInterface
public interface AbbrSupplier {

    /**
     * Returns the abbreviation.
     *
     * @return the abbreviation
     */
    String getAbbr();
}
