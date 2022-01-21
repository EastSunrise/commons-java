package cn.wsg.commons.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * Utilities for {@link java.net}.
 *
 * @author Kingen
 */
public final class NetUtils {

    private NetUtils() {
    }

    /**
     * Return the host URI, as a string, like <i>https://www.example.com/</i>.
     *
     * @return the host URI
     */
    public static String getHostURI(URL url) {
        StringBuilder builder = new StringBuilder(url.getProtocol());
        builder.append("://");
        builder.append(url.getHost());
        if (-1 != url.getPort()) {
            builder.append(":");
            builder.append(url.getPort());
        }
        return builder.toString();
    }

    /**
     * Creates a {@link URL}.
     *
     * @param url the string of the url
     * @return a url
     * @throws NullPointerException     if the string of url is null
     * @throws IllegalArgumentException if an exception is thrown
     * @see URL#URL(String)
     */
    public static URL createURL(String url) {
        Objects.requireNonNull(url, "the string of the url");
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}
