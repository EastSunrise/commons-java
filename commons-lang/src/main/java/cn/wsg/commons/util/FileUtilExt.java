package cn.wsg.commons.util;

import cn.wsg.commons.SystemConsts;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * Extension of {@link FileUtils} and {@link FilenameUtils}.
 *
 * @author Kingen
 */
public final class FileUtilExt {

    private FileUtilExt() {
    }

    /**
     * Copies the extension of the filename to append to the given basename. If the basename is null, the source
     * filename will be returned.
     *
     * @param filename the source filename
     * @param basename the basename to which the extension is appended
     * @return a new filename based on the basename and ending with the same extension, or the source filename if the
     * given basename is null
     */
    public static String copyExtension(String filename, String basename) {
        if (null == basename) {
            return filename;
        }
        String extension = FilenameUtils.getExtension(filename);
        if (!extension.isEmpty()) {
            extension = SystemConsts.EXTENSION_SEPARATOR + extension;
        }
        return basename + extension;
    }
}
