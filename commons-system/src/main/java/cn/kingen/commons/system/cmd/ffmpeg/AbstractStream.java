package cn.kingen.commons.system.cmd.ffmpeg;

import cn.kingen.commons.convert.JsonDurationFormat;
import cn.kingen.commons.convert.JsonJoinedValue;
import cn.kingen.commons.lang.Constants;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import org.apache.commons.lang3.math.Fraction;

/**
 * This class provides a skeleton implementation of the information about each media stream contained in the input
 * multimedia stream.
 *
 * @author Kingen
 */
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "codec_type")
public abstract class AbstractStream implements Serializable {

    @JsonProperty(value = "index", required = true)
    private int index;

    @JsonProperty(value = "codec_name", required = true)
    private String codecName;

    @JsonProperty(value = "codec_long_name", required = true)
    private String codecLongName;

    @JsonProperty(value = "codec_tag_string", required = true)
    private String codecTagString;

    @JsonProperty(value = "codec_tag", required = true)
    private String codecTag;

    @JsonProperty(value = "r_frame_rate", required = true)
    private Fraction rFrameRate;

    @JsonProperty(value = "avg_frame_rate", required = true)
    private Fraction averageFrameRate;

    @JsonProperty(value = "time_base", required = true)
    private Fraction timeBase;

    @JsonProperty(value = "start_pts", required = true)
    private int startPts;

    @JsonProperty(value = "start_time", required = true)
    private double startTime;

    @JsonProperty(value = "disposition", required = true)
    private Disposition disposition;

    @JsonProperty("duration_ts")
    private Long durationTs;

    @JsonProperty("duration")
    @JsonDurationFormat(format = JsonDurationFormat.Format.NUMBER_AS_SECONDS)
    private Duration duration;

    @Getter
    public static class Disposition implements Serializable {

        @JsonProperty(value = "dub", required = true)
        private int dub;

        @JsonProperty(value = "karaoke", required = true)
        private int karaoke;

        @JsonProperty(value = "default", required = true)
        private int jsonMemberDefault;

        @JsonProperty(value = "original", required = true)
        private int original;

        @JsonProperty(value = "visual_impaired", required = true)
        private int visualImpaired;

        @JsonProperty(value = "forced", required = true)
        private int forced;

        @JsonProperty(value = "attached_pic", required = true)
        private int attachedPic;

        @JsonProperty(value = "timed_thumbnails", required = true)
        private int timedThumbnails;

        @JsonProperty(value = "comment", required = true)
        private int comment;

        @JsonProperty(value = "hearing_impaired", required = true)
        private int hearingImpaired;

        @JsonProperty(value = "lyrics", required = true)
        private int lyrics;

        @JsonProperty(value = "clean_effects", required = true)
        private int cleanEffects;
    }

    /**
     * Common tags contained in a stream.
     */
    @Getter
    public static class BaseTags implements Serializable {

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
        @JsonFormat(pattern = Constants.PAT_DATE_TIME, timezone = "utc")
        private LocalDateTime statisticsWritingDateUtc;

        @JsonProperty("_STATISTICS_WRITING_DATE_UTC-eng")
        @JsonFormat(pattern = Constants.PAT_DATE_TIME, timezone = "utc")
        private LocalDateTime statisticsWritingDateUtcEng;

        @JsonProperty("_STATISTICS_TAGS")
        @JsonJoinedValue(delimiter = " ")
        private List<String> statisticsTags;

        @JsonProperty("_STATISTICS_TAGS-eng")
        @JsonJoinedValue(delimiter = " ")
        private List<String> statisticsTagsEng;
    }
}