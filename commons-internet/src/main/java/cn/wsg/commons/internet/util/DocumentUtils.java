package cn.wsg.commons.internet.util;

import cn.wsg.commons.internet.support.CssSelectors;
import cn.wsg.commons.util.MapUtilsExt;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility for operations on a {@link org.jsoup.nodes.Document}.
 *
 * @author Kingen
 */
public final class DocumentUtils {

    private DocumentUtils() {
    }

    /**
     * Gets metadata from the document.
     *
     * @return metadata in a map, including name-content mappings or property-content mappings of OG
     */
    public static Map<String, String> getMetadata(Document document) {
        Elements metas = document.select(CssSelectors.TAG_META);
        Map<String, String> metadata = new HashMap<>(metas.size());
        for (Element meta : metas) {
            if (meta.hasAttr(CssSelectors.ATTR_NAME)) {
                String name = meta.attr(CssSelectors.ATTR_NAME);
                MapUtilsExt.putIfAbsentOrElseThrow(metadata, name, meta.attr(CssSelectors.ATTR_CONTENT));
                continue;
            }
            if (meta.hasAttr(CssSelectors.ATTR_PROPERTY)) {
                String og = meta.attr(CssSelectors.ATTR_PROPERTY);
                MapUtilsExt.putIfAbsentOrElseThrow(metadata, og, meta.attr(CssSelectors.ATTR_CONTENT));
            }
        }
        return metadata;
    }

    /**
     * Parses a text of the interval between the target date and {@link LocalDateTime#now()}.
     *
     * @param interval the text of the interval
     * @return a {@code LocalDate} based on {@link LocalDateTime#now()} with the interval
     * subtracted, not null
     */
    public static LocalDateTime parseInterval(@Nonnull String interval) {
        Matcher minutes = Lazy.MINUTES_INTERVAL_REGEX.matcher(interval);
        if (minutes.matches()) {
            return LocalDateTime.now().minusMinutes(Integer.parseInt(minutes.group("m")));
        }
        Matcher hours = Lazy.HOURS_INTERVAL_REGEX.matcher(interval);
        if (hours.matches()) {
            return LocalDateTime.now().minusHours(Integer.parseInt(hours.group("h")));
        }
        Matcher days = Lazy.DAYS_INTERVAL_REGEX.matcher(interval);
        if (days.matches()) {
            return LocalDateTime.now().minusDays(Integer.parseInt(days.group("d")));
        }
        Matcher weeks = Lazy.WEEKS_INTERVAL_REGEX.matcher(interval);
        if (weeks.matches()) {
            return LocalDateTime.now().minusWeeks(Integer.parseInt(weeks.group("w")));
        }
        Matcher months = Lazy.MONTHS_INTERVAL_REGEX.matcher(interval);
        if (months.matches()) {
            return LocalDateTime.now().minusMonths(Integer.parseInt(months.group("M")));
        }
        Matcher years = Lazy.YEARS_INTERVAL_REGEX.matcher(interval);
        if (years.matches()) {
            return LocalDateTime.now().minusYears(Integer.parseInt(years.group("y")));
        }
        throw new IllegalArgumentException("Can't recognize the interval: " + interval);
    }

    /**
     * Collects all not-blank texts recursively.
     *
     * @param node the root node
     * @return list of not-blank texts
     */
    public static List<String> collectTexts(Node node) {
        List<String> texts = new ArrayList<>();
        collectTexts(node, texts);
        return texts;
    }

    private static void collectTexts(Node node, List<String> texts) {
        if (node instanceof TextNode) {
            String text = ((TextNode)node).text();
            if (StringUtils.isBlank(text)) {
                return;
            }
            texts.add(text.strip());
            return;
        }
        if (node instanceof Element) {
            for (Node childNode : node.childNodes()) {
                collectTexts(childNode, texts);
            }
            return;
        }
        throw new IllegalArgumentException("Unexpected type of node: " + node.getClass());
    }

    private static class Lazy {
        private static final Pattern MINUTES_INTERVAL_REGEX = Pattern.compile("(?<m>\\d+)?????????");
        private static final Pattern HOURS_INTERVAL_REGEX = Pattern.compile("(?<h>\\d+)?????????");
        private static final Pattern DAYS_INTERVAL_REGEX = Pattern.compile("(?<d>\\d+)??????");
        private static final Pattern WEEKS_INTERVAL_REGEX = Pattern.compile("(?<w>\\d+)??????");
        private static final Pattern MONTHS_INTERVAL_REGEX = Pattern.compile("(?<M>\\d+)?????????");
        private static final Pattern YEARS_INTERVAL_REGEX = Pattern.compile("(?<y>\\d+)??????");
    }
}
