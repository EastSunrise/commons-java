package cn.wsg.commons.internet.com.imdb;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Kingen
 */
@JsonSubTypes({
    @JsonSubTypes.Type(value = ImdbOrganization.class, name = "Organization"),
    @JsonSubTypes.Type(value = ImdbPerson.class, name = "Person")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public interface ImdbCreator extends ImdbObject {

    /**
     * Returns the url of the creator.
     *
     * @return the url of the creator
     */
    String getUrl();
}
