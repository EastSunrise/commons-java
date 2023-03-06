package cn.kingen.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;

/**
 * The stream of attached information.
 *
 * @author Kingen
 */
@Getter
@JsonTypeName("attachment")
@JsonIgnoreProperties("side_data_list")
public class AttachmentStream extends AbstractStream {

    @JsonProperty(value = "tags", required = true)
    private AttachmentTags tags;

    @Getter
    public static class AttachmentTags {

        @JsonProperty(value = "filename", required = true)
        private String filename;

        @JsonProperty(value = "mimetype", required = true)
        private String mimetype;
    }
}
