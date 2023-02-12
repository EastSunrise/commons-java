package cn.kingen.commons.internet.support;

import java.util.LinkedList;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.impl.client.cache.CachingHttpClientBuilder;
import org.apache.http.impl.execchain.ClientExecChain;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;

/**
 * The builder for {@link org.apache.http.impl.client.CloseableHttpClient} instances capable of rate
 * limiting and processor.
 *
 * @author Kingen
 */
public class RootHttpClientBuilder extends CachingHttpClientBuilder {

    private static final double DEFAULT_PERMITS_PER_SECOND = 1.0;
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36 Edg/96.0.1054.62";

    private double permitsPerSecond = DEFAULT_PERMITS_PER_SECOND;
    private LinkedList<HttpRequestInterceptor> requestBefore;
    private LinkedList<HttpRequestInterceptor> requestAfter;
    private LinkedList<HttpResponseInterceptor> responseBefore;
    private LinkedList<HttpResponseInterceptor> responseAfter;

    protected RootHttpClientBuilder() {
    }

    public static RootHttpClientBuilder create() {
        RootHttpClientBuilder builder = new RootHttpClientBuilder();
        builder.setUserAgent(USER_AGENT);
        return builder;
    }

    /**
     * Defines the rate limit of the client.
     *
     * @param permitsPerSecond limit of requests per second
     */
    public final RootHttpClientBuilder setRateLimiter(double permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
        return this;
    }

    /**
     * @see #addInterceptorFirst(HttpRequestInterceptor)
     */
    public final RootHttpClientBuilder addInterceptorBefore(HttpRequestInterceptor interceptor) {
        if (interceptor == null) {
            return this;
        }
        if (requestBefore == null) {
            requestBefore = new LinkedList<>();
        }
        requestBefore.addFirst(interceptor);
        return this;
    }

    /**
     * @see #addInterceptorLast(HttpRequestInterceptor)
     */
    public final RootHttpClientBuilder addInterceptorAfter(HttpRequestInterceptor interceptor) {
        if (interceptor == null) {
            return this;
        }
        if (requestAfter == null) {
            requestAfter = new LinkedList<>();
        }
        requestAfter.addLast(interceptor);
        return this;
    }

    /**
     * @see #addInterceptorFirst(HttpResponseInterceptor)
     */
    public final RootHttpClientBuilder addInterceptorBefore(HttpResponseInterceptor interceptor) {
        if (interceptor == null) {
            return this;
        }
        if (responseBefore == null) {
            responseBefore = new LinkedList<>();
        }
        responseBefore.addFirst(interceptor);
        return this;
    }

    /**
     * @see #addInterceptorLast(HttpResponseInterceptor)
     */
    public final RootHttpClientBuilder addInterceptorAfter(HttpResponseInterceptor interceptor) {
        if (interceptor == null) {
            return this;
        }
        if (responseAfter == null) {
            responseAfter = new LinkedList<>();
        }
        responseAfter.addLast(interceptor);
        return this;
    }

    @Override
    protected ClientExecChain decorateMainExec(ClientExecChain mainExec) {
        final HttpProcessorBuilder pb = HttpProcessorBuilder.create();
        if (requestBefore != null) {
            for (final HttpRequestInterceptor i : requestBefore) {
                pb.addFirst(i);
            }
        }
        if (responseBefore != null) {
            for (final HttpResponseInterceptor i : responseBefore) {
                pb.addFirst(i);
            }
        }
        if (requestAfter != null) {
            for (final HttpRequestInterceptor i : requestAfter) {
                pb.addLast(i);
            }
        }
        if (responseAfter != null) {
            for (final HttpResponseInterceptor i : responseAfter) {
                pb.addLast(i);
            }
        }
        HttpProcessor processor = pb.build();

        RootClientExec exec = new RootClientExec(permitsPerSecond, mainExec, processor);
        return super.decorateMainExec(exec);
    }
}
