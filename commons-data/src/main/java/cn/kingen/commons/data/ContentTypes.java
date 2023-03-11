package cn.kingen.commons.data;

import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Common content types.
 *
 * @author Kingen
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContentTypes {

    public static final Map<String, String> IMAGES = Map.of(
        ".gif", "image/gif",
        ".ief", "image/ief",
        ".bmp", "image/bmp",
        ".png", "image/png",
        ".tif", "image/tiff",
        ".tiff", "image/tiff",
        ".svg", "image/svg+xml",
        ".jfif", "image/jpeg",
        ".jpg", "image/jpeg",
        ".jpeg", "image/jpeg"
    );
    public static final Map<String, String> VIDEOS = Map.of(
        ".m4v", "video/mp4",
        ".mp4", "video/mp4",
        ".mpg", "video/mpeg",
        ".mpeg", "video/mpeg",
        ".mov", "video/quicktime"
    );
    public static final Map<String, String> AUDIOS = Map.of(
        ".m4a", "audio/mp4",
        ".flac", "audio/flac",
        ".mp3", "audio/mpeg",
        ".ogg", "audio/ogg",
        ".aac", "audio/aac",
        ".wav", "audio/x-wav"
    );
}
