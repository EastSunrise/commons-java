package cn.wsg.commons.internet.common;

import cn.wsg.commons.internet.com.imdb.ImdbObject;
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
@JsonTypeName("AggregateRating")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class AggregateRating implements ImdbObject {

    @JsonProperty("ratingCount")
    private Integer ratingCount;

    @JsonProperty("bestRating")
    private Double bestRating;

    @JsonProperty("worstRating")
    private Double worstRating;

    @JsonProperty("ratingValue")
    private Double ratingValue;
}