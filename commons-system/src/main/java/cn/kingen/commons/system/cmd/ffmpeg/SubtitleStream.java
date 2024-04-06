package cn.kingen.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * The subtitle stream.
 *
 * @author Kingen
 */
@Getter
@ToString(callSuper = true)
public class SubtitleStream extends AbstractStream {

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("height")
    private Integer height;
}
