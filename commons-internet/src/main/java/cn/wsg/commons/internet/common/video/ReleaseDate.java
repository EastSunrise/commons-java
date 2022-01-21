package cn.wsg.commons.internet.common.video;

import cn.wsg.commons.Region;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.Objects;

/**
 * @author Kingen
 */
@Getter
public class ReleaseDate {

    private final Region country;
    private final Temporal date;
    private final String attribute;

    private ReleaseDate(Region country, Temporal date, String attribute) {
        this.country = Objects.requireNonNull(country);
        this.date = Objects.requireNonNull(date);
        this.attribute = attribute;
    }

    /**
     * Obtains an instance of {@code ReleaseDate} from a text string using a specific formatter.
     *
     * @param text      the text to parse, not null
     * @param formatter the formatter to use, not null
     * @return the parsed release date with a year, a year-month or a localDate
     * @throws DateTimeParseException if the text cannot be parsed
     */
    public static ReleaseDate parse(Region country, String text, DateTimeFormatter formatter, String attribute) {
        try {
            return new ReleaseDate(country, LocalDate.parse(text, formatter), attribute);
        } catch (DateTimeParseException ignored) {
        }
        try {
            return new ReleaseDate(country, YearMonth.parse(text, formatter), attribute);
        } catch (DateTimeParseException ignored) {
        }
        return new ReleaseDate(country, Year.parse(text, formatter), attribute);
    }
}
