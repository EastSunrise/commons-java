package cn.wsg.commons.lang;

import java.time.format.DateTimeFormatter;

/**
 * Common datetime-related constants.
 *
 * @author Kingen
 */
public final class DatetimeConsts {

    /**
     * Common datetime formatters.
     */
    public static final DateTimeFormatter DTF_YYYY_MM_DD_HH_MM = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm");
    public static final String PAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DTF_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter
        .ofPattern(PAT_YYYY_MM_DD_HH_MM_SS);
}
