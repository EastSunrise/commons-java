package cn.wsg.commons.internet.com.imdb;

import cn.wsg.commons.internet.org.schema.item.Episode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.List;

/**
 * @author Kingen
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ImdbEpisode extends ImdbCreativeWork implements Episode {

    @JsonProperty("actor")
    private List<ImdbPerson> actors;

    @JsonProperty("director")
    private List<ImdbPerson> directors;

    @JsonProperty("duration")
    private Duration duration;

    @JsonProperty("trailer")
    private ImdbVideoObject trailer;

    @Setter(AccessLevel.PACKAGE)
    @JsonProperty("partOfSeries")
    private String seriesTitleId;

    @Setter(AccessLevel.PACKAGE)
    @JsonProperty("episodeNumber")
    private EpisodeNumber episodeNumber;
}
