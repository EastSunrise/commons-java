package cn.kingen.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;

/**
 * The subtitle stream.
 *
 * @author Kingen
 */
@Getter
@JsonTypeName("subtitle")
public class SubtitleStream extends AbstractStream {

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("tags")
    private BaseTags tags;
}
