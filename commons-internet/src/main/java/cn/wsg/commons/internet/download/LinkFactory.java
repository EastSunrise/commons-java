package cn.wsg.commons.internet.download;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.function.FailableSupplier;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A factory to create links.
 *
 * @author Kingen
 */
@Slf4j
public final class LinkFactory {

    private LinkFactory() {
    }

    /**
     * Parses the given url as a {@code DownloadLink}.
     */
    public static DownloadLink parse(String url, Charset charset, String description) throws InvalidLinkException {
        return parse(url, charset, description, null);
    }

    /**
     * Parses the given url as a {@code DownloadLink}.
     */
    public static DownloadLink parse(String url, Charset charset, String description,
        FailableSupplier<String, InvalidPasswordException> passwordProvider) throws InvalidLinkException {
        String decoded = Objects.requireNonNull(url);
        Objects.requireNonNull(charset);
        try {
            decoded = Thunder.decodeThunder(decoded, charset);
        } catch (IllegalArgumentException e) {
            throw new InvalidLinkException("Not a valid thunder url " + decoded, e);
        }
        try {
            decoded = URLDecoder.decode(decoded, charset);
        } catch (IllegalArgumentException ignored) {
        }

        if (StringUtils.startsWithIgnoreCase(decoded, Ed2kLink.ED2K_PREFIX)) {
            return Ed2kLink.of(decoded, description);
        }
        if (StringUtils.startsWithIgnoreCase(decoded, MagnetLink.MAGNET_PREFIX)) {
            return MagnetLink.of(decoded, description);
        }
        if (StringUtils.startsWithIgnoreCase(decoded, YyetsLink.YYETS_PREFIX)) {
            return YyetsLink.of(decoded, description);
        }
        if (StringUtils.startsWithIgnoreCase(decoded, JianpianLink.JIANPIAN_PREFIX)) {
            return JianpianLink.of(decoded, description);
        }
        if (StringUtils.containsIgnoreCase(decoded, BaiduDiskLink.BAIDU_DISK_HOST)) {
            if (null != passwordProvider) {
                return BaiduDiskLink.of(decoded, passwordProvider.get(), description);
            }
            throw new InvalidPasswordException("No password is supplied for " + decoded);
        }
        if (StringUtils.containsIgnoreCase(decoded, ThunderDiskLink.THUNDER_DISK_HOST)) {
            if (null != passwordProvider) {
                return ThunderDiskLink.of(decoded, passwordProvider.get(), description);
            }
            throw new InvalidPasswordException("No password is provided for " + decoded);
        }
        if (StringUtils.containsIgnoreCase(decoded, UcDiskLink.UC_DISK_HOST)) {
            return UcDiskLink.of(decoded, description);
        }
        for (String prefix : HttpLink.HTTP_PREFIXES) {
            if (StringUtils.startsWithIgnoreCase(decoded, prefix)) {
                return HttpLink.of(decoded, description);
            }
        }
        throw new InvalidLinkException("Unknown type of link: " + decoded);
    }

    /**
     * Extract a password from the given strings.
     */
    public static String extractPassword(String... strings) {
        for (String str : strings) {
            Matcher matcher = Lazy.PASSWORD_REGEX.matcher(str);
            if (matcher.find()) {
                return matcher.group("p");
            }
        }
        return null;
    }

    private static class Lazy {
        private static final Pattern PASSWORD_REGEX = Pattern.compile("(提取码|密码)[:：]\\s?(?<p>\\w{4})");
    }
}
