package cn.kingen.commons.internet.support;

import com.google.common.util.concurrent.RateLimiter;
import java.io.IOException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpExecutionAware;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.execchain.ClientExecChain;
import org.apache.http.protocol.HttpProcessor;

/**
 * The root request executor in the request execution chain that is responsible to add a rate limit
 * and process the request exactly before executing and the response before caching if enabled.
 *
 * @author Kingen
 */
@Slf4j
@SuppressWarnings("UnstableApiUsage")
public class RootClientExec implements ClientExecChain {

    private final RateLimiter limiter;
    private final ClientExecChain backend;
    private final HttpProcessor processor;

    public RootClientExec(double permitsPerSecond, ClientExecChain backend, HttpProcessor processor) {
        this.limiter = RateLimiter.create(permitsPerSecond);
        this.backend = Objects.requireNonNull(backend);
        this.processor = Objects.requireNonNull(processor);
    }

    @Override
    public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext context,
        HttpExecutionAware execAware) throws IOException, HttpException {
        processor.process(request, context);
        log.info("{}", execAware);
        limiter.acquire();
        CloseableHttpResponse response = backend.execute(route, request, context, execAware);
        processor.process(response, context);
        return response;
    }
}
