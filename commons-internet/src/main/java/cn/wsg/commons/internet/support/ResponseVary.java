package cn.wsg.commons.internet.support;

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.cache.HeaderConstants;
import org.apache.http.protocol.HttpContext;

/**
 * Removes the vary of the response.
 *
 * @author Kingen
 */
public class ResponseVary implements HttpResponseInterceptor {

    @Override
    public void process(HttpResponse response, HttpContext context) {
        response.removeHeaders(HeaderConstants.VARY);
    }
}
