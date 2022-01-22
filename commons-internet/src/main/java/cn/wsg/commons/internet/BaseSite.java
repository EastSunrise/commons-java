package cn.wsg.commons.internet;

import cn.wsg.commons.internet.support.SiteHelper;
import cn.wsg.commons.util.AssertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Kingen
 */
public class BaseSite implements Closeable {

    protected static final String METHOD_GET = HttpGet.METHOD_NAME;
    protected static final String METHOD_POST = HttpPost.METHOD_NAME;

    private final String name;

    private final HttpHost host;

    public BaseSite(String name, HttpHost host) {
        SiteHelper.validateStatus(getClass());
        this.name = AssertUtils.requireNotBlank(name);
        this.host = Objects.requireNonNull(host);
    }

    public String getName() {
        return name;
    }

    public HttpHost getHost() {
        return host;
    }

    @Override
    public void close() throws IOException {
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
}
