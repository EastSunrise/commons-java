package cn.wsg.commons.internet.download;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A link of thunder disk.
 *
 * @author Kingen
 */
public final class ThunderDiskLink extends BaseDownloadLink implements EncryptedLink {

    static final String THUNDER_DISK_HOST = "pan.xunlei.com";

    private final String key;
    private final String password;

    private ThunderDiskLink(String description, String key, String password) {
        super(description);
        this.key = key;
        this.password = Objects.requireNonNull(password);
    }

    public static ThunderDiskLink of(String url, String password, String description) throws InvalidLinkException {
        if (!StringUtils.containsIgnoreCase(url, THUNDER_DISK_HOST)) {
            throw new InvalidLinkException(ThunderDiskLink.class, url);
        }
        if (password == null || !Lazy.PASSWORD_REGEX.matcher(password).matches()) {
            throw new InvalidPasswordException(ThunderDiskLink.class, password);
        }

        Matcher matcher = Lazy.URL_REGEX.matcher(url);
        if (matcher.matches()) {
            return new ThunderDiskLink(description, matcher.group("key"), password);
        }
        throw new InvalidLinkException(ThunderDiskLink.class, url);
    }

    @Override
    public String getUrl() {
        return String.format("https://pan.xunlei.com/s/%s", key);
    }

    @Override
    public String getPassword() {
        return password;
    }

    private static class Lazy {
        private static final Pattern PASSWORD_REGEX = Pattern.compile("[a-z0-9]{4}");
        private static final Pattern URL_REGEX = Pattern.compile("https://pan\\.xunlei\\.com/s/(?<key>[\\w-]{26})");
    }
}
