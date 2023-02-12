package cn.kingen.commons.data.validate.validator;

import cn.kingen.commons.data.Descriptors;
import cn.kingen.commons.data.InvalidValueException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Validates whether the values are valid URLs.
 *
 * @author Kingen
 */
@Slf4j
public class URLValidator extends AbstractParsableValidator<URL> {

    public URLValidator() {
        super(URL.class);
    }

    /**
     * Groups the urls by host.
     */
    @Override
    public void describe(List<URL> urls) {
        Descriptors.enumerate(urls, url -> {
            StringBuilder builder = new StringBuilder(url.toString().length()).append(url.getProtocol()).append("://")
                .append(url.getHost());
            if (-1 != url.getPort()) {
                builder.append(":").append(url.getPort());
            }
            return builder.toString();
        });
    }

    @Override
    protected URL parseText(String text) throws InvalidValueException {
        try {
            return new URL(text);
        } catch (MalformedURLException e) {
            throw new InvalidValueException(e);
        }
    }
}
