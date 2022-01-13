package cn.wsg.commons.internet.download;

import cn.wsg.commons.lang.AssertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A magnet link.
 *
 * @author Kingen
 */
public final class MagnetLink extends BaseDownloadLink {

    static final String MAGNET_PREFIX = "magnet:?";

    private final Set<String> topics;

    // nullable properties

    private final Set<String> names;
    private final Set<String> trackers;
    private final Set<Long> sizes;

    private MagnetLink(String desc, Set<String> topics, Set<String> names, Set<String> trackers, Set<Long> sizes) {
        super(desc);
        this.topics = AssertUtils.requireNotEmpty(topics, "topics of a magnet resource");
        this.names = names;
        this.trackers = trackers;
        this.sizes = sizes;
    }

    public static MagnetLink of(String url, String description) throws InvalidLinkException {
        if (!StringUtils.startsWithIgnoreCase(url, MAGNET_PREFIX)) {
            throw new InvalidLinkException(MagnetLink.class, url);
        }
        url = url.replace(" ", "");
        url = StringEscapeUtils.unescapeHtml4(url);
        Matcher xt = Lazy.XT_REGEX.matcher(url);
        Set<String> topics = new HashSet<>();
        while (xt.find()) {
            topics.add(xt.group("xt"));
        }
        if (topics.isEmpty()) {
            throw new InvalidLinkException(MagnetLink.class, url);
        }

        Matcher dns = Lazy.DN_REGEX.matcher(url);
        Set<String> names = new HashSet<>();
        while (dns.find()) {
            String dn = dns.group("dn");
            if (dn != null) {
                names.add(dn);
            }
        }
        Matcher trs = Lazy.TR_REGEX.matcher(url);
        Set<String> trackers = new HashSet<>();
        while (trs.find()) {
            String tr = trs.group("tr");
            if (tr != null) {
                trackers.add(tr);
            }
        }
        Matcher xl = Lazy.XL_REGEX.matcher(url);
        Set<Long> sizes = new HashSet<>();
        while (xl.find()) {
            sizes.add(Long.valueOf(xl.group("xl")));
        }
        return new MagnetLink(description, topics, names, trackers, sizes);
    }

    @Override
    public String getUrl() {
        StringBuilder builder = new StringBuilder(MAGNET_PREFIX);
        builder.append(topics.stream().map(s -> "xt=" + s).collect(Collectors.joining("&")));
        for (String name : names) {
            builder.append("&dn=").append(name);
        }
        for (String tracker : trackers) {
            builder.append("&tr=").append(tracker);
        }
        for (Long size : sizes) {
            builder.append("&xl=").append(size);
        }
        return builder.toString();
    }

    private static class Lazy {

        private static final String EQ = "(\\d+|\\.\\d+|\\+\\d+)?=";
        private static final String VAR = "[^&]*(&(?!(xl|tr|dn|xt)" + EQ + ")[^&]*)*";
        private static final Pattern XT_REGEX = Pattern.compile("(?<=[?&])xt" + EQ
            + "(?<xt>urn:(btih|tree:tiger|sha1|ed2k|aich|kzhash|md5):([0-9A-Za-z]{40}|[0-9A-Za-z]{32})(?!\\w))");
        private static final Pattern DN_REGEX = Pattern.compile("(?<=[?&])dn" + EQ + "(?<dn>" + VAR + ")");
        private static final Pattern TR_REGEX = Pattern.compile("(?<=[?&])tr" + EQ + "(?<tr>" + VAR + ")");
        private static final Pattern XL_REGEX = Pattern.compile("(?<=[?&])xl" + EQ + "(?<xl>\\d+)");
    }
}
