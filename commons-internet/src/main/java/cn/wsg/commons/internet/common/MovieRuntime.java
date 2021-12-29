package cn.wsg.commons.internet.common;

import java.time.Duration;
import java.util.Objects;

/**
 * Runtime of a video.
 *
 * @author Kingen
 */
public final class MovieRuntime {

    private final Duration duration;
    private final String comment;

    public MovieRuntime(Duration duration, String comment) {
        this.duration = Objects.requireNonNull(duration);
        this.comment = comment;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getComment() {
        return comment;
    }
}
