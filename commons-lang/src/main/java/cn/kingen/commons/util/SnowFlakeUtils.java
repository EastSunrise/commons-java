package cn.kingen.commons.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility to generate a unique id using Snowflake algorithm.
 *
 * @author Sigen Wang
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SnowFlakeUtils {

    private static final long SEQUENCE_BITS = 10;
    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;
    private static final char[] OPTIONS;

    private static long lastMillis = -1L;
    private static long sequence;

    static {
        OPTIONS = new char[]{'-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '_'};
    }

    /**
     * Generates a unique numeric id concatenating the timestamp since {@link java.time.Instant#EPOCH} and an auto
     * increment sequence. Ids between 2000-12-11T19:21:40Z and 2279-06-18T01:36:40Z are 16-digits long.
     */
    public static synchronized long generateId() {
        long currentMillis = System.currentTimeMillis();
        if (currentMillis == lastMillis) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
        } else {
            sequence = 0;
        }
        lastMillis = currentMillis;
        return (currentMillis << SEQUENCE_BITS) | sequence;
    }

    /**
     * Generates a unique id and converts it to a base64 string. Ids between 1978-09-17T10:58:26.944Z and
     * 2527-06-23T06:20:44.416Z are 9-chars long.
     */
    public static String generateBase64Id() {
        long id = generateId();
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(OPTIONS[(int) (id & 63)]);
            id >>= 6;
        }
        return sb.reverse().toString();
    }
}
