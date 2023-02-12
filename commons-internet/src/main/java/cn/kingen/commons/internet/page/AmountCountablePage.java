package cn.kingen.commons.internet.page;

/**
 * An extension of {@link Page} whose total amount of elements is available.
 *
 * @author Kingen
 */
public interface AmountCountablePage<T> extends Page<T>, PageCountable, AmountCountable {

}
