package cn.kingen.commons.data;

import cn.kingen.commons.lang.BilingualDisplayable;
import lombok.Getter;

/**
 * Twelve constellations.
 *
 * @author Kingen
 */
@Getter
public enum Constellation implements BilingualDisplayable {

    ARIES("白羊座", "Aries"),
    TAURUS("金牛座", "Taurus"),
    GEMINI("双子座", "Gemini"),
    CANCER("巨蟹座", "Cancer"),
    LEO("狮子座", "Leo"),
    VIRGO("处女座", "Virgo"),
    LIBRA("天秤座", "Libra"),
    SCORPIO("天蝎座", "Scorpio"),
    SAGITTARIUS("射手座", "Sagittarius"),
    CAPRICORNUS("摩羯座", "Capricornus"),
    AQUARIUS("水瓶座", "Aquarius"),
    PISCES("双鱼座", "Pisces");

    private final String zhName;
    private final String enName;


    Constellation(String zhName, String enName) {
        this.zhName = zhName;
        this.enName = enName;
    }
}
