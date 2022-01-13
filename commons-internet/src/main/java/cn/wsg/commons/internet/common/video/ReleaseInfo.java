package cn.wsg.commons.internet.common.video;

import cn.wsg.commons.lang.AssertUtils;
import lombok.Getter;

import java.util.List;

/**
 * todo change to {@link cn.wsg.commons.internet.org.schema.item.Event}
 *
 * @author Kingen
 */
@Getter
public class ReleaseInfo {

    private final String enTitle;
    private final List<ReleaseDate> releaseDates;
    private final List<String> alias;

    public ReleaseInfo(String enTitle, List<ReleaseDate> releaseDates, List<String> alias) {
        this.enTitle = AssertUtils.requireNotBlank(enTitle);
        this.releaseDates = AssertUtils.requireNotEmpty(releaseDates, "release dates");
        this.alias = AssertUtils.requireNotEmpty(alias, "alias");
    }
}
