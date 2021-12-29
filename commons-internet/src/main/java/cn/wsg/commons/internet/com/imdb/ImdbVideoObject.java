package cn.wsg.commons.internet.com.imdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Kingen
 */
@Getter
@JsonTypeName("VideoObject")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ImdbVideoObject implements ImdbObject {

    @JsonProperty("name")
    private String name;

    @JsonProperty("embedUrl")
    private String embedUrl;

    @JsonProperty("thumbnail")
    private ImdbImageObject thumbnail;

    @JsonProperty("thumbnailUrl")
    private String thumbnailUrl;

    @JsonProperty("description")
    private String description;
}