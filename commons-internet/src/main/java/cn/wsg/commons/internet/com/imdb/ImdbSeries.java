package cn.wsg.commons.internet.com.imdb;

import cn.wsg.commons.internet.common.AggregateRating;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Kingen
 */
@Getter
@JsonIgnoreProperties("@context")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ImdbSeries implements ImdbTitle {

    @JsonProperty("url")
    private String url;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String image;

    @JsonProperty("description")
    private String description;

    @JsonProperty("review")
    private ImdbReview review;

    @JsonProperty("aggregateRating")
    private AggregateRating aggregateRating;

    @JsonProperty("contentRating")
    private String contentRating;

    @JsonProperty("genre")
    private List<String> genres;

    @JsonProperty("keywords")
    private String keywords;

    @JsonProperty("actor")
    private List<ImdbPerson> actors;

    @JsonProperty("creator")
    private List<ImdbCreator> creators;

    @JsonProperty("trailer")
    private ImdbVideoObject trailer;
}
