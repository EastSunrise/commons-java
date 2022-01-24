package cn.wsg.commons.data.common.video;

/**
 * Genres defined on IMDb and Douban.
 *
 * @author Kingen
 * @see <a href="https://help.imdb.com/article/contribution/titles/genres/GZDRMS6R742JRGAG">Genres</a>
 */
public enum MovieGenre {

    ACTION("Action", "动作"),
    ADULT("Adult", null),
    ADVENTURE("Adventure", "冒险"),
    ANIMATION("Animation", "动画"),
    BIOGRAPHY("Biography", "传记"),
    COMEDY("Comedy", "喜剧"),
    CRIME("Crime", "犯罪"),
    DOCUMENTARY("Documentary", "纪录片"),
    DRAMA("Drama", "剧情"),
    FAMILY("Family", "家庭"),
    FANTASY("Fantasy", "奇幻"),
    FILM_NOIR("Film-Noir", "黑色电影"),
    GAME_SHOW("Game-Show", null),
    HISTORY("History", "历史"),
    HORROR("Horror", "恐怖"),
    MUSICAL("Musical", "歌舞"),
    MUSIC("Music", "音乐"),
    MYSTERY("Mystery", "悬疑"),
    NEWS("News", null),
    REALITY_TV("Reality-TV", "真人秀"),
    ROMANCE("Romance", "爱情"),
    SCI_FI("Sci-Fi", "科幻"),
    SHORT("Short", "短片"),
    SPORT("Sport", "运动"),
    TALK_SHOW("Talk-Show", "脱口秀"),
    THRILLER("Thriller", "惊悚"),
    WAR("War", "战争"),
    WESTERN("Western", "西部"),

    // extended genres on Douban

    ANCIENT_COSTUME("Ancient-Costum", "古装"),
    DISASTER("Disaster", "灾难"),
    EROTICA("Erotica", "情色"),
    GAY("Gay/Lesbian", "同性"),
    KIDS("Kids", "儿童"),
    MARTIAL_ARTS("Martial-Arts", "武侠"),
    OPERA("Opera", "戏曲"),
    Ghost(null, "鬼怪"),
    ;

    private final String enTitle;
    private final String zhTitle;

    MovieGenre(String enTitle, String zhTitle) {
        this.enTitle = enTitle;
        this.zhTitle = zhTitle;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public String getZhTitle() {
        return zhTitle;
    }
}
