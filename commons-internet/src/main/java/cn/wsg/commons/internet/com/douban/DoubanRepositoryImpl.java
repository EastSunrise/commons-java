package cn.wsg.commons.internet.com.douban;

import cn.wsg.commons.internet.AbstractLoggableSite;
import cn.wsg.commons.internet.common.MovieGenre;
import cn.wsg.commons.internet.page.Page;
import cn.wsg.commons.internet.page.PageIndex;
import cn.wsg.commons.internet.support.CssSelectors;
import cn.wsg.commons.internet.support.FileHttpCacheStorage;
import cn.wsg.commons.internet.support.LoginException;
import cn.wsg.commons.internet.support.NotFoundException;
import cn.wsg.commons.internet.support.OtherResponseException;
import cn.wsg.commons.internet.support.ResponseCacheControl;
import cn.wsg.commons.internet.support.RootHttpClientBuilder;
import cn.wsg.commons.internet.support.SiteHelper;
import cn.wsg.commons.internet.support.UnexpectedException;
import cn.wsg.commons.lang.AssertUtils;
import cn.wsg.commons.lang.EnumUtilExt;
import cn.wsg.commons.lang.RegExUtilsExt;
import cn.wsg.commons.lang.jackson.EnumDeserializers;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.cache.CacheConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Kingen
 */
@Slf4j
public class DoubanRepositoryImpl extends AbstractLoggableSite<Long> implements DoubanRepository {

    public static final Pattern URL_MOVIE_SUBJECT_REGEX =
        Pattern.compile("https://movie.douban.com/subject/(?<id>\\d{7,8})/?");

    public DoubanRepositoryImpl() {
        super("Douban", HttpHost.create("https://douban.com"),
            RootHttpClientBuilder.create()
                .setRateLimiter(1.0)
                .addInterceptorBefore(new ResponseCacheControl())
                .setHttpCacheStorage(new FileHttpCacheStorage())
                .setCacheConfig(CacheConfig.custom().setMaxObjectSize(Integer.MAX_VALUE).build())
                .build(),
            SiteHelper.defaultContext());
    }

    @Override
    public void login(String username, String password)
        throws OtherResponseException, LoginException {
        this.logout();
        RequestBuilder builder = this.create("accounts", METHOD_POST, "/j/mobile/login/basic")
            .addParameter("ck", "")
            .addParameter("remember", String.valueOf(true))
            .addParameter("name", username)
            .addParameter("password", password);
        LoginResult result;
        try {
            result = this.getObject(builder, Lazy.MAPPER, LoginResult.class);
        } catch (NotFoundException e) {
            throw new UnexpectedException(e);
        }
        // todo handle captcha
        if (!result.isSuccess()) {
            throw new LoginException(result.getMessage());
        }
    }

    @Override
    public Long user() {
        Cookie cookie = this.getCookie("dbcl2");
        if (cookie == null) {
            return null;
        }
        Matcher matcher = RegExUtilsExt
            .matchesOrElseThrow(Lazy.COOKIE_DBCL2_REGEX, cookie.getValue());
        return Long.parseLong(matcher.group("id"));
    }

    @Override
    public void logout() throws OtherResponseException {
        if (this.user() == null) {
            return;
        }
        this.findDocument(this.httpGet(""));
        RequestBuilder builder = this.httpGet("/accounts/logout")
            .addParameter("source", "main")
            .addParameter("ck", Objects.requireNonNull(this.getCookie("ck")).getValue());
        this.findDocument(builder);
    }

    @Override
    public Page<SubjectIndex> searchGlobally(String keyword, PageIndex page, DoubanCatalog catalog)
        throws OtherResponseException {
        AssertUtils.requireNotBlank(keyword);
        RequestBuilder builder = this.httpGet("/j/search")
            .addParameter("q", keyword)
            .addParameter("start", String.valueOf(page.getCurrent() * 20));
        if (catalog != null) {
            builder.addParameter("cat", String.valueOf(catalog.getIntCode()));
        }
        SearchResult result;
        try {
            result = this.getObject(builder, Lazy.MAPPER, SearchResult.class);
        } catch (NotFoundException e) {
            throw new UnexpectedException(e);
        }
        if (result.getMsg() != null) {
            throw new OtherResponseException(HttpStatus.SC_INTERNAL_SERVER_ERROR, result.getMsg());
        }
        List<SubjectIndex> indices = new ArrayList<>(result.getItems().size());
        for (String item : result.getItems()) {
            Element nbg = Jsoup.parse(item).body().selectFirst(".nbg");
            String href = nbg.attr(CssSelectors.ATTR_HREF);
            Matcher matcher = RegExUtilsExt.matchesOrElseThrow(Lazy.SUBJECT_LINK_REGEX, href);
            String url = URLDecoder.decode(matcher.group("u"), StandardCharsets.UTF_8);
            Matcher urlMatcher = Lazy.SUBJECT_URL_REGEX.matcher(url);
            if (!urlMatcher.matches()) {
                log.info("Not a subject: {}", url);
                continue;
            }
            long id = Long.parseLong(urlMatcher.group("id"));
            int code = Integer.parseInt(matcher.group("c"));
            DoubanCatalog cat = EnumUtilExt.valueOfIntCode(DoubanCatalog.class, code);
            indices.add(new SubjectIndex(id, cat, nbg.attr(CssSelectors.ATTR_TITLE)));
        }
        return Page.amountCountable(indices, page, 20, result.getTotal());
    }

