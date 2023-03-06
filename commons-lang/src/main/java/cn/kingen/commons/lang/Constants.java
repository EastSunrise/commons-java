package cn.kingen.commons.lang;

import java.time.format.DateTimeFormatter;

/**
 * Common constants.
 *
 * @author Kingen
 */
public final class Constants {

    // Common characters.

    public static final String COMMA = ",";

    // Common datetime formatters.

    public static final String PAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DTF_DATE_TIME = DateTimeFormatter.ofPattern(PAT_DATE_TIME);
}
