package cn.kingen.commons.system.cmd.ffmpeg;

import cn.kingen.commons.system.cmd.CommandExecutor;
import cn.kingen.commons.system.cmd.CommandTask;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.Fraction;
import org.bytedeco.javacpp.Loader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The executor of ffprobe that gathers information from multimedia streams and prints it in human-and-machine-readable
 * fashion.
 *
 * @author Kingen
 * @see <a href="http://www.ffmpeg.org/ffprobe.html">ffprobe Documentation</a>
 */
public class FfprobeExecutor extends CommandExecutor {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .registerModule(new SimpleModule().addDeserializer(Fraction.class, new FractionDeserializer()))
            .registerModule(new JavaTimeModule());
    private final Charset charset;

    public FfprobeExecutor(String executablePath) {
        this(executablePath, StandardCharsets.UTF_8);
    }

    public FfprobeExecutor(String executablePath, Charset charset) {
        super(executablePath);
        this.charset = Objects.requireNonNull(charset);
    }

    /**
     * Loads an instance of ffprobe.
     * <p>
     * <strong>org.bytedeco:ffmpeg-platform</strong> is required to be imported.
     */
    public static FfprobeExecutor load() {
        String ffprobe = Loader.load(org.bytedeco.ffmpeg.ffprobe.class);
        return new FfprobeExecutor(ffprobe);
    }

    /**
     * Gets the container format of the input multimedia stream.
     *
     * @param path path of the input multimedia stream
     * @return the container format
     * @throws IOException if an I/O error occurs
     */
    public Format getFormat(String path) throws IOException {
        return this.getResult(path, "-show_format").format;
    }

    /**
     * Gets all media streams contained in the input multimedia stream.
     *
     * @param path path of the input multimedia stream
     * @return list of all media streams
     * @throws IOException if an I/O error occurs
     */
    public List<AbstractStream> getStreams(String path) throws IOException {
        return this.getResult(path, "-show_streams").streams;
    }

    /**
     * Gets all video streams contained in the input multimedia stream.
     *
     * @param path path of the input multimedia stream
     * @return list of all video streams
     * @throws IOException if an I/O error occurs
     */
    public List<VideoStream> getVideoStreams(String path) throws IOException {
        return this.getStreams(path, "v", VideoStream.class);
    }

    /**
     * Gets all audio streams contained in the input multimedia stream.
     *
     * @param path path of the input multimedia stream
     * @return list of all audio streams
     * @throws IOException if an I/O error occurs
     */
    public List<AudioStream> getAudioStreams(String path) throws IOException {
        return this.getStreams(path, "a", AudioStream.class);
    }

    private <T extends AbstractStream> List<T> getStreams(String path, String arg, Class<T> clazz) throws IOException {
        return this.getResult(path, "-show_streams", "-select_streams", arg).streams.stream().map(clazz::cast)
                .collect(Collectors.toList());
    }

    private Result getResult(String path, String... args) throws IOException {
        String[] commands = ArrayUtils.addAll(args, "-v", "quiet", "-of", "json", "-i", "\"" + path + "\"");
        CommandTask task = this.createTask(commands);
        task.execute();
        try {
            String json = IOUtils.toString(task.getInputStream(), charset);
            return MAPPER.readValue(json, Result.class);
        } finally {
            task.destroy();
        }
    }

    @Getter
    private static class Result {

        @JsonProperty("streams")
        private List<AbstractStream> streams;
        @JsonProperty("format")
        private Format format;

        Result() {
        }
    }
}