    @Override
    public List<SubjectIndex> search(DoubanCatalog catalog, String keyword) {
        AssertUtils.requireNotBlank(keyword);
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Page<MarkedSubject> findUserSubjects(DoubanCatalog catalog, long userId,
        MarkedStatus status, PageIndex page) throws NotFoundException, OtherResponseException {
        RequestBuilder builder = this.create(catalog.name().toLowerCase(), METHOD_GET,
            "/people/%d/%s", userId, status.name().toLowerCase())
            .addParameter("start", String.valueOf(page.getCurrent() * 30))
            .addParameter("sort", "time")
            .addParameter("rating", "all")
            .addParameter("filter", "all")
            .addParameter("mode", "list");
        Document document = this.getDocument(builder);
        Elements lis = document.selectFirst(".list-view").select(".item");
        List<MarkedSubject> subjects = new ArrayList<>(lis.size());
        for (Element li : lis) {
            long id = Long.parseLong(li.id().substring(4));
            String title = li.selectFirst(CssSelectors.TAG_A).text().split("/")[0].strip();
            LocalDate markedDate = LocalDate.parse(li.selectFirst(".date").text());
            subjects.add(new MarkedSubject(id, catalog, title, markedDate));
        }
        String numStr = document.selectFirst(".subject-num").text();
        int total = Integer.parseInt(numStr.split("/")[1].strip());
        return Page.amountCountable(subjects, page, 30, total);
    }

    @Override
    public Page<PersonIndex> findUserCreators(DoubanCatalog catalog, long userId, PageIndex page)
        throws NotFoundException, OtherResponseException {
        RequestBuilder builder = this.create(catalog.name().toLowerCase(), METHOD_GET,
            "/people/%d/%s", userId, catalog.getPersonPlurality())
            .addParameter("start", String.valueOf(page.getCurrent() * 15));
        Document document = this.getDocument(builder);
        Elements items = document.select(".item");
        List<PersonIndex> indices = new ArrayList<>(items.size());
        for (Element item : items) {
            Element a = item.selectFirst(CssSelectors.TAG_A);
            String href = a.attr(CssSelectors.ATTR_HREF);
            Matcher matcher = RegExUtilsExt.matchesOrElseThrow(Lazy.CREATOR_URL_REGEX, href);
            long id = Long.parseLong(matcher.group("id"));
            indices.add(new PersonIndex(id, catalog, a.attr(CssSelectors.ATTR_TITLE)));
        }
        String title = document.title();
        Matcher matcher = RegExUtilsExt.matchesOrElseThrow(Lazy.CREATORS_PAGE_TITLE_REGEX, title);
        long total = Long.parseLong(matcher.group("t"));
        return Page.amountCountable(indices, page, 15, total);
    }

    @Override
    public DoubanVideo findMovieById(long id)
        throws NotFoundException, OtherResponseException {
        return this
            .getSubject(DoubanVideo.class, DoubanCatalog.MOVIE, id, (video, document) -> video);
    }

    @Override
    public DoubanBook findBookById(long id) throws NotFoundException, OtherResponseException {
        return this.getSubject(DoubanBook.class, DoubanCatalog.BOOK, id, (book, document) -> book);
    }

    private <T extends DoubanSubject> T getSubject(Class<T> clazz, DoubanCatalog catalog, long id,
        BiFunction<T, Document, T> decorator)
        throws NotFoundException, OtherResponseException {
        String cat = catalog.name().toLowerCase();
        RequestBuilder builder = this.create(cat, METHOD_GET, "/subject/%d/", id);
        Document document = this.getDocument(builder);
        String html = document.selectFirst("script[type=application/ld+json]").html();
        html = StringUtils.replaceChars(html, "\n\t", "");
        try {
            return decorator.apply(Lazy.MAPPER.readValue(html, clazz), document);
        } catch (JsonProcessingException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public long getDbIdByImdbId(String imdbId)
        throws NotFoundException, OtherResponseException, LoginException {
        if (this.user() == null) {
            throw new LoginException("Please log in first.");
        }
        AssertUtils.requireNotBlank(imdbId);
        RequestBuilder builder = this.create("movie", METHOD_POST, "/new_subject")
            .addParameter("ck", Objects.requireNonNull(this.getCookie("ck")).getValue())
            .addParameter("type", "0")
            .addParameter("p_title", imdbId)
            .addParameter("p_uid", imdbId)
            .addParameter("cat", String.valueOf(DoubanCatalog.MOVIE.getIntCode()))
            .addParameter("subject_submit", "下一步");
        Document document = this.findDocument(builder);
        Element fieldset = document.selectFirst("div#content")
            .selectFirst(CssSelectors.TAG_FIELDSET);
        Element input = fieldset.selectFirst("input#p_uid");
        if (input == null) {
            throw new NotFoundException("");
        }
        Element span = input.nextElementSibling();
        Element ref = span.nextElementSibling();
        if (ref == null) {
            throw new NotFoundException(span.text());
        }
        String href = ref.attr(CssSelectors.ATTR_HREF);
        Matcher matcher = RegExUtilsExt.matchesOrElseThrow(URL_MOVIE_SUBJECT_REGEX, href);
        return Long.parseLong(matcher.group("id"));
    }

    private static final class Lazy {

        private static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new SimpleModule()
                .addDeserializer(MovieGenre.class,
                    EnumDeserializers.ofKey(MovieGenre.class, MovieGenre::getZhTitle)))
            .registerModule(new JavaTimeModule());

        private static final Pattern COOKIE_DBCL2_REGEX = Pattern
            .compile("\"(?<id>\\d+):[0-9A-Za-z+/]+\"");

        private static final Pattern SUBJECT_LINK_REGEX = Pattern.compile(
            "https://www\\.douban\\.com/link2/\\?url=(?<u>[\\w%.-]+)"
                + "&query=(?<q>[\\w%-]+)&cat_id=(?<c>\\d+)&type=search&pos=(?<p>\\d+)");
        private static final Pattern CREATORS_PAGE_TITLE_REGEX = Pattern
            .compile("[^()\\s]+\\((?<t>\\d+)\\)");

        private static final Pattern SUBJECT_URL_REGEX;
        private static final Pattern CREATOR_URL_REGEX;

        static {
            String catalogs = Arrays.stream(DoubanCatalog.values()).map(DoubanCatalog::name)
                .map(String::toLowerCase).collect(Collectors.joining("|"));
            SUBJECT_URL_REGEX = Pattern
                .compile("https://(" + catalogs + ")\\.douban\\.com/subject/(?<id>\\d+)/");
            String creators = Arrays.stream(DoubanCatalog.values()).map(DoubanCatalog::getPerson)
                .collect(Collectors.joining("|"));
            CREATOR_URL_REGEX = Pattern.compile(
                "https://(" + catalogs + ")\\.douban\\.com/(" + creators + ")/(?<id>\\d+)/");
        }
    }

    @Getter
    private static class LoginResult {

        @JsonProperty("status")
        private String status;
        @JsonProperty("message")
        private String message;
        @JsonProperty("description")
        private String description;
        @JsonProperty("payload")
        private Payload payload;

        public boolean isSuccess() {
            return "success".equals(status);
        }

        @Getter
        private static class Payload {

            @JsonProperty("account_info")
            private String account;

            /**
             * When graph validate code is required.
             */
            @JsonProperty("tc_app_id")
            private Long tcAppId;
            @JsonProperty("captcha_signature_sample")
            private String captchaSignatureSample;
            @JsonProperty("touch_cap_url")
            private String touchCapUrl;
            @JsonProperty("captcha_id")
            private String captchaId;
            @JsonProperty("captcha_image_url")
            private String captchaImageUrl;
        }
    }

    @Getter
    private static class SearchResult {

        @JsonProperty("items")
        private List<String> items;

        @JsonProperty("total")
        private int total;

        @JsonProperty("limit")
        private int limit;

        @JsonProperty("more")
        private boolean more;

        @JsonProperty("msg")
        private String msg;
    }
}
