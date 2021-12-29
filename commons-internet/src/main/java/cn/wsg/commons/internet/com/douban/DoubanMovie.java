package cn.wsg.commons.internet.com.douban;

import cn.wsg.commons.internet.common.AggregateRating;
import cn.wsg.commons.internet.common.MovieGenre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class DoubanMovie implements DoubanVideo {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("image")
    private String image;

    @JsonProperty("director")
    private List<DoubanPerson> directors;

    @JsonProperty("author")
    private List<DoubanPerson> authors;

    @JsonProperty("actor")
    private List<DoubanPerson> actors;

    @JsonProperty("datePublished")
    private LocalDate datePublished;

    @JsonProperty("genre")
    private List<MovieGenre> genres;

    @JsonProperty("duration")
    private Duration duration;

    @JsonProperty("description")
    private String description;

    @JsonProperty("aggregateRating")
    private AggregateRating aggregateRating;
}
