package cn.wsg.commons.internet.support;

import cn.wsg.commons.internet.WrappedResponseHandler;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * An implementation of {@link WrappedResponseHandler} that assumes the content of the response is a
 * string and then returns the string and the headers.
 *
 * @author Kingen
 */
public class WrappedStringResponseHandler extends AbstractWrappedResponseHandler<String> {

    private final Charset charset;

    public WrappedStringResponseHandler(Charset charset) {
        this.charset = charset;
    }

    public WrappedStringResponseHandler() {
        this.charset = StandardCharsets.UTF_8;
    }

    @Override
    public String handleEntity(HttpEntity entity) throws IOException {
        return EntityUtils.toString(entity, charset);
    }
}
