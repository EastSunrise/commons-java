package cn.kingen.commons.data;

import lombok.Getter;

/**
 * Codes for the representation of names of countries and regions: the codes and English names are based on <a
 * href="https://www.iso.org/iso-3166-country-codes.html">ISO 3166 — Country Codes</a> and the Chinese names are based
 * on <a href="http://std.samr.gov.cn/gb/search/gbDetailed?id=71F772D7BB83D3A7E05397BE0A0AB82A">GB/T 2659-2000</a>.
 *
 * @author Kingen
 */
@Getter
public enum Country implements AdministrativeArea {

    // regions with officially assigned codes

    AD("AD", "AND", 20, "安道尔", "Andorra"),
    AE("AE", "ARE", 784, "阿联酋", "United Arab Emirates (the)"),
    AF("AF", "AFG", 4, "阿富汗", "Afghanistan"),
    AG("AG", "ATG", 28, "安提瓜和巴布达", "Antigua and Barbuda"),
    AI("AI", "AIA", 660, "安圭拉", "Anguilla"),
    AL("AL", "ALB", 8, "阿尔巴尼亚", "Albania"),
    AM("AM", "ARM", 51, "亚美尼亚", "Armenia"),
    AO("AO", "AGO", 24, "安哥拉", "Angola"),
    AQ("AQ", "ATA", 10, "南极洲", "Antarctica"),
    AR("AR", "ARG", 32, "阿根廷", "Argentina"),
    AS("AS", "ASM", 16, "美属萨摩亚", "American Samoa"),
    AT("AT", "AUT", 40, "奥地利", "Austria"),
    AU("AU", "AUS", 36, "澳大利亚", "Australia"),
    AW("AW", "ABW", 533, "阿鲁巴", "Aruba"),
    AX("AX", "ALA", 248, "奥兰群岛", "Åland Islands"),
    AZ("AZ", "AZE", 31, "阿塞拜疆", "Azerbaijan"),
    BA("BA", "BIH", 70, "波黑", "Bosnia and Herzegovina"),
    BB("BB", "BRB", 52, "巴巴多斯", "Barbados"),
    BD("BD", "BGD", 50, "孟加拉国", "Bangladesh"),
    BE("BE", "BEL", 56, "比利时", "Belgium"),
    BF("BF", "BFA", 854, "布基纳法索", "Burkina Faso"),
    BG("BG", "BGR", 100, "保加利亚", "Bulgaria"),
    BH("BH", "BHR", 48, "巴林", "Bahrain"),
    BI("BI", "BDI", 108, "布隆迪", "Burundi"),
    BJ("BJ", "BEN", 204, "贝宁", "Benin"),
    BL("BL", "BLM", 652, "圣巴泰勒米", "Saint Barthélemy"),
    BM("BM", "BMU", 60, "百慕大", "Bermuda"),
    BN("BN", "BRN", 96, "文莱", "Brunei Darussalam"),
    BO("BO", "BOL", 68, "玻利维亚", "Bolivia (Plurinational State of)"),
    BR("BR", "BRA", 76, "巴西", "Brazil"),
    BQ("BQ", "BES", 535, "博内尔岛、圣尤斯特歇斯和萨巴岛", "Bonaire, Sint Eustatius and Saba"),
    BS("BS", "BHS", 44, "巴哈马", "Bahamas (the)"),
    BT("BT", "BTN", 64, "不丹", "Bhutan"),
    BV("BV", "BVT", 74, "布维岛", "Bouvet Island"),
    BW("BW", "BWA", 72, "博茨瓦纳", "Botswana"),
    BY("BY", "BLR", 112, "白俄罗斯", "Belarus"),
    BZ("BZ", "BLZ", 84, "伯利兹", "Belize"),
    CA("CA", "CAN", 124, "加拿大", "Canada"),
    CC("CC", "CCK", 166, "科科斯（基林）群岛", "Cocos (Keeling) Islands (the)"),
    CD("CD", "COD", 180, "刚果（金）", "Congo (the Democratic Republic of the)"),
    CF("CF", "CAF", 140, "中非", "Central African Republic (the)"),
    CG("CG", "COG", 178, "刚果（布）", "Congo (the)"),
    CH("CH", "CHE", 756, "瑞士", "Switzerland"),
    CI("CI", "CIV", 384, "科特迪瓦", "Côte d'Ivoire"),
    CK("CK", "COK", 184, "库克群岛", "Cook Islands (the)"),
    CL("CL", "CHL", 152, "智利", "Chile"),
    CM("CM", "CMR", 120, "喀麦隆", "Cameroon"),
    CN("CN", "CHN", 156, "中国", "China"),
    CO("CO", "COL", 170, "哥伦比亚", "Colombia"),
    CR("CR", "CRI", 188, "哥斯达黎加", "Costa Rica"),
    CU("CU", "CUB", 192, "古巴", "Cuba"),
    CV("CV", "CPV", 132, "佛得角", "Cabo Verde"),
    CW("CW", "CUW", 531, "库拉索", "Curaçao"),
    CX("CX", "CXR", 162, "圣诞岛", "Christmas Island"),
    CY("CY", "CYP", 196, "塞浦路斯", "Cyprus"),
    CZ("CZ", "CZE", 203, "捷克", "Czechia"),
    DE("DE", "DEU", 276, "德国", "Germany"),
    DJ("DJ", "DJI", 262, "吉布提", "Djibouti"),
    DK("DK", "DNK", 208, "丹麦", "Denmark"),
    DM("DM", "DMA", 212, "多米尼克", "Dominica"),
    DO("DO", "DOM", 214, "多米尼加", "Dominican Republic (the)"),
    DZ("DZ", "DZA", 12, "阿尔及利亚", "Algeria"),
    EC("EC", "ECU", 218, "厄瓜多尔", "Ecuador"),
    EE("EE", "EST", 233, "爱沙尼亚", "Estonia"),
    EG("EG", "EGY", 818, "埃及", "Egypt"),
    EH("EH", "ESH", 732, "西撒哈拉", "Western Sahara"),
    ER("ER", "ERI", 232, "厄立特里亚", "Eritrea"),
    ES("ES", "ESP", 724, "西班牙", "Spain"),
    ET("ET", "ETH", 231, "埃塞俄比亚", "Ethiopia"),
    FI("FI", "FIN", 246, "芬兰", "Finland"),
    FJ("FJ", "FJI", 242, "斐济", "Fiji"),
    FK("FK", "FLK", 238, "福克兰群岛（马尔维纳斯）", "Falkland Islands (the) [Malvinas]"),
    FM("FM", "FSM", 583, "密克罗尼西亚联邦", "Micronesia (Federated States of)"),
    FO("FO", "FRO", 234, "法罗群岛", "Faroe Islands (the)"),
    FR("FR", "FRA", 250, "法国", "France"),
    GA("GA", "GAB", 266, "加蓬", "Gabon"),
    GB("GB", "GBR", 826, "英国", "United Kingdom of Great Britain and Northern Ireland (the)"),
    GD("GD", "GRD", 308, "格林纳达", "Grenada"),
    GE("GE", "GEO", 268, "格鲁吉亚", "Georgia"),
    GF("GF", "GUF", 254, "法属圭亚那", "French Guiana"),
    GG("GG", "GGY", 831, "根西岛", "Guernsey"),
    GH("GH", "GHA", 288, "加纳", "Ghana"),
    GI("GI", "GIB", 292, "直布罗陀", "Gibraltar"),
    GL("GL", "GRL", 304, "格陵兰", "Greenland"),
    GM("GM", "GMB", 270, "冈比亚", "Gambia (the)"),
    GN("GN", "GIN", 324, "几内亚", "Guinea"),
    GP("GP", "GLP", 312, "瓜德罗普", "Guadeloupe"),
    GQ("GQ", "GNQ", 226, "赤道几内亚", "Equatorial Guinea"),
    GR("GR", "GRC", 300, "希腊", "Greece"),
    GS("GS", "SGS", 239, "南乔治亚岛和南桑德韦奇岛", "South Georgia and the South Sandwich Islands"),
    GT("GT", "GTM", 320, "危地马拉", "Guatemala"),
    GU("GU", "GUM", 316, "关岛", "Guam"),
    GW("GW", "GNB", 624, "几内亚比绍", "Guinea-Bissau"),
    GY("GY", "GUY", 328, "圭亚那", "Guyana"),
    HK("HK", "HKG", 344, "香港", "Hong Kong"),
    HM("HM", "HMD", 334, "赫德岛和麦克唐纳岛", "Heard Island and McDonald Islands"),
    HN("HN", "HND", 340, "洪都拉斯", "Honduras"),
    HR("HR", "HRV", 191, "克罗地亚", "Croatia"),
    HT("HT", "HTI", 332, "海地", "Haiti"),
    HU("HU", "HUN", 348, "匈牙利", "Hungary"),
    ID("ID", "IDN", 360, "印度尼西亚", "Indonesia"),
    IE("IE", "IRL", 372, "爱尔兰", "Ireland"),
    IL("IL", "ISR", 376, "以色列", "Israel"),
    IM("IM", "IMN", 833, "马恩岛", "Isle of Man"),
    IN("IN", "IND", 356, "印度", "India"),
    IO("IO", "IOT", 86, "英属印度洋领地", "British Indian Ocean Territory (the)"),
    IQ("IQ", "IRQ", 368, "伊拉克", "Iraq"),
    IR("IR", "IRN", 364, "伊朗", "Iran (Islamic Republic of)"),
    IS("IS", "ISL", 352, "冰岛", "Iceland"),
    IT("IT", "ITA", 380, "意大利", "Italy"),
    JE("JE", "JEY", 832, "泽西", "Jersey"),
    JM("JM", "JAM", 388, "牙买加", "Jamaica"),
    JO("JO", "JOR", 400, "约旦", "Jordan"),
    JP("JP", "JPN", 392, "日本", "Japan"),
    KE("KE", "KEN", 404, "肯尼亚", "Kenya"),
    KG("KG", "KGZ", 417, "吉尔吉斯斯坦", "Kyrgyzstan"),
    KH("KH", "KHM", 116, "柬埔寨", "Cambodia"),
    KI("KI", "KIR", 296, "基里巴斯", "Kiribati"),
    KM("KM", "COM", 174, "科摩罗", "Comoros (the)"),
    KN("KN", "KNA", 659, "圣基茨和尼维斯", "Saint Kitts and Nevis"),
    KP("KP", "PRK", 408, "朝鲜", "Korea (the Democratic People's Republic of)"),
    KR("KR", "KOR", 410, "韩国", "Korea (the Republic of)"),
    KW("KW", "KWT", 414, "科威特", "Kuwait"),
    KY("KY", "CYM", 136, "开曼群岛", "Cayman Islands (the)"),
    KZ("KZ", "KAZ", 398, "哈萨克斯坦", "Kazakhstan"),
    LA("LA", "LAO", 418, "老挝", "Lao People's Democratic Republic (the)"),
    LB("LB", "LBN", 422, "黎巴嫩", "Lebanon"),
    LC("LC", "LCA", 662, "圣卢西亚", "Saint Lucia"),
    LI("LI", "LIE", 438, "列支敦士登", "Liechtenstein"),
    LK("LK", "LKA", 144, "斯里兰卡", "Sri Lanka"),
    LR("LR", "LBR", 430, "利比里亚", "Liberia"),
    LS("LS", "LSO", 426, "莱索托", "Lesotho"),
    LT("LT", "LTU", 440, "立陶宛", "Lithuania"),
    LU("LU", "LUX", 442, "卢森堡", "Luxembourg"),
    LV("LV", "LVA", 428, "拉脱维亚", "Latvia"),
    LY("LY", "LBY", 434, "利比亚", "Libya"),
    MA("MA", "MAR", 504, "摩洛哥", "Morocco"),
    MC("MC", "MCO", 492, "摩纳哥", "Monaco"),
    MD("MD", "MDA", 498, "摩尔多瓦", "Moldova (the Republic of)"),
    ME("ME", "MNE", 499, "黑山", "Montenegro"),
    MF("MF", "MAF", 663, "法属圣马丁", "Saint Martin (French part)"),
    MG("MG", "MDG", 450, "马达加斯加", "Madagascar"),
    MH("MH", "MHL", 584, "马绍尔群岛", "Marshall Islands (the)"),
    MK("MK", "MKD", 807, "北马其顿", "North Macedonia"),
    ML("ML", "MLI", 466, "马里", "Mali"),
    MM("MM", "MMR", 104, "缅甸", "Myanmar"),
    MN("MN", "MNG", 496, "蒙古", "Mongolia"),
    MO("MO", "MAC", 446, "澳门", "Macao"),
    MP("MP", "MNP", 580, "北马里亚纳", "Northern Mariana Islands (the)"),
    MQ("MQ", "MTQ", 474, "马提尼克", "Martinique"),
    MR("MR", "MRT", 478, "毛里塔尼亚", "Mauritania"),
    MS("MS", "MSR", 500, "蒙特塞拉特", "Montserrat"),
    MT("MT", "MLT", 470, "马耳他", "Malta"),
    MU("MU", "MUS", 480, "毛里求斯", "Mauritius"),
    MV("MV", "MDV", 462, "马尔代夫", "Maldives"),
    MW("MW", "MWI", 454, "马拉维", "Malawi"),
    MX("MX", "MEX", 484, "墨西哥", "Mexico"),
    MY("MY", "MYS", 458, "马来西亚", "Malaysia"),
    MZ("MZ", "MOZ", 508, "莫桑比克", "Mozambique"),
    NA("NA", "NAM", 516, "纳米比亚", "Namibia"),
    NC("NC", "NCL", 540, "新喀里多尼亚", "New Caledonia"),
    NE("NE", "NER", 562, "尼日尔", "Niger (the)"),
    NF("NF", "NFK", 574, "诺福克岛", "Norfolk Island"),
    NG("NG", "NGA", 566, "尼日利亚", "Nigeria"),
    NI("NI", "NIC", 558, "尼加拉瓜", "Nicaragua"),
    NL("NL", "NLD", 528, "荷兰", "Netherlands (the)"),
    NO("NO", "NOR", 578, "挪威", "Norway"),
    NP("NP", "NPL", 524, "尼泊尔", "Nepal"),
    NR("NR", "NRU", 520, "瑙鲁", "Nauru"),
    NU("NU", "NIU", 570, "纽埃", "Niue"),
    NZ("NZ", "NZL", 554, "新西兰", "New Zealand"),
    OM("OM", "OMN", 512, "阿曼", "Oman"),
    PA("PA", "PAN", 591, "巴拿马", "Panama"),
    PE("PE", "PER", 604, "秘鲁", "Peru"),
    PF("PF", "PYF", 258, "法属波利尼西亚", "French Polynesia"),
    PG("PG", "PNG", 598, "巴布亚新几内亚", "Papua New Guinea"),
    PH("PH", "PHL", 608, "菲律宾", "Philippines (the)"),
    PK("PK", "PAK", 586, "巴基斯坦", "Pakistan"),
    PL("PL", "POL", 616, "波兰", "Poland"),
    PM("PM", "SPM", 666, "圣皮埃尔和密克隆", "Saint Pierre and Miquelon"),
    PN("PN", "PCN", 612, "皮特凯恩", "Pitcairn"),
    PR("PR", "PRI", 630, "波多黎各", "Puerto Rico"),
    PS("PS", "PSE", 275, "巴勒斯坦", "Palestine, State of"),
    PT("PT", "PRT", 620, "葡萄牙", "Portugal"),
    PW("PW", "PLW", 585, "帕劳", "Palau"),
    PY("PY", "PRY", 600, "巴拉圭", "Paraguay"),
    QA("QA", "QAT", 634, "卡塔尔", "Qatar"),
    RE("RE", "REU", 638, "留尼汪", "Réunion"),
    RO("RO", "ROU", 642, "罗马尼亚", "Romania"),
    RS("RS", "SRB", 688, "塞尔维亚", "Serbia"),
    RU("RU", "RUS", 643, "俄罗斯联邦", "Russian Federation (the)"),
    RW("RW", "RWA", 646, "卢旺达", "Rwanda"),
    SA("SA", "SAU", 682, "沙特阿拉伯", "Saudi Arabia"),
    SB("SB", "SLB", 90, "所罗门群岛", "Solomon Islands"),
    SC("SC", "SYC", 690, "塞舌尔", "Seychelles"),
    SD("SD", "SDN", 729, "苏丹", "Sudan (the)"),
    SE("SE", "SWE", 752, "瑞典", "Sweden"),
    SG("SG", "SGP", 702, "新加坡", "Singapore"),
    SH("SH", "SHN", 654, "圣赫勒拿、阿森松和特里斯坦达库尼亚", "Saint Helena, Ascension and Tristan da Cunha"),
    SI("SI", "SVN", 705, "斯洛文尼亚", "Slovenia"),
    SJ("SJ", "SJM", 744, "斯瓦尔巴岛和扬马延岛", "Svalbard and Jan Mayen"),
    SK("SK", "SVK", 703, "斯洛伐克", "Slovakia"),
    SL("SL", "SLE", 694, "塞拉利昂", "Sierra Leone"),
    SM("SM", "SMR", 674, "圣马力诺", "San Marino"),
    SN("SN", "SEN", 686, "塞内加尔", "Senegal"),
    SO("SO", "SOM", 706, "索马里", "Somalia"),
    SR("SR", "SUR", 740, "苏里南", "Suriname"),
    SS("SS", "SSD", 728, "南苏丹", "South Sudan"),
    ST("ST", "STP", 678, "圣多美和普林西比", "Sao Tome and Principe"),
    SV("SV", "SLV", 222, "萨尔瓦多", "El Salvador"),
    SX("SX", "SXM", 534, "荷属圣马丁", "Sint Maarten (Dutch part)"),
    SY("SY", "SYR", 760, "叙利亚", "Syrian Arab Republic (the)"),
    SZ("SZ", "SWZ", 748, "斯威士兰", "Eswatini"),
    TC("TC", "TCA", 796, "特克斯和凯科斯群岛", "Turks and Caicos Islands (the)"),
    TD("TD", "TCD", 148, "乍得", "Chad"),
    TF("TF", "ATF", 260, "法属南部领地", "French Southern Territories (the)"),
    TG("TG", "TGO", 768, "多哥", "Togo"),
    TH("TH", "THA", 764, "泰国", "Thailand"),
    TJ("TJ", "TJK", 762, "塔吉克斯坦", "Tajikistan"),
    TK("TK", "TKL", 772, "托克劳", "Tokelau"),
    TL("TL", "TLS", 626, "东帝汶", "Timor-Leste"),
    TM("TM", "TKM", 795, "土库曼斯坦", "Turkmenistan"),
    TN("TN", "TUN", 788, "突尼斯", "Tunisia"),
    TO("TO", "TON", 776, "汤加", "Tonga"),
    TR("TR", "TUR", 792, "土耳其", "Turkey"),
    TT("TT", "TTO", 780, "特立尼达和多巴哥", "Trinidad and Tobago"),
    TV("TV", "TUV", 798, "图瓦卢", "Tuvalu"),
    TW("TW", "TWN", 158, "台湾", "Taiwan (Province of China)"),
    TZ("TZ", "TZA", 834, "坦桑尼亚", "Tanzania, the United Republic of"),
    UA("UA", "UKR", 804, "乌克兰", "Ukraine"),
    UG("UG", "UGA", 800, "乌千达", "Uganda"),
    UM("UM", "UMI", 581, "美国本土外小岛屿", "United States Minor Outlying Islands (the)"),
    US("US", "USA", 840, "美国", "United States of America (the)"),
    UY("UY", "URY", 858, "乌拉圭", "Uruguay"),
    UZ("UZ", "UZB", 860, "乌兹别克斯坦", "Uzbekistan"),
    VA("VA", "VAT", 336, "梵蒂冈", "Holy See (the)"),
    VC("VC", "VCT", 670, "圣文森特和格林纳丁斯", "Saint Vincent and the Grenadines"),
    VE("VE", "VEN", 862, "委内瑞拉", "Venezuela (Bolivarian Republic of)"),
    VG("VG", "VGB", 92, "英属维尔京群岛", "Virgin Islands (British)"),
    VI("VI", "VIR", 850, "美属维尔京群岛", "Virgin Islands (U.S.)"),
    VN("VN", "VNM", 704, "越南", "Viet Nam"),
    VU("VU", "VUT", 548, "瓦努阿图", "Vanuatu"),
    WF("WF", "WLF", 876, "瓦利斯和富图纳", "Wallis and Futuna"),
    WS("WS", "WSM", 882, "萨摩亚", "Samoa"),
    YE("YE", "YEM", 887, "也门", "Yemen"),
    YT("YT", "MYT", 175, "马约特", "Mayotte"),
    ZA("ZA", "ZAF", 710, "南非", "South Africa"),
    ZM("ZM", "ZMB", 894, "赞比亚", "Zambia"),
    ZW("ZW", "ZWE", 716, "津巴布韦", "Zimbabwe"),

