package cn.wsg.commons.internet.com.imdb;

import cn.wsg.commons.internet.BaseSite;
import cn.wsg.commons.internet.support.FileHttpCacheStorage;
import cn.wsg.commons.internet.support.NotFoundException;
import cn.wsg.commons.internet.support.OtherResponseException;
import cn.wsg.commons.internet.support.RequestCacheControl;
import cn.wsg.commons.internet.support.ResponseCacheControl;
import cn.wsg.commons.internet.support.ResponseVary;
import cn.wsg.commons.internet.support.RootHttpClientBuilder;
import cn.wsg.commons.internet.support.SiteHelper;
import cn.wsg.commons.internet.support.UnexpectedException;
import cn.wsg.commons.lang.Language;
import cn.wsg.commons.lang.RegExUtilsExt;
import cn.wsg.commons.lang.jackson.EnumDeserializers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.cache.CacheConfig;
import org.jsoup.nodes.Document;

/**
 * @author Kingen
 */
public class ImdbRepositoryImpl extends BaseSite implements ImdbRepository {

    public ImdbRepositoryImpl() {
        super("IMDb", HttpHost.create("https://www.imdb.com"),
            RootHttpClientBuilder.create()
                .setRateLimiter(1.0)
                .addInterceptorBefore(new RequestCacheControl())
                .addInterceptorBefore(new ResponseVary())
                .addInterceptorBefore(new ResponseCacheControl())
                .setHttpCacheStorage(new FileHttpCacheStorage())
                .setCacheConfig(CacheConfig.custom().setMaxObjectSize(Integer.MAX_VALUE).build())
                .build(),
            SiteHelper.defaultContext());
    }

    @Nonnull
    @Override
    public ImdbTitle findTitleById(@Nonnull String imdbId)
        throws NotFoundException, OtherResponseException {
        return getObject(ImdbTitle.class, Lazy.TITLE_ID_REGEX, "title", imdbId);
    }

    @Nonnull
    @Override
    public ImdbPerson findPersonById(@Nonnull String imdbId)
        throws NotFoundException, OtherResponseException {
        return getObject(ImdbPerson.class, Lazy.PERSON_ID_REGEX, "name", imdbId);
    }

    private <T extends ImdbObject> T getObject(Class<T> clazz, Pattern p, String type, String id)
        throws NotFoundException, OtherResponseException {
        RegExUtilsExt.matchesOrElseThrow(p, id);
        Document document = getDocument(httpGet("/%s/%s/", type, id));
        try {
            String html = document.selectFirst("script[type=application/ld+json]").html();
            return Lazy.MAPPER.readValue(html, clazz);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    }

    private static final class Lazy {

        private static final ObjectMapper MAPPER = new ObjectMapper()
            .setLocale(Locale.ENGLISH)
            .registerModule(new SimpleModule()
                .addDeserializer(Language.class,
                    EnumDeserializers.ofKey(Language.class, Language::getEnName))
            )
            .registerModule(new JavaTimeModule());

        private static final Pattern TITLE_ID_REGEX = Pattern.compile("tt\\d{7}");
        private static final Pattern PERSON_ID_REGEX = Pattern.compile("nm\\d{7}");
    }
}
