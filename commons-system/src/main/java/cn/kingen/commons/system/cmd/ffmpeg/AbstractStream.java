package cn.kingen.commons.system.cmd.ffmpeg;

import cn.kingen.commons.convert.JsonDurationFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.math.Fraction;

import java.io.Serializable;
import java.time.Duration;
import java.util.Map;

/**
 * This class provides a skeleton implementation of the information about each media stream contained in the input
 * multimedia stream.
 *
 * @author Kingen
 */
@Getter
@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "codec_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = VideoStream.class, name = "video"),
        @JsonSubTypes.Type(value = AudioStream.class, name = "audio"),
        @JsonSubTypes.Type(value = SubtitleStream.class, name = "subtitle"),
        @JsonSubTypes.Type(value = DataStream.class, name = "data"),
        @JsonSubTypes.Type(value = AttachmentStream.class, name = "attachment")
})
public abstract class AbstractStream implements Serializable {

    @JsonProperty(value = "index")
    private Integer index;

    @JsonProperty(value = "codec_name")
    private String codecName;

    @JsonProperty(value = "codec_long_name")
    private String codecLongName;

    @JsonProperty(value = "codec_tag_string")
    private String codecTagString;

    @JsonProperty(value = "codec_tag")
    private String codecTag;

    @JsonProperty(value = "r_frame_rate")
    private Fraction rFrameRate;

    @JsonProperty(value = "avg_frame_rate")
    private Fraction averageFrameRate;

    @JsonProperty(value = "time_base")
    private Fraction timeBase;

    @JsonProperty(value = "start_pts")
    private Integer startPts;

    @JsonProperty(value = "start_time")
    private Double startTime;

    @JsonProperty(value = "disposition")
    private Disposition disposition;

    @JsonProperty("duration_ts")
    private Long durationTs;

    @JsonProperty("duration")
    @JsonDurationFormat(format = JsonDurationFormat.Format.NUMBER_AS_SECONDS)
    private Duration duration;

    @JsonProperty("tags")
    private Map<String, Object> tags;

    @Getter
    @ToString
    public static class Disposition implements Serializable {

        @JsonProperty(value = "dub")
        private Integer dub;

        @JsonProperty(value = "karaoke")
        private Integer karaoke;

        @JsonProperty(value = "default")
        private Integer jsonMemberDefault;

        @JsonProperty(value = "original")
        private Integer original;

        @JsonProperty(value = "visual_impaired")
        private Integer visualImpaired;

        @JsonProperty(value = "forced")
        private Integer forced;

        @JsonProperty(value = "attached_pic")
        private Integer attachedPic;

        @JsonProperty(value = "timed_thumbnails")
        private Integer timedThumbnails;

        @JsonProperty(value = "comment")
        private Integer comment;

        @JsonProperty(value = "hearing_impaired")
        private Integer hearingImpaired;

        @JsonProperty(value = "lyrics")
        private Integer lyrics;

        @JsonProperty(value = "clean_effects")
        private Integer cleanEffects;
    }
}