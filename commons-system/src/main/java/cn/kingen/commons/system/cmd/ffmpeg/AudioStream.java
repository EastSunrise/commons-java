package cn.kingen.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * The audio stream.
 *
 * @author Kingen
 */
@Getter
@ToString(callSuper = true)
public class AudioStream extends AbstractStream {

    @JsonProperty("profile")
    private String profile;

    @JsonProperty(value = "sample_fmt")
    private String sampleFormat;

    @JsonProperty(value = "sample_rate")
    private Integer sampleRate;

    @JsonProperty(value = "channels")
    private Integer channels;

    @JsonProperty(value = "channel_layout")
    private String channelLayout;

    @JsonProperty("bit_rate")
    private Long bitRate;

    @JsonProperty(value = "bits_per_sample")
    private Integer bitsPerSample;

    @JsonProperty("bits_per_raw_sample")
    private Integer bitsPerRawSample;

    @JsonProperty("nb_frames")
    private Integer nbFrames;

    @JsonProperty("dmix_mode")
    private Integer dmixMode;

    @JsonProperty("ltrt_cmixlev")
    private Double ltrtCmixlev;

    @JsonProperty("ltrt_surmixlev")
    private Double ltrtSurmixlev;

    @JsonProperty("loro_cmixlev")
    private Double loroCmixlev;

    @JsonProperty("loro_surmixlev")
    private Double loroSurmixlev;
}
