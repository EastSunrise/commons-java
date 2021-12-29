package cn.wsg.commons.internet.com.douban;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a person on {@link DoubanRepository}, a celebrity, an author or a musician.
 *
 * @author Kingen
 */
@Getter
@JsonTypeName("Person")
@JsonIgnoreProperties("@context")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DoubanPerson implements DoubanObject {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;
}
