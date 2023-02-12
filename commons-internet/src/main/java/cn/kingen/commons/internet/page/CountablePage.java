package cn.kingen.commons.internet.page;

/**
 * An extension of {@link Page} whose number of total pages is available.
 *
 * @author Kingen
 */
public interface CountablePage<T> extends Page<T>, PageCountable {

}
