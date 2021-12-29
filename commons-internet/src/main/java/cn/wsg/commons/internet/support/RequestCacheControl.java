package cn.wsg.commons.internet.support;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.cache.HeaderConstants;
import org.apache.http.protocol.HttpContext;

/**
 * Defines the cache strategy of the request.
 *
 * @author Kingen
 */
public class RequestCacheControl implements HttpRequestInterceptor {

    @Override
    public void process(HttpRequest request, HttpContext context) {
        request.setHeader(HeaderConstants.CACHE_CONTROL, "max-age=604800");
    }
}
