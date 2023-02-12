package cn.kingen.commons.internet.support;

import cn.kingen.commons.internet.ResponseWrapper;
import org.apache.http.Header;

/**
 * A basic implementation of {@link ResponseWrapper}.
 *
 * @author Kingen
 */
public class BasicResponseWrapper<T> implements ResponseWrapper<T> {

    private final Header[] headers;

    private final T content;

    public BasicResponseWrapper(Header[] headers, T content) {
        this.headers = headers;
        this.content = content;
    }

    @Override
    public Header[] getHeaders() {
        return headers;
    }

    @Override
    public T getContent() {
        return content;
    }
}
