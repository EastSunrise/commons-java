package cn.kingen.commons.system.cmd.ffmpeg;

import cn.kingen.commons.lang.jackson.JsonDurationFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Duration;
import lombok.Getter;
import org.apache.commons.lang3.math.Fraction;

/**
 * This class provides a skeleton implementation of the information about each media stream
 * contained in the input multimedia stream.
 *
 * @author Kingen
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "codec_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = VideoStream.class, name = "video"),
    @JsonSubTypes.Type(value = AudioStream.class, name = "audio"),
    @JsonSubTypes.Type(value = SubtitleStream.class, name = "subtitle"),
    @JsonSubTypes.Type(value = AttachmentStream.class, name = "attachment"),
    @JsonSubTypes.Type(value = DataStream.class, name = "data"),
})
@Getter
public abstract class AbstractStream {

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

    AbstractStream() {
    }

    @Getter
    public static class Disposition {

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

        Disposition() {
        }
    }
}