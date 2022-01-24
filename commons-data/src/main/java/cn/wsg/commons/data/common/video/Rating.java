package cn.wsg.commons.data.common.video;

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
@JsonTypeName("Rating")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Rating implements cn.wsg.commons.data.schema.item.Rating {

    @JsonProperty("bestRating")
    private Double bestRating;

    @JsonProperty("worstRating")
    private Double worstRating;

    @JsonProperty("ratingValue")
    private Double ratingValue;
}
