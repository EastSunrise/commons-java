package cn.kingen.commons.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utilities for net operations.
 *
 * @author Kingen
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NetUtils {

    /**
     * Parses the query part of an url to a multiple-values map.
     */
    public static Map<String, List<String>> getQuery(URL url) {
        Map<String, List<String>> queries = new LinkedHashMap<>();
        if (url.getQuery() == null) {
            return queries;
        }
        String[] pairs = url.getQuery().split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = idx > 0 ? pair.substring(0, idx) : pair;
            if (!queries.containsKey(key)) {
                queries.put(key, new ArrayList<>());
            }
            String value = idx > 0 && pair.length() > idx + 1 ? pair.substring(idx + 1) : null;
            queries.get(key).add(value);
        }
        return queries;
    }
}
