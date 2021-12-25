package cn.wsg.commons.data.validator;

import cn.wsg.commons.data.Descriptors;
import cn.wsg.commons.data.InvalidValueException;
import cn.wsg.commons.lang.NetUtils;
import java.net.URL;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Validates whether the values are valid URLs.
 *
 * @author Kingen
 */
@Slf4j
public class URLValidator extends AbstractValidator<URL> {

    @Override
    public URL convert(Object value) throws InvalidValueException {
        if (value instanceof URL) {
            return (URL) value;
        }
        try {
            return NetUtils.createURL(value.toString());
        } catch (IllegalArgumentException e) {
            throw new InvalidValueException(e.getMessage());
        }
    }

    /**
     * Groups the urls by host.
     */
    @Override
    public void describe(List<URL> urls) {
        Descriptors.enumerate(urls, url -> {
            StringBuilder builder = new StringBuilder(url.toString().length())
                .append(url.getProtocol()).append("://")
                .append(url.getHost());
            if (-1 != url.getPort()) {
                builder.append(":").append(url.getPort());
            }
            return builder.toString();
        });
    }
}
