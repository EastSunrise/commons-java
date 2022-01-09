package cn.wsg.commons.internet;

import cn.wsg.commons.internet.support.NotFoundException;
import cn.wsg.commons.internet.support.SiteHelper;
import cn.wsg.commons.internet.support.UnexpectedException;
import cn.wsg.commons.internet.support.WrappedStringResponseHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

/**
 * A basic implementation of a site, providing a default context to execute requests.
 * <p>
 * todo rebuild the connection after a long interval.
 *
 * @author Kingen
 */
@Slf4j
public class BaseSite implements SiteClient, Closeable {

    protected static final String METHOD_GET = HttpGet.METHOD_NAME;

    protected static final String METHOD_POST = HttpPost.METHOD_NAME;

    private final String name;

    private final HttpHost host;

    private final CloseableHttpClient client;

    private final HttpClientContext context;

    protected BaseSite(String name, HttpHost host) {
        this(name, host, SiteHelper.defaultClient(), SiteHelper.defaultContext());
    }

    protected BaseSite(String name, HttpHost host, CloseableHttpClient client, HttpClientContext context) {
        SiteHelper.validateStatus(getClass());
        this.name = name;
        this.host = Objects.requireNonNull(host);
        this.client = Objects.requireNonNull(client);
        this.context = context;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public HttpHost getHost() {
        return host;
    }

    @Override
    public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException {
        return client.execute(request, responseHandler, context);
    }

    @Override
    public <T> ResponseWrapper<T> getResponseWrapper(RequestBuilder builder, WrappedResponseHandler<T> responseHandler)
        throws IOException {
        return execute(builder.build(), responseHandler);
    }

    @Override
    public String getContent(RequestBuilder builder) throws IOException {
        WrappedStringResponseHandler handler = new WrappedStringResponseHandler();
        return getResponseWrapper(builder, handler).getContent();
    }

    /**
     * An extension of {@link #getContent} which returns the html content of the response as a
     * {@link Document} and splits the exception if thrown.
     *
     * @throws NotFoundException if the target document is not found
     * @see #findDocument
     */
    public Document getDocument(RequestBuilder builder) throws NotFoundException {
        try {
            return Jsoup.parse(getContent(builder));
        } catch (IOException e) {
            throw SiteHelper.handleException(e);
        }
    }

    /**
     * An extension of {@link #getContent} which returns the html content of the response as a
     * {@link Document}.
     * <p>
     * Different from the method {@link #getDocument}, this method will throw a runtime exception
     * instead of {@link NotFoundException} if the target document is not found.
     *
     * @see #getDocument
     */
    public Document findDocument(RequestBuilder builder) {
        try {
            return getDocument(builder);
        } catch (NotFoundException e) {
            throw new UnexpectedException(e);
        }
    }

    /**
     * An extension of {@link #getContent} which returns the json content of the response as a Java
     * object and splits the exception if thrown.
     *
     * @throws NotFoundException if the target object is not found
     */
    public <T> T getObject(RequestBuilder builder, ObjectMapper mapper, Class<? extends T> clazz)
        throws NotFoundException {
        try {
            return mapper.readValue(getContent(builder), clazz);
        } catch (IOException e) {
            throw SiteHelper.handleException(e);
        }
    }

    /**
     * An extension of {@link #getContent} which returns the json content of the response as a
     * <i>generic</i> Java object and splits the exception if thrown.
     *
     * @throws NotFoundException if the target object is not found
     */
    public <T> T getObject(RequestBuilder builder, ObjectMapper mapper, TypeReference<? extends T> type)
        throws NotFoundException {
        try {
            return mapper.readValue(getContent(builder), type);
        } catch (IOException e) {
            throw SiteHelper.handleException(e);
        }
    }

    @Override
    public void close() throws IOException {
        if (client != null) {
            client.close();
        }
    }

    /**
     * Returns a HttpGet request builder with the given path.
     *
     * @param args arguments to format the path
     */
    protected final RequestBuilder httpGet(String path, Object... args) {
        return create(null, METHOD_GET, path, args);
    }

    /**
     * Creates a http request builder of the specified method under the specified substation.
     *
     * @param args arguments to format the path
     */
    protected final RequestBuilder create(String substation, String method, String path, Object... args) {
        HttpHost target = host;
        if (StringUtils.isNotBlank(substation)) {
            String hostname = substation + "." + host.getHostName();
            target = new HttpHost(hostname, host.getPort(), host.getSchemeName());
        }
        if (args.length > 0) {
            path = String.format(path, args);
        }
        return RequestBuilder.create(method).setUri(target.toURI() + path);
    }

    protected final HttpClientContext getContext() {
        return context;
    }

    /**
     * Returns the cookie of the given name in current context.
     *
     * @param name name of the cookie to be queried
     * @return value of the cookie, may null
     */
    protected final Cookie getCookie(String name) {
        CookieStore cookieStore = context.getCookieStore();
        if (cookieStore == null) {
            return null;
        }
        for (Cookie cookie : cookieStore.getCookies()) {
            if (Objects.equals(cookie.getName(), name)) {
                return cookie;
            }
        }
        return null;
    }
}
