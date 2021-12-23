package cn.wsg.commons.system.cmd.ffmpeg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * The stream of attached information.
 *
 * @author Kingen
 */
@Getter
@JsonIgnoreProperties("side_data_list")
public class AttachmentStream extends AbstractStream {

    @JsonProperty(value = "tags", required = true)
    private AttachmentTags tags;

    AttachmentStream() {
    }

    @Getter
    public static class AttachmentTags {

        @JsonProperty(value = "filename", required = true)
        private String filename;

        @JsonProperty(value = "mimetype", required = true)
        private String mimetype;

        AttachmentTags() {
        }
    }
}
