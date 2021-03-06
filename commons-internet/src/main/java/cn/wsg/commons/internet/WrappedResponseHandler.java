package cn.wsg.commons.internet;

import org.apache.http.client.ResponseHandler;

/**
 * A generic {@link ResponseHandler} that returns the response as a {@link ResponseWrapper}.
 *
 * @author Kingen
 * @see ResponseWrapper
 */
public interface WrappedResponseHandler<T> extends ResponseHandler<ResponseWrapper<T>> {

}
