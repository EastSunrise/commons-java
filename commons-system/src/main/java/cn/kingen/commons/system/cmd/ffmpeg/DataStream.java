package cn.kingen.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * The data stream.
 *
 * @author Kingen
 */
@Getter
@JsonTypeName("data")
public class DataStream extends AbstractStream {

    @JsonProperty(value = "nb_frames", required = true)
    private int nbFrames;

    @JsonProperty(value = "tags", required = true)
    private DataTags tags;

    @JsonProperty("bit_rate")
    private Long bitRate;

    @Getter
    public static class DataTags {

        @JsonProperty("language")
        private String language;

        @JsonProperty("handler_name")
        private String handlerName;

        @JsonProperty("creation_time")
        private LocalDateTime creationTime;
    }
}
