package cn.kingen.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * The subtitle stream.
 *
 * @author Kingen
 */
@Getter
public class SubtitleStream extends AbstractStream {

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("tags")
    private BaseTags tags;

    SubtitleStream() {
    }
}
