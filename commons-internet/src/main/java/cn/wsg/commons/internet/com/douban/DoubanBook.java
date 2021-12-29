package cn.wsg.commons.internet.com.douban;

import cn.wsg.commons.internet.common.UnknownType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Kingen
 */
@Getter
@JsonTypeName("Book")
@JsonIgnoreProperties("@context")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DoubanBook implements DoubanSubject {

    @JsonProperty("workExample")
    private List<UnknownType> workExample;

    @JsonProperty("name")
    private String name;

    @JsonProperty("author")
    private List<DoubanPerson> authors;

    @JsonProperty("url")
    private String url;

    @JsonProperty("isbn")
    private Long isbn;

    @JsonProperty("sameAs")
    private String sameAs;
}
