package cn.kingen.commons.data;

import lombok.Getter;

/**
 * Common file types.
 *
 * @author Kingen
 */
@Getter
public enum Filetype {

    // image types

    JPG(".jpg", Type.IMAGE, "image/jpeg"),
    JPEG(".jpeg", Type.IMAGE, "image/jpeg"),
    JFIF(".jfif", Type.IMAGE, "image/jpeg"),
    PNG(".png", Type.IMAGE, "image/png"),
    BMP(".bmp", Type.IMAGE, "image/bmp"),
    GIF(".gif", Type.IMAGE, "image/gif"),
    SVG(".svg", Type.IMAGE, "image/svg+xml"),
    TIF(".tif", Type.IMAGE, "image/tiff"),
    TIFF(".tiff", Type.IMAGE, "image/tiff"),
    IEF(".ief", Type.IMAGE, "image/ief"),

    // video types

    MP4(".mp4", Type.VIDEO, "video/mp4"),
    M4V(".m4v", Type.VIDEO, "video/mp4"),
    MPG(".mpg", Type.VIDEO, "video/mpeg"),
    MPEG(".mpeg", Type.VIDEO, "video/mpeg"),
    MOV(".mov", Type.VIDEO, "video/quicktime"),

    // audio types

    MP3(".mp3", Type.AUDIO, "audio/mpeg"),
    FLAC(".flac", Type.AUDIO, "audio/flac"),
    AAC(".aac", Type.AUDIO, "audio/aac"),
    WAV(".wav", Type.AUDIO, "audio/x-wav"),
    M4A(".m4a", Type.AUDIO, "audio/mp4"),
    OGG(".ogg", Type.AUDIO, "audio/ogg");

    private final String extension;
    private final Type type;
    private final String contentType;

    Filetype(String extension, Type type, String contentType) {
        this.extension = extension;
        this.type = type;
        this.contentType = contentType;
    }

    public enum Type {
        IMAGE, VIDEO, AUDIO
    }
}
