package cn.kingen.commons.system.excel;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.Function;
import lombok.NonNull;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A template is a descriptor for properties of beans that matches the data in format of Excel.
 *
 * @author Kingen
 **/
public class ExcelTemplate<T> {

    public static final DateTimeFormatter STD_LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final List<ColumnDescriptor<T, ?>> columns = new ArrayList<>();

    private String sheetName;

    private Charset charset = StandardCharsets.UTF_8;

    private ZoneId zoneId = ZoneId.systemDefault();

    public ExcelTemplate() {
    }

    public ExcelTemplate(String defaultSheetName) {
        this.sheetName = defaultSheetName;
    }

    /**
     * Creates a template for the given class, including all properties annotated with {@link ExcelColumn}.
     *
     * @param clazz    type of beans to be read
     * @param implicit include not-explicitly annotated fields if true
     */
    public static <T> ExcelTemplate<T> create(Class<T> clazz, boolean implicit) {
        ExcelTemplate<T> template = new ExcelTemplate<>();
        for (Field field : FieldUtils.getAllFieldsList(clazz)) {
            ExcelColumn column = field.getAnnotation(ExcelColumn.class);
            if (column != null || implicit) {
                String header = column != null ? column.header() : field.getName();
                template = template.addColumn(header, t -> {
                    try {
                        return FieldUtils.readField(field, t, true);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    }
                });
            }
        }
        return template;
    }

    /**
     * Creates a sheet using default sheet name and writes data based on this template.
     */
    public void writeSheet(XSSFWorkbook wb, List<? extends T> objects) {
        writeSheet(wb, null, objects);
    }

    /**
     * Creates a sheet and writes data based on this template.
     */
    public void writeSheet(XSSFWorkbook wb, String sheetName, List<? extends T> objects) {
        if (sheetName == null) {
            sheetName = this.sheetName;
        }
        if (sheetName == null) {
            throw new IllegalArgumentException("sheet name must not be null");
        }
        XSSFSheet sheet = wb.createSheet(sheetName);
        writeHeaders(wb, sheet);

        int rowIndex = 1;
        for (T obj : objects) {
            XSSFRow row = sheet.createRow(rowIndex);
            for (int ci = 0; ci < columns.size(); ci++) {
                Object value = columns.get(ci).getter().apply(obj);
                writeCell(row.createCell(ci), value);
            }
            rowIndex++;
        }
    }

    private void writeHeaders(XSSFWorkbook workbook, XSSFSheet sheet) {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        XSSFRow row0 = sheet.createRow(0);
        for (int ci = 0; ci < columns.size(); ci++) {
            ColumnDescriptor<?, ?> column = columns.get(ci);
            if (column.width >= 0) {
                sheet.setColumnWidth(ci, column.width);
            }
            XSSFCell cell = row0.createCell(ci);
            cell.setCellValue(column.header());
            cell.setCellStyle(style);
        }
    }

    private void writeCell(XSSFCell cell, Object value) {
        if (value == null || writeBase(cell, value)) {
            return;
        }
        if (writeDateTime(cell, value)) {
            return;
        }
        if (writeArray(cell, value)) {
            return;
        }
        if (writeOptional(cell, value)) {
            return;
        }
        cell.setCellValue(value.toString());
    }

    private boolean writeBase(XSSFCell cell, Object value) {
        if (value instanceof Boolean bool) {
            cell.setCellValue(bool);
        } else if (value instanceof Character ch) {
            cell.setCellValue(ch.toString());
        } else if (value instanceof Number number) {
            cell.setCellValue(number.doubleValue());
        } else if (value instanceof CharSequence cs) {
            cell.setCellValue(cs.toString());
        } else {
            return false;
        }
        return true;
    }

    private boolean writeDateTime(XSSFCell cell, Object value) {
        if (value instanceof Instant instant) {
            cell.setCellValue(LocalDateTime.ofInstant(instant, zoneId).format(STD_LOCAL_DATE_TIME));
        } else if (value instanceof OffsetDateTime || value instanceof ZonedDateTime) {
            cell.setCellValue(LocalDateTime.from((TemporalAccessor) value).format(STD_LOCAL_DATE_TIME));
        } else if (value instanceof LocalDate date) {
            cell.setCellValue(date.toString());
        } else if (value instanceof LocalTime time) {
            cell.setCellValue(time.toString());
        } else if (value instanceof LocalDateTime dateTime) {
            cell.setCellValue(dateTime.format(STD_LOCAL_DATE_TIME));
        } else if (value instanceof Date date) {
            cell.setCellValue(SDF_DATE.format(date));
        } else if (value instanceof Calendar calendar) {
            cell.setCellValue(SDF_DATE.format(calendar.getTime()));
        } else if (value instanceof Duration duration) {
            if (duration.getNano() > 0) {
                cell.setCellValue(duration.toNanos() * 1.0 / 1000_000_000L);
            } else {
                cell.setCellValue(duration.toSeconds());
            }
        } else {
            return false;
        }
        return true;
    }

    private boolean writeArray(XSSFCell cell, Object value) {
        if (value instanceof byte[] bytes) {
            cell.setCellValue(new String(bytes, charset));
        } else if (value instanceof char[] chars) {
            cell.setCellValue(new String(chars));
        } else {
            return false;
        }
        return true;
    }

    private boolean writeOptional(XSSFCell cell, Object value) {
        if (value instanceof Optional<?> optional) {
            writeCell(cell, optional.orElse(null));
        } else if (value instanceof OptionalDouble optional) {
            optional.ifPresent(cell::setCellValue);
        } else if (value instanceof OptionalLong optional) {
            optional.ifPresent(val -> cell.setCellValue((double) val));
        } else if (value instanceof OptionalInt optional) {
            optional.ifPresent(cell::setCellValue);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Adds a column manually.
     */
    public <V> ExcelTemplate<T> addColumn(String header, Function<T, V> getter) {
        return this.addColumn(header, getter, -1);
    }

    /**
     * Adds a column manually.
     */
    public <V> ExcelTemplate<T> addColumn(String header, Function<T, V> getter, int width) {
        columns.add(new ColumnDescriptor<>(header, getter, width));
        return this;
    }

    public ExcelTemplate<T> charset(@NonNull Charset charset) {
        this.charset = charset;
        return this;
    }

    public ExcelTemplate<T> zoneId(@NonNull ZoneId zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    public record ColumnDescriptor<T, V>(@NonNull String header, @NonNull Function<T, V> getter, int width) {
    }
}
