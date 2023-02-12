package cn.kingen.commons.system.cmd.ffmpeg;

import cn.kingen.commons.lang.DatetimeConsts;
import cn.kingen.commons.lang.jackson.JsonDurationFormat;
import cn.kingen.commons.lang.jackson.JsonJoinedValue;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

/**
 * Common tags contained in a stream.
 *
 * @author Kingen
 */
@Getter
public class BaseTags {

    @JsonProperty("title")
    private String title;

    @JsonProperty("language")
    @JsonAlias("LANGUAGE")
    private String language;

    @JsonProperty("BPS")
    private Long bps;

    @JsonProperty("BPS-eng")
    private Long bpsEng;

    @JsonProperty("DURATION")
    @JsonDurationFormat(format = JsonDurationFormat.Format.DURATION)
    private Duration duration;

    @JsonProperty("DURATION-eng")
    @JsonDurationFormat(format = JsonDurationFormat.Format.DURATION)
    private Duration durationEng;

    @JsonProperty("NUMBER_OF_FRAMES")
    private Long numberOfFrames;

    @JsonProperty("NUMBER_OF_FRAMES-eng")
    private Long numberOfFramesEng;

    @JsonProperty("NUMBER_OF_BYTES")
    private Long numberOfBytes;

    @JsonProperty("NUMBER_OF_BYTES-eng")
    private Long numberOfBytesEng;

    @JsonProperty("_STATISTICS_WRITING_APP")
    private String statisticsWritingApp;

    @JsonProperty("_STATISTICS_WRITING_APP-eng")
    private String statisticsWritingAppEng;

    @JsonProperty("_STATISTICS_WRITING_DATE_UTC")
    @JsonFormat(pattern = DatetimeConsts.PAT_YYYY_MM_DD_HH_MM_SS, timezone = "utc")
    private LocalDateTime statisticsWritingDateUtc;

    @JsonProperty("_STATISTICS_WRITING_DATE_UTC-eng")
    @JsonFormat(pattern = DatetimeConsts.PAT_YYYY_MM_DD_HH_MM_SS, timezone = "utc")
    private LocalDateTime statisticsWritingDateUtcEng;

    @JsonProperty("_STATISTICS_TAGS")
    @JsonJoinedValue(separator = " ")
    private List<String> statisticsTags;

    @JsonProperty("_STATISTICS_TAGS-eng")
    @JsonJoinedValue(separator = " ")
    private List<String> statisticsTagsEng;

    BaseTags() {
    }
}
