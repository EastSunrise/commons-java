package cn.kingen.commons.internet.support;

import cn.kingen.commons.lang.SystemConsts;
import cn.kingen.commons.lang.util.StringUtilsExt;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.cache.HttpCacheEntry;
import org.apache.http.client.cache.HttpCacheStorage;
import org.apache.http.client.cache.HttpCacheUpdateCallback;

/**
 * Stores caches in the file system.
 *
 * @author Kingen
 */
@Slf4j
public class FileHttpCacheStorage implements HttpCacheStorage {

    @Override
    public void putEntry(String key, HttpCacheEntry entry) throws IOException {
        // todo variant entry
        if (SiteHelper.isResponseError(entry.getStatusCode())) {
            log.warn("Can't write an error entry.");
            return;
        }
        log.info("Writing an entry: {}", key);
        File file = getFile(key);
        try (ObjectOutputStream oos = new ObjectOutputStream(FileUtils.openOutputStream(file))) {
            oos.writeObject(entry);
        }
    }

    @Override
    public HttpCacheEntry getEntry(String key) throws IOException {
        File file = getFile(key);
        if (!file.exists()) {
            return null;
        }
        log.info("Reading an entry: {}", key);
        try (ObjectInputStream ois = new ObjectInputStream(FileUtils.openInputStream(file))) {
            return (HttpCacheEntry)ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void removeEntry(String key) throws IOException {
        log.info("Removing an entry: {}", key);
        File file = getFile(key);
        if (file.isFile()) {
            if (!file.delete()) {
                throw new IOException("Failed to delete the file: " + file);
            }
        }
    }

    @Override
    public void updateEntry(String key, HttpCacheUpdateCallback callback) throws IOException {
        log.info("Updating an entry: {}", key);
        File file = getFile(key);
        try (ObjectInputStream ois = new ObjectInputStream(FileUtils.openInputStream(file));
            ObjectOutputStream oos = new ObjectOutputStream(FileUtils.openOutputStream(file))) {
            oos.writeObject(callback.update((HttpCacheEntry)ois.readObject()));
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    private File getFile(String key) {
        return new File(SystemConsts.SYSTEM_TMPDIR, StringUtilsExt.toFilename(key + ".cache"));
    }
}
