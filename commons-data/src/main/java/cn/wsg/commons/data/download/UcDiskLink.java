package cn.wsg.commons.data.download;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A link of UC disk.
 *
 * @author Kingen
 */
public final class UcDiskLink extends BaseDownloadLink {

    static final String UC_DISK_HOST = "yun.cn";

    private final String key;
    private final String ck;

    private UcDiskLink(String description, String key, String ck) {
        super(description);
        this.key = Objects.requireNonNull(key);
        this.ck = ck;
    }

    public static UcDiskLink of(String url, String description) throws InvalidLinkException {
        if (!StringUtils.containsIgnoreCase(url, UC_DISK_HOST)) {
            throw new InvalidLinkException(UcDiskLink.class, url);
        }
        Matcher matcher = Lazy.UC_REGEX.matcher(url);
        if (matcher.lookingAt()) {
            return new UcDiskLink(description, matcher.group("key"), matcher.group("ck"));
        }
        throw new InvalidLinkException(UcDiskLink.class, url);
    }

    @Override
    public String getUrl() {
        String url = String.format("https://www.yun.cn/s/%s", key);
        if (ck != null) {
            url += "?chkey=" + ck;
        }
        return url;
    }

    private static class Lazy {
        private static final Pattern UC_REGEX =
            Pattern.compile("https://www\\.yun\\.cn/s/(?<key>[0-9a-z]{32})(\\?chkey=(?<ck>[0-9a-z]{5}))?");

    }
}
