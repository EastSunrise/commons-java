package cn.kingen.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Getter;
import org.apache.commons.lang3.math.Fraction;

/**
 * The video stream.
 *
 * @author Kingen
 */
@Getter
public class VideoStream extends AbstractStream {

    @JsonProperty(value = "level", required = true)
    private int level;

    @JsonProperty(value = "pix_fmt", required = true)
    private String pixFormat;

    @JsonProperty(value = "width", required = true)
    private int width;

    @JsonProperty(value = "height", required = true)
    private int height;

    @JsonProperty(value = "coded_width", required = true)
    private int codedWidth;

    @JsonProperty(value = "coded_height", required = true)
    private int codedHeight;

    @JsonProperty(value = "closed_captions", required = true)
    private int closedCaptions;

    @JsonProperty(value = "has_b_frames", required = true)
    private int hasBFrames;

    @JsonProperty(value = "refs", required = true)
    private int refs;

    @JsonProperty("tags")
    private VideoTags tags;

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

    @Getter
    public static class VideoTags extends BaseTags {

        @JsonProperty("filename")
        private String filename;

        @JsonProperty("creation_time")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "utc")
        private LocalDateTime creationTime;

        @JsonProperty("encoder")
        private String encoder;

        @JsonProperty("handler_name")
        private String handlerName;

        @JsonProperty("vendor_id")
        private String vendorId;

        @JsonProperty("mimetype")
        private String mimetype;

        VideoTags() {
        }
    }
}
