package cn.wsg.commons.internet.com.douban;

import cn.wsg.commons.internet.util.EnumMapping;
import cn.wsg.commons.lang.Region;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Kingen
 */
enum RegionAlias implements EnumMapping<Region> {

    CN(Region.CN, "中国大陆"),
    HK(Region.HK, "中国香港"),
    TW(Region.TW, "中国台湾"),
    RU(Region.RU, "俄罗斯"),
    XA(Region.XA, "西德"),
    ;

    private final Region region;
    private final String[] alias;

    RegionAlias(Region region, String... alias) {
        this.region = region;
        this.alias = alias;
    }

    @Override
    public Region getEnum() {
        return region;
    }

    @Override
    public boolean contains(String value) {
        return ArrayUtils.contains(alias, value);
    }
}
