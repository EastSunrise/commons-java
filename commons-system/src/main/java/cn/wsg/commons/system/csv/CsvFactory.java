package cn.wsg.commons.system.csv;

import cn.wsg.commons.function.Getter;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A factory to provide operations of reading and writing between Java beans and data in format of
 * csv.
 *
 * @author Kingen
 */
public class CsvFactory {

    private final CSVFormat format;

    public CsvFactory() {
        this(CSVFormat.DEFAULT);
    }

    public CsvFactory(CSVFormat format) {
        this.format = format;
    }

    /**
     * Reads csv data from the input stream and stores in a list of beans.
     *
     * @param input       input stream of csv data
     * @param constructor a no-args constructor of beans
     * @param template    the structure of beans where the data will be stored
     * @param charset     charset of the stream
     * @return list of beans which store the data read from the stream
     */
    public <T> List<T> readCsv(InputStream input, Supplier<T> constructor, CsvTemplate<T> template, Charset charset)
        throws IOException {
        CSVFormat csvFormat = format;
        Map<String, CsvSetter<T>> setters = template.getSetters();
        if (MapUtils.isNotEmpty(setters)) {
            csvFormat = csvFormat.withHeader(setters.keySet().toArray(new String[0])).withFirstRecordAsHeader();
        }
        CSVParser parser = CSVParser.parse(input, charset, csvFormat);
        List<T> result = new ArrayList<>();
        for (CSVRecord record : parser) {
            T bean = constructor.get();
            for (Map.Entry<String, CsvSetter<T>> entry : setters.entrySet()) {
                CsvSetter<T> setter = entry.getValue();
                setter.set(bean, record.get(entry.getKey()));
            }
            result.add(bean);
        }
        return result;
    }

    /**
     * Writes the given beans to the output stream. Each value of properties of the beans is written
     * as a string.
     *
     * @param output   output stream
     * @param beans    beans to be written to the output stream
     * @param template the structure of beans to be written
     * @see CSVPrinter#print(Object)
     */
    public <T> void writeCsv(Appendable output, Iterable<T> beans, CsvTemplate<T> template) throws IOException {
        CSVPrinter printer = format.print(output);
        Map<String, Getter<T, ?>> getters = template.getGetters();
        printer.printRecord(getters.keySet());
        for (T t : beans) {
            for (Getter<T, ?> getter : getters.values()) {
                printer.print(getter.get(t));
            }
            printer.println();
        }
        printer.close(true);
    }
}
