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
@JsonTypeName("CreativeWork")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ImdbCreativeWork implements ImdbTitle {

    @JsonProperty("url")
    private String url;
}
