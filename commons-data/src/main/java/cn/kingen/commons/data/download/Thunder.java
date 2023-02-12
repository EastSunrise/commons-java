package cn.kingen.commons.data.download;

import cn.kingen.commons.lang.util.RegExUtilsExt;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * Utility for thunder.
 *
 * @author Kingen
 * @see <a href="https://www.xunlei.com/">Thunder</a>
 */
public final class Thunder {

    static final String THUNDER_PREFIX = "thunder://";
    private static final String EMPTY_LINK = "thunder://QUFaWg==";

    private Thunder() {
    }

    /**
     * Returns {@code true} if the url represents an empty url.
     */
    public static boolean isEmptyLink(String url) {
        return EMPTY_LINK.equals(url);
    }

    /**
     * Encode a url to a thunder url.
     */
    public static String encodeThunder(String url) {
        if (StringUtils.startsWithIgnoreCase(url, THUNDER_PREFIX)) {
            return url;
        }
        byte[] bytes = ("AA" + url + "ZZ").getBytes(StandardCharsets.UTF_8);
        return THUNDER_PREFIX + Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Decode a thunder to a common url.
     */
    public static String decodeThunder(String url, Charset charset) {
        String decoded = url.strip();
        while (StringUtils.startsWithIgnoreCase(decoded, THUNDER_PREFIX)) {
            decoded = StringUtils.replace(decoded, "%2b", "+");
            decoded = StringUtils.replace(decoded, "%20", "+");
            decoded = StringUtils.replace(decoded, "%3D", "=");
            decoded = RegExUtilsExt.matchesOrElseThrow(Lazy.THUNDER_REGEX, decoded).group("c");
            decoded = new String(Base64.getDecoder().decode(decoded.getBytes(charset)), charset);
            decoded = RegExUtilsExt.matchesOrElseThrow(Lazy.SRC_URL_REGEX, decoded).group("u").strip();
        }
        return decoded;
    }

    private static class Lazy {
        private static final Pattern THUNDER_REGEX =
            Pattern.compile("thunder://(?<c>([\\w+/-]{4})+([\\w+/-]{2}[\\w+/-=]=)?)", Pattern.CASE_INSENSITIVE);
        private static final Pattern SRC_URL_REGEX = Pattern.compile("AA\\s*(?<u>.*)\\s*ZZ");
    }
}
