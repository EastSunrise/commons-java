package cn.kingen.commons.internet;

import cn.kingen.commons.internet.support.NotFoundException;
import cn.kingen.commons.internet.support.SiteHelper;
import cn.kingen.commons.internet.support.UnexpectedException;
import cn.kingen.commons.internet.support.WrappedStringResponseHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * A basic implementation of {@link SiteClient}, providing a default context to execute requests.
 * <p>
 * todo rebuild the connection after a long interval.
 *
 * @author Kingen
 */
@Slf4j
public class BaseSiteClient extends BaseSite implements SiteClient {

    private final CloseableHttpClient client;

    private final HttpClientContext context;

    protected BaseSiteClient(String name, HttpHost host) {
        this(name, host, SiteHelper.defaultClient(), SiteHelper.defaultContext());
    }

    protected BaseSiteClient(String name, HttpHost host, CloseableHttpClient client, HttpClientContext context) {
        super(name, host);
        this.client = Objects.requireNonNull(client);
        this.context = context;
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
