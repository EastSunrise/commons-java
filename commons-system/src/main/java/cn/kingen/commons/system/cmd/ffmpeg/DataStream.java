package cn.kingen.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * The data stream.
 *
 * @author Kingen
 */
@Getter
@ToString(callSuper = true)
public class DataStream extends AbstractStream {

    @JsonProperty(value = "nb_frames")
    private Integer nbFrames;

    @JsonProperty("bit_rate")
    private Long bitRate;
}
