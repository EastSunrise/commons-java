package cn.wsg.commons.data.download;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A link of baidu disk.
 *
 * @author Kingen
 */
public final class BaiduDiskLink extends BaseDownloadLink implements EncryptedLink {

    static final String BAIDU_DISK_HOST = "pan.baidu.com";

    private final String url;

    private final String password;

    private BaiduDiskLink(String url, String password, String description) {
        super(description);
        this.url = Objects.requireNonNull(url);
        this.password = password;
    }

    public static BaiduDiskLink of(String url, String password, String desc) throws InvalidLinkException {
        if (!StringUtils.containsIgnoreCase(url, BAIDU_DISK_HOST)) {
            throw new InvalidLinkException(BaiduDiskLink.class, url);
        }
        if (password != null && !Lazy.PASSWORD_REGEX.matcher(password).matches()) {
            throw new InvalidPasswordException(BaiduDiskLink.class, password);
        }

        Matcher sMatcher = Lazy.URL_REGEX_S.matcher(url);
        if (sMatcher.find()) {
            String s = sMatcher.group("s");
            return new BaiduDiskLink(String.format("https://pan.baidu.com/share/init?surl=%s", s), password, desc);
        }
        Matcher shareMatcher = Lazy.URL_REGEX_SHARE.matcher(url);
        if (shareMatcher.find()) {
            return new BaiduDiskLink(shareMatcher.group(), password, desc);
        }
        throw new InvalidLinkException(BaiduDiskLink.class, url);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getPassword() {
        return password;
    }

    private static class Lazy {
        private static final Pattern URL_REGEX_S = Pattern
            .compile("(https?://)?pan\\.baidu\\.com(/s/1|/share/init\\?surl=)(?<s>[\\w-]{22}|\\w{4,7})(?![\\w-])");
        private static final Pattern URL_REGEX_SHARE = Pattern.compile(
            "(https?://)?pan\\.baidu\\.com/share/(link|init)\\?(shareid=\\d+&uk=\\d+|uk=\\d+&shareid=\\d+)(?!\\d)");
        private static final Pattern PASSWORD_REGEX = Pattern.compile("\\w{4}");
    }
}
