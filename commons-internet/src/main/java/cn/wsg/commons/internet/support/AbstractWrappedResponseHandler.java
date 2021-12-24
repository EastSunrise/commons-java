package cn.wsg.commons.internet.support;

import cn.wsg.commons.internet.ResponseWrapper;
import cn.wsg.commons.internet.WrappedResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * This class provides skeletal implementation of {@link WrappedResponseHandler}.
 *
 * @author Kingen
 * @see org.apache.http.impl.client.AbstractResponseHandler
 */
public abstract class AbstractWrappedResponseHandler<T> implements WrappedResponseHandler<T> {

    @Override
    public ResponseWrapper<T> handleResponse(HttpResponse response) throws IOException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= SiteUtils.MIN_ERROR_STATUS_CODE) {
            EntityUtils.consume(entity);
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }
        Header[] headers = response.getAllHeaders();
        if (entity == null) {
            return new BasicResponseWrapper<>(headers, null);
        }
        return new BasicResponseWrapper<>(headers, handleEntity(entity));
    }

    /**
     * Handles the response entity and transforms it into the actual response object.
     *
     * @param entity the entity to be handled
     * @return the actual object
     * @throws IOException if an I/O exception occurs when handling the entity
     */
    public abstract T handleEntity(HttpEntity entity) throws IOException;
}
