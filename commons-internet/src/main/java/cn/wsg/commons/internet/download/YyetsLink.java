package cn.wsg.commons.internet.download;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A yyets link.
 *
 * @author Kingen
 */
public final class YyetsLink extends BaseDownloadLink {

    static final String YYETS_PREFIX = "yyets://";

    private final String name;
    private final long size;
    private final String hash;

    private YyetsLink(String description, String name, long size, String hash) {
        super(description);
        this.name = name;
        this.size = size;
        this.hash = hash;
    }

    public static YyetsLink of(String url, String description) throws InvalidLinkException {
        if (!StringUtils.startsWithIgnoreCase(url, YYETS_PREFIX)) {
            throw new InvalidLinkException(YyetsLink.class, url);
        }
        Matcher matcher = Lazy.YYETS_REGEX.matcher(url);
        if (!matcher.matches()) {
            throw new InvalidLinkException(YyetsLink.class, url);
        }
        long size = Long.parseLong(matcher.group("size"));
        return new YyetsLink(description, matcher.group("name"), size, matcher.group("hash"));
    }

    @Override
    public String getUrl() {
        return String.format("yyets://N=%s|S=%d|H=%s", name, size, hash);
    }

    public String getFilename() {
        return name;
    }

    public long length() {
        return size;
    }

    private static class Lazy {

        private static final Pattern YYETS_REGEX = Pattern
            .compile("yyets://N=(?<name>[^|]+)\\|S=(?<size>\\d+)\\|H=(?<hash>[0-9A-Za-z]{40})\\|",
                Pattern.CASE_INSENSITIVE);
    }
}
