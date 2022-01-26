package cn.wsg.commons.data.common;

/**
 * Codes for provinces, autonomous regions, municipalities and special administrative regions of the People's Republic
 * of China.
 *
 * @author Kingen
 * @see <a href="http://std.samr.gov.cn/gb/search/gbDetailed?id=71F772D76EA3D3A7E05397BE0A0AB82A">GB/T 2260-2007</a>
 * @see <a href="http://www.mca.gov.cn/article/sj/xzqh/">行政区划代码</a>
 */
public enum Province implements AdministrativeArea {

    BJ(110000, "BJ", "京", "北京市", "Beijing Shi"),
    TJ(120000, "TJ", "津", "天津市", "Tianjin Shi"),
    HE(130000, "HE", "冀", "河北省", "Hebei Sheng"),
    SX(140000, "SX", "晋", "山西省", "Shanxi Sheng"),
    NM(150000, "NM", "内蒙古", "内蒙古自治区", "Nei Mongol Zizhiqu"),
    LN(210000, "LN", "辽", "辽宁省", "Liaoning Sheng"),
    JL(220000, "JL", "吉", "吉林省", "Jilin Sheng"),
    HL(230000, "HL", "黑", "黑龙江省", "Heilongjiang Sheng"),
    SH(310000, "SH", "沪", "上海市", "Shanghai Shi"),
    JS(320000, "JS", "苏", "江苏省", "Jiangsu Sheng"),
    ZJ(330000, "ZJ", "浙", "浙江省", "Zhejiang Sheng"),
    AH(340000, "AH", "皖", "安徽省", "Anhui Sheng"),
    FJ(350000, "FJ", "闽", "福建省", "Fujian Sheng"),
    JX(360000, "JX", "赣", "江西省", "Jiangxi Sheng"),
    SD(370000, "SD", "鲁", "山东省", "Shandong Sheng"),
    HA(410000, "HA", "豫", "河南省", "Henan Sheng"),
    HB(420000, "HB", "鄂", "湖北省", "Hubei Sheng"),
    HN(430000, "HN", "湘", "湖南省", "Hunan Sheng"),
    GD(440000, "GD", "粤", "广东省", "Guangdong Sheng"),
    GX(450000, "GX", "桂", "广西壮族自治区", "Guangxi Zhuangzu Zizhiqu"),
    HI(460000, "HI", "琼", "海南省", "Hainan Sheng"),
    CQ(500000, "CQ", "渝", "重庆市", "Chongqing Shi"),
    SC(510000, "SC", "川", "四川省", "Sichuan Sheng"),
    GZ(520000, "GZ", "贵", "贵州省", "Guizhou Sheng"),
    YN(530000, "YN", "云", "云南省", "Yunnan Sheng"),
    xz(540000, "xz", "藏", "西藏自治区", "Xizang Zizhiqu"),
    SN(610000, "SN", "陕", "陕西省", "Shaanxi Sheng"),
    GS(620000, "GS", "甘", "甘肃省", "Gansu Sheng"),
    QH(630000, "QH", "青", "青海省", "Qinghai Sheng"),
    NX(640000, "NX", "宁", "宁夏回族自治区", "Ningxia Huizu Zizhiqu"),
    XJ(650000, "XJ", "新", "新疆维吾尔自治区", "Xinjiang Uygur Zizhiqu"),
    TW(710000, "TW", "台", "台湾省", "Taiwan Sheng"),
    HK(810000, "HK", "港", "香港特别行政区", "Hongkong Tebiexingzhengqu"),
    MO(820000, "MO", "澳", "澳门特别行政区", "Macau Tebiexingzhengqu");

    private final int number;
    private final String alpha;
    private final String zhShortName;
    private final String zhName;
    private final String romanAlpha;

    Province(int number, String alpha, String zhShortName, String zhName, String romanAlpha) {
        this.number = number;
        this.alpha = alpha;
        this.zhShortName = zhShortName;
        this.zhName = zhName;
        this.romanAlpha = romanAlpha;
    }

    public String getZhShortName() {
        return zhShortName;
    }

    @Override
    public String getZhName() {
        return zhName;
    }

    @Override
    public String getEnName() {
        return romanAlpha;
    }

    @Override
    public String getCode() {
        return alpha;
    }

    @Override
    public int getIntCode() {
        return number;
    }
}
