package cn.kingen.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.math.Fraction;

/**
 * The video stream.
 *
 * @author Kingen
 */
@Getter
@ToString(callSuper = true)
public class VideoStream extends AbstractStream {

    @JsonProperty(value = "level")
    private Integer level;

    @JsonProperty(value = "pix_fmt")
    private String pixFormat;

    @JsonProperty(value = "width")
    private Integer width;

    @JsonProperty(value = "height")
    private Integer height;

    @JsonProperty(value = "coded_width")
    private Integer codedWidth;

    @JsonProperty(value = "coded_height")
    private Integer codedHeight;

    @JsonProperty(value = "closed_captions")
    private Integer closedCaptions;

    @JsonProperty(value = "has_b_frames")
    private Integer hasBFrames;

    @JsonProperty(value = "refs")
    private Integer refs;

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("sample_aspect_ratio")
    private Fraction sampleAspectRatio;

    @JsonProperty("display_aspect_ratio")
    private Fraction displayAspectRatio;

    @JsonProperty("chroma_location")
    private String chromaLocation;

    @JsonProperty("bit_rate")
    private Long bitRate;

    @JsonProperty("field_order")
    private String fieldOrder;

    @JsonProperty("is_avc")
    private Boolean avc;

    @JsonProperty("nal_length_size")
    private Integer nalLengthSize;

    @JsonProperty("bits_per_raw_sample")
    private Integer bitsPerRawSample;

    @JsonProperty("color_range")
    private String colorRange;

    @JsonProperty("color_space")
    private String colorSpace;

    @JsonProperty("color_transfer")
    private String colorTransfer;

    @JsonProperty("color_primaries")
    private String colorPrimaries;

    @JsonProperty("nb_frames")
    private Integer nbFrames;

    VideoStream() {
    }
}
