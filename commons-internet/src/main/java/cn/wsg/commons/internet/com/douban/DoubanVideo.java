package cn.wsg.commons.internet.com.douban;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Kingen
 */
@Getter
public class DoubanVideo extends DoubanCreativeWork {

    @Setter(AccessLevel.PACKAGE)
    private String zhTitle;

    @Setter(AccessLevel.PACKAGE)
    private String originalTitle;

    @Setter(AccessLevel.PACKAGE)
    private String imdbId;

}
