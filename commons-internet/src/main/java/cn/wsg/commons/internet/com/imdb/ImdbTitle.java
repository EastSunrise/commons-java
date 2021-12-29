package cn.wsg.commons.internet.com.imdb;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A work of {@link ImdbRepository}, may a movie, an episode or else.
 *
 * @author Kingen
 */
@JsonSubTypes({
    @JsonSubTypes.Type(value = ImdbMovie.class, name = "Movie"),
    @JsonSubTypes.Type(value = ImdbSeries.class, name = "TVSeries"),
    @JsonSubTypes.Type(value = ImdbEpisode.class, name = "TVEpisode"),
    @JsonSubTypes.Type(value = ImdbCreativeWork.class, name = "CreativeWork"),
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public interface ImdbTitle extends ImdbObject {

}
