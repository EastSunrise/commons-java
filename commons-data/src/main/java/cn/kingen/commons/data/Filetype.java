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

    BMP(".bmp", Type.IMAGE, "image/bmp"),
    GIF(".gif", Type.IMAGE, "image/gif"),
    ICO(".ico", Type.IMAGE, "image/x-icon"),
    JPG(".jpg", Type.IMAGE, "image/jpeg"),
    JPEG(".jpeg", Type.IMAGE, "image/jpeg"),
    JIF(".jif", Type.IMAGE, "image/jpeg"),
    JFIF(".jfif", Type.IMAGE, "image/jpeg"),
    PNG(".png", Type.IMAGE, "image/png"),
    SVG(".svg", Type.IMAGE, "image/svg+xml"),
    TIF(".tif", Type.IMAGE, "image/tiff"),
    TIFF(".tiff", Type.IMAGE, "image/tiff"),
    WEBP(".webp", Type.IMAGE, "image/webp"),

    // video types

    THREE_GP(".3gp", Type.VIDEO, "video/3gpp"),
    AVI(".avi", Type.VIDEO, "video/x-msvideo"),
    FLV(".flv", Type.VIDEO, "video/x-flv"),
    H264(".h264", Type.VIDEO, "video/h264"),
    MKV(".mkv", Type.VIDEO, "video/x-matroska"),
    MOV(".mov", Type.VIDEO, "video/quicktime"),
    MP4(".mp4", Type.VIDEO, "video/mp4"),
    MPEG(".mpeg", Type.VIDEO, "video/mpeg"),
    TS(".ts", Type.VIDEO, "video/mp2t"),
    WEBM(".webm", Type.VIDEO, "video/webm"),
    WMV(".wmv", Type.VIDEO, "video/x-ms-wmv"),

    // audio types

    AAC(".aac", Type.AUDIO, "audio/aac"),
    MP3(".mp3", Type.AUDIO, "audio/mpeg"),
    OGG(".ogg", Type.AUDIO, "audio/ogg"),
    WAV(".wav", Type.AUDIO, "audio/wav"),
    FLAC(".flac", Type.AUDIO, "audio/flac"),
    ;

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
