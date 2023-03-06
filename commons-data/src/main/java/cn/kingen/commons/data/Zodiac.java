package cn.kingen.commons.data;

import cn.kingen.commons.lang.BilingualDisplayable;
import lombok.Getter;

/**
 * Twelve zodiacs.
 *
 * @author Kingen
 */
@Getter
public enum Zodiac implements BilingualDisplayable {

    RAT("鼠"),
    OX("牛"),
    TIGER("虎"),
    RABBIT("兔"),
    DRAGON("龙"),
    SNAKE("蛇"),
    HORSE("马"),
    SHEEP("羊"),
    MONKEY("猴"),
    ROOSTER("鸡"),
    DOG("狗"),
    PIG("猪");

    private final String zhName;

    Zodiac(String zhName) {
        this.zhName = zhName;
    }

    @Override
    public String getEnName() {
        return name().toLowerCase();
    }
}
