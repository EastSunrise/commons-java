package cn.kingen.commons.internet;

import org.apache.http.Header;

/**
 * An object returned by {@link SiteClient#getResponseWrapper}, including the headers of the response
 * and the content of the response.
 *
 * @param <T> real type of the content of the response
 * @author Kingen
 */
public interface ResponseWrapper<T> {

    /**
     * Returns the headers of the response.
     *
     * @return the headers
     */
    Header[] getHeaders();

    /**
     * Returns the content of the response as an object.
     *
     * @return the content of the response
     */
    T getContent();
}
