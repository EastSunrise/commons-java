package cn.wsg.commons.internet.com.douban;

import cn.wsg.commons.internet.common.AggregateRating;
import cn.wsg.commons.internet.common.MovieGenre;
import cn.wsg.commons.internet.org.schema.item.CreativeWork;
import cn.wsg.commons.lang.Language;
import cn.wsg.commons.lang.Region;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Kingen
 */
@Getter
@JsonSubTypes({
    @JsonSubTypes.Type(value = DoubanMovie.class, name = "Movie"), @JsonSubTypes.Type(value = DoubanTVSeries.class, name = "TVSeries")
})
@JsonIgnoreProperties("@context")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class DoubanCreativeWork extends DoubanThing implements CreativeWork {

    @JsonProperty("author")
    private List<DoubanPerson> authors;

    @JsonProperty("workExample")
    private List<DoubanCreativeWork> workExample;

    @JsonProperty("datePublished")
    private LocalDate datePublished;

    @JsonProperty("genre")
    private List<MovieGenre> genres;

    @JsonProperty("aggregateRating")
    private AggregateRating aggregateRating;

    @Setter(AccessLevel.PACKAGE)
    @JsonProperty("countryOfOrigin")
    private List<Region> countriesOfOrigin;

    @Setter(AccessLevel.PACKAGE)
    @JsonProperty("inLanguage")
    private List<Language> languages;
}
