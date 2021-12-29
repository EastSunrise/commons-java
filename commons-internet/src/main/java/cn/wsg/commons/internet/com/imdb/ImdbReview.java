package cn.wsg.commons.internet.com.imdb;

import cn.wsg.commons.lang.Language;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Kingen
 */
@Getter
@JsonTypeName("Review")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ImdbReview implements ImdbObject {

    @JsonProperty("itemReviewed")
    private ImdbCreativeWork itemReviewed;

    @JsonProperty("author")
    private ImdbPerson author;

    @JsonProperty("dateCreated")
    private LocalDate dateCreated;

    @JsonProperty("inLanguage")
    private Language inLanguage;

    @JsonProperty("name")
    private String name;

    @JsonProperty("reviewBody")
    private String reviewBody;
}
