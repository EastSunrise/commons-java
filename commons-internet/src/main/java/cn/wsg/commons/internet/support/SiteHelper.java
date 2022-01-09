package cn.wsg.commons.internet.support;

import cn.wsg.commons.internet.BaseSite;
import cn.wsg.commons.internet.ConcreteSite;
import cn.wsg.commons.internet.ConcreteSite.SiteStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.cache.CachingHttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;

/**
 * Utility for site-related operations.
 *
 * @author Kingen
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SiteHelper {

    public static final int MIN_ERROR_STATUS_CODE = 300;
    public static final int DEFAULT_TIME_OUT = 30000;

    public static CloseableHttpClient defaultClient() {
        return CachingHttpClientBuilder.create().setHttpCacheStorage(new FileHttpCacheStorage())
            .setConnectionManager(new PoolingHttpClientConnectionManager()).build();
    }

    public static HttpClientContext defaultContext() {
        HttpClientContext clientContext = HttpClientContext.create();
        RequestConfig requestConfig =
            RequestConfig.custom().setConnectTimeout(DEFAULT_TIME_OUT).setSocketTimeout(DEFAULT_TIME_OUT).build();
        clientContext.setRequestConfig(requestConfig);
        return clientContext;
    }

    /**
     * Validate the status of the site based on the annotation {@link ConcreteSite}.
     *
     * @throws SiteStatusException if the status is abnormal
     */
    public static void validateStatus(Class<? extends BaseSite> clazz) {
        ConcreteSite annotation = clazz.getAnnotation(ConcreteSite.class);
        if (annotation != null) {
            SiteStatus status = annotation.status();
            if (SiteStatus.NORMAL != status) {
                throw new SiteStatusException(annotation);
            }
        }
    }

    /**
     * Finds a header of the specified name.
     */
    public static Header findHeader(Header[] headers, String name) {
        for (Header header : headers) {
            if (header.getName().equalsIgnoreCase(name)) {
                return header;
            }
        }
        throw new IllegalArgumentException("Can't find the header of " + name);
    }

    /**
     * Handles the exceptions when executing requests.
     *
     * @return NotFoundException if the status code is 404
     */
    public static UnexpectedException handleException(IOException e) throws NotFoundException {
        if (e instanceof HttpResponseException
            && ((HttpResponseException)e).getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            throw new NotFoundException(e.getMessage());
        }
        return new UnexpectedException(e);
    }
}
