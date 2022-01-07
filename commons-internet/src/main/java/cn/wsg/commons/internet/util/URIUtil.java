package cn.wsg.commons.internet.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Kingen
 */
public final class URIUtil {

    /**
     * Returns the value of the first parameter of the name in the uri.
     */
    public static String getQueryValue(String uri, String name) {
        List<NameValuePair> params = new URIBuilder(URI.create(uri)).getQueryParams();
        for (NameValuePair param : params) {
            if (param.getName().equals(name)) {
                return param.getValue();
            }
        }
        throw new NoSuchElementException(name);
    }
}
