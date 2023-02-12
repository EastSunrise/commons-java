package cn.kingen.commons.data.download;

import cn.kingen.commons.lang.util.AssertUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * A link of http/https/ftp, except {@link BaiduDiskLink}, {@link UcDiskLink} or {@link ThunderDiskLink}.
 *
 * @author Kingen
 */
public final class HttpLink extends BaseDownloadLink {

    static final String[] HTTP_PREFIXES = {"https://", "http://", "ftp://"};

    private final URL url;

    private HttpLink(String description, URL url) {
        super(description);
        this.url = Objects.requireNonNull(url);
    }

    public static HttpLink of(String url, String description) throws InvalidLinkException {
        if (Arrays.stream(HTTP_PREFIXES).noneMatch(prefix -> StringUtils.startsWithIgnoreCase(url, prefix))) {
            throw new InvalidLinkException(HttpLink.class, url);
        }
        try {
            return new HttpLink(description, new URL(url));
        } catch (MalformedURLException e) {
            throw new InvalidLinkException(HttpLink.class, url, e);
        }
    }

    @Override
    public String getUrl() {
        return url.toString();
    }

    public String getFilename() {
        return AssertUtils.requireNotBlankElse(FilenameUtils.getName(url.getPath()), "index.html");
    }
}
