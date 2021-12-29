package cn.wsg.commons.internet.support;

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.cache.HeaderConstants;
import org.apache.http.protocol.HttpContext;

/**
 * Customizes the cache strategy of the response.
 *
 * @author Kingen
 */
public class ResponseCacheControl implements HttpResponseInterceptor {

    @Override
    public void process(HttpResponse response, HttpContext context) {
        response.setHeader(HeaderConstants.CACHE_CONTROL, "max-age=604800");
    }
}
