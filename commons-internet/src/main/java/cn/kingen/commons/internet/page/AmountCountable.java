package cn.kingen.commons.internet.page;

/**
 * Indicates that the amount of total elements is available.
 *
 * @author Kingen
 */
public interface AmountCountable {

    /**
     * Returns the total amount of elements.
     *
     * @return the total amount of elements
     */
    long getTotalElements();
}
