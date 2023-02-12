package cn.kingen.commons.data.download;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * An ed2k link.
 *
 * @author Kingen
 */
public final class Ed2kLink extends BaseDownloadLink {

    static final String ED2K_PREFIX = "ed2k://";

    private final String filename;
    private final long size;
    private final String hash;

    // nullable properties

    private final String rootHash;
    private final String partHash;
    private final String href;
    private final String sources;

    private Ed2kLink(String desc, String filename, long size, String hash, String rootHash, String partHash,
        String href, String sources) {
        super(desc);
        this.filename = Objects.requireNonNull(filename);
        this.size = size;
        this.hash = Objects.requireNonNull(hash);
        this.rootHash = rootHash;
        this.partHash = partHash;
        this.href = href;
        this.sources = sources;
    }

    public static Ed2kLink of(String url, String description) throws InvalidLinkException {
        if (!StringUtils.startsWithIgnoreCase(url, ED2K_PREFIX)) {
            throw new InvalidLinkException(Ed2kLink.class, url);
        }
        url = url.replace(" ", "");
        Matcher matcher = Lazy.ED2K_REGEX.matcher(url);
        if (!matcher.lookingAt()) {
            throw new InvalidLinkException(Ed2kLink.class, url);
        }
        String name = matcher.group("name");
        long size = Long.parseLong(matcher.group("size"));
        String hash = matcher.group("hash");
        String rootHash = matcher.group("h");
        String partHash = matcher.group("p");
        String href = matcher.group("s");
        String sources = matcher.group("sources");
        return new Ed2kLink(description, name, size, hash, rootHash, partHash, href, sources);
    }

    @Override
    public String getUrl() {
        StringBuilder builder = new StringBuilder("ed2k://|file");
        builder.append("|").append(filename).append("|").append(size).append("|").append(hash);
        if (rootHash != null) {
            builder.append("|h=").append(rootHash);
        }
        if (partHash != null) {
            builder.append("|p=").append(partHash);
        }
        if (href != null) {
            builder.append("|s=").append(href);
        }
        builder.append("|/");
        if (sources != null) {
            builder.append("|sources,").append(sources).append("|/");
        }
        return builder.toString();
    }

    public String getFilename() {
        return filename;
    }

    public long length() {
        return size;
    }

    private static class Lazy {
        private static final Pattern ED2K_REGEX = Pattern.compile(
            "ed2k://\\|file\\|(?<name>[^|]+)\\|(?<size>\\d+)\\|(?<hash>[0-9A-Za-z]{32})"
                + "(\\|h=(?<h>[0-9A-Za-z]{32})|\\|p=(?<p>[0-9A-Za-z]{32}(:[0-9A-Za-z]{32})*)|\\|s=(?<s>[^|]+))*"
                + "(\\|/\\|sources,(?<sources>\\d{1,3}(\\.\\d{1,3}){3}:\\d{1,5})\\|/|(\\|/.*)|(\\|/\\|/)|(\\|)|)",
            Pattern.CASE_INSENSITIVE);
    }
}