    // regions with exceptionally reserved codes

    SU("SU", "SUN", 810, "苏联", "USSR"),

    // regions with transitionally reserved codes

    YU("YU", "YUG", 891, "南斯拉夫", "Yugoslavia"),

    // regions with formerly used codes

    CS("CS", "CSK", 200, "捷克斯洛伐克", "Czechoslovakia"),
    DD("DD", "DDR", 278, "民主德国", "German Democratic Republic"),

    // regions with no suitable existing codes in ISO 3166 (AA, QM-QZ, XA-XZ, ZZ and AAA-AAZ, QMA-QZZ, XAA-XZZ,
    // ZZA-ZZZ and 900-999)

    XA("XA", "XAA", 901, "联邦德国", "The Federal Republic of Germany"),
    ;

    private final String alpha2;
    private final String alpha3;
    private final int number;
    private final String zhShortName;
    private final String enShortName;

    Country(String alpha2, String alpha3, int number, String zhShortName, String enShortName) {
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
        this.number = number;
        this.zhShortName = zhShortName;
        this.enShortName = enShortName;
    }

    @Override
    public int getCode() {
        return number;
    }

    @Override
    public String getAbbr() {
        return alpha2;
    }

    @Override
    public String getZhName() {
        return zhShortName;
    }

    @Override
    public String getEnName() {
        return enShortName;
    }
}
