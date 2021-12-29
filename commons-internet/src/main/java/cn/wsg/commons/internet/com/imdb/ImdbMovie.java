package cn.wsg.commons.internet.com.imdb;

import cn.wsg.commons.internet.common.AggregateRating;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.Duration;
import java.time.LocalDate;
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
public class ImdbMovie implements ImdbTitle {

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

    @JsonProperty("datePublished")
    private LocalDate datePublished;

    @JsonProperty("keywords")
    private String keywords;

    @JsonProperty("trailer")
    private ImdbVideoObject trailer;

    @JsonProperty("actor")
    private List<ImdbPerson> actors;

    @JsonProperty("director")
    private List<ImdbPerson> directors;

    @JsonSubTypes({
        @JsonSubTypes.Type(value = ImdbOrganization.class, name = "Organization"),
        @JsonSubTypes.Type(value = ImdbPerson.class, name = "Person")
    })
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    @JsonProperty("creator")
    private List<ImdbCreator> creators;

    @JsonProperty("duration")
    private Duration duration;
}
