package cn.wsg.commons.internet.com.douban;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Kingen
 */
@JsonSubTypes({
    @JsonSubTypes.Type(value = DoubanMovie.class, name = "Movie"),
    @JsonSubTypes.Type(value = DoubanSeries.class, name = "TVSeries")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public interface DoubanVideo extends DoubanSubject {

}
