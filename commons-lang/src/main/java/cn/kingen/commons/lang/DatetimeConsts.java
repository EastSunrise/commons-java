package cn.kingen.commons.lang;

import java.time.format.DateTimeFormatter;

/**
 * Common datetime-related constants.
 *
 * @author Kingen
 */
public final class DatetimeConsts {

    public static final int SECONDS_PER_MINUTE = 60;
    public static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * 60;
    public static final int SECONDS_PER_DAY = SECONDS_PER_HOUR * 24;
    public static final int SECONDS_PER_WEEK = SECONDS_PER_DAY * 7;

    /**
     * Common datetime formatters.
     */
    public static final DateTimeFormatter DTF_YYYY_MM_DD_HH_MM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final String PAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String PAT_ISO_LOCAL_DATE = "yyyy-MM-dd";
    public static final DateTimeFormatter DTF_YYYY_MM_DD_HH_MM_SS =
        DateTimeFormatter.ofPattern(PAT_YYYY_MM_DD_HH_MM_SS);
}
