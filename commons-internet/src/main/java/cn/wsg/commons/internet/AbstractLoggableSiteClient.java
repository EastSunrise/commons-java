package cn.wsg.commons.internet;

import cn.wsg.commons.SystemConsts;
import cn.wsg.commons.internet.support.SiteHelper;
import cn.wsg.commons.util.StringUtilsExt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.*;

/**
 * This class provides a skeletal implementation of a loggable site whose cookies that containing
 * logon information will be stored locally as a browser does.
 *
 * @author Kingen
 */
@Slf4j
public abstract class AbstractLoggableSiteClient<U> extends BaseSiteClient implements Loggable<U> {

    private static final File TMPDIR = new File(SystemConsts.SYSTEM_TMPDIR);

    protected AbstractLoggableSiteClient(String name, HttpHost host) {
        super(name, host, SiteHelper.defaultClient(), loadContext(host));
    }

    protected AbstractLoggableSiteClient(String name, HttpHost host, CloseableHttpClient client,
        HttpClientContext context) {
        super(name, host, client, context);
    }

    protected static HttpClientContext loadContext(HttpHost host) {
        HttpClientContext context = SiteHelper.defaultContext();
        String filepath = StringUtilsExt.toFilename(host.toString()) + ".cookie";
        File file = new File(StringUtils.joinWith(File.separator, TMPDIR, "context", filepath));
        if (file.canRead()) {
            try (ObjectInputStream stream = new ObjectInputStream(FileUtils.openInputStream(file))) {
                log.trace("Read cookies from {}.", file.getPath());
                CookieStore cookieStore = (CookieStore)stream.readObject();
                context.setCookieStore(cookieStore);
            } catch (IOException | ClassNotFoundException e) {
                log.warn("Failed to load context of {}", host.toString());
            }
        }
        return context;
    }

    @Override
    public void close() throws IOException {
        String filepath = StringUtilsExt.toFilename(this.getHost().toString()) + ".cookie";
        File file = new File(StringUtils.joinWith(File.separator, TMPDIR, "context", filepath));
        try (ObjectOutput stream = new ObjectOutputStream(FileUtils.openOutputStream(file))) {
            log.trace("Synchronize cookies of {}.", this.getHost());
            stream.writeObject(this.getContext().getCookieStore());
        }
        super.close();
    }
}
