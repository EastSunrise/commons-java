package cn.wsg.commons.data.download;

import cn.wsg.commons.util.AssertUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A jianpian link.
 *
 * @author Kingen
 */
public class JianpianLink extends BaseDownloadLink {

    static final String JIANPIAN_PREFIX = "jianpian://";

    private final PathType pathtype;
    private final String path;

    private JianpianLink(String description, PathType pathtype, String path) {
        super(description);
        this.pathtype = Objects.requireNonNull(pathtype);
        this.path = AssertUtils.requireNotBlank(path);
    }

    public static JianpianLink of(String url, String description) {
        if (!StringUtils.startsWithIgnoreCase(url, JIANPIAN_PREFIX)) {
            throw new InvalidLinkException(JianpianLink.class, url);
        }
        Matcher matcher = Lazy.URL_REGEX.matcher(url);
        if (matcher.matches()) {
            PathType type = PathType.valueOf(matcher.group("t").toUpperCase());
            return new JianpianLink(description, type, matcher.group("p"));
        }
        throw new InvalidLinkException(JianpianLink.class, url);
    }

    @Override
    public String getUrl() {
        return String.format("jianpian://pathtype=%s&path=%s", pathtype.name().toLowerCase(), path);
    }

    private enum PathType {
        URL
    }

    private static class Lazy {
        private static final Pattern URL_REGEX;

        static {
            String types = Arrays.stream(PathType.values()).map(Enum::name).map(String::toLowerCase)
                .collect(Collectors.joining("|"));
            URL_REGEX = Pattern.compile("jianpian://pathtype=(?<t>" + types + ")&path=(?<p>[^&]+)");
        }
    }
}
