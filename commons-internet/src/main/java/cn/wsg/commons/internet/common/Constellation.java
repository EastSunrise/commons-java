package cn.wsg.commons.internet.common;

import cn.wsg.commons.lang.function.BilingualDisplayable;
import lombok.Getter;

/**
 * Twelve constellations.
 *
 * @author Kingen
 */
@Getter
public enum Constellation implements BilingualDisplayable {

    ARIES("白羊座", "Aries", "おひつじ座", "牧羊座", "牡羊座"),
    TAURUS("金牛座", "Taurus", "おうし座"),
    GEMINI("双子座", "Gemini", "ふたご座"),
    CANCER("巨蟹座", "Cancer", "かに座"),
    LEO("狮子座", "Leo", "しし座"),
    VIRGO("处女座", "Virgo", "おとめ座"),
    LIBRA("天秤座", "Libra", "てんびん座", "天枰座", "天平座"),
    SCORPIO("天蝎座", "Scorpio", "さそり座"),
    SAGITTARIUS("射手座", "Sagittarius", "いて座", "人马座"),
    CAPRICORNUS("摩羯座", "Capricornus", "やぎ座", "魔羯座", "山羊座"),
    AQUARIUS("水瓶座", "Aquarius", "みずがめ座"),
    PISCES("双鱼座", "Pisces", "うお座");

    private final String zhName;

    private final String enName;

    private final String jaName;

    private String[] alias;

    Constellation(String zhName, String enName, String jaName) {
        this.zhName = zhName;
        this.enName = enName;
        this.jaName = jaName;
    }

    Constellation(String zhName, String enName, String jaName, String... alias) {
        this.zhName = zhName;
        this.enName = enName;
        this.jaName = jaName;
        this.alias = alias;
    }
}
