package cn.wsg.commons.data.schema.item;

/**
 * A web page. Every web page is implicitly assumed to be declared to be of type WebPage, so the various properties
 * about that webpage, such as
 * <code>breadcrumb</code> may be used. We recommend explicit declaration if these properties are specified, but if they
 * are found outside of an itemscope, they
 * will be assumed to be about the page.
 *
 * @author Kingen
 */
public interface WebPage extends CreativeWork {
}