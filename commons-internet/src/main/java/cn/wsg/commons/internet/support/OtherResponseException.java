package cn.wsg.commons.internet.support;

import org.apache.http.client.HttpResponseException;

/**
 * Exceptions thrown when the status code isn't 404.
 *
 * @author Kingen
 */
public class OtherResponseException extends HttpResponseException {

    private static final long serialVersionUID = -525774900924868242L;

    public OtherResponseException(HttpResponseException e) {
        super(e.getStatusCode(), e.getReasonPhrase());
    }

    public OtherResponseException(int statusCode, String reasonPhrase) {
        super(statusCode, reasonPhrase);
    }
}
