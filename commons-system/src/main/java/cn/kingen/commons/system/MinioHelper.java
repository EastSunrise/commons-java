package cn.kingen.commons.system;


import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import lombok.extern.slf4j.Slf4j;

/**
 * Helper to save resources to MinIO.
 *
 * @author Kingen
 * @see <a href="https://min.io/docs/minio/linux/developers/java/minio-java.html#minio-java-quickstart">Java Quickstart
 * Guide</a>
 */
@Slf4j
public final class MinioHelper {

    public static final String PATH_SEPARATOR = "/";
    private static final long TEN_MB = 10485760;

    public static Proxy proxy;

    private MinioHelper() {
    }

    /**
     * Saves an object located by the specified url to MinIO. If the server previously contained the object, the old
     * object is replaced by the new one.
     *
     * @param client      client used to access MinIO
     * @param url         location of the object
     * @param bucketName  bucket where the object is saved
     * @param objectName  path where the object is saved in the bucket
     * @param contentType content-type of the object
     * @return the path to access the object stored in the server
     */
    public static String save(MinioClient client, URL url, String bucketName, String objectName, String contentType)
        throws IOException, GeneralSecurityException, MinioException {
        if (objectName == null) {
            objectName = url.getHost() + url.getFile();
        }
        boolean exists = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            log.debug("Creates a new bucket: {}", bucketName);
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        PutObjectArgs.Builder builder = PutObjectArgs.builder().bucket(bucketName).object(objectName);
        if (contentType == null) {
            contentType = URLConnection.getFileNameMap().getContentTypeFor(url.getPath());
        }
        if (contentType != null) {
            builder.contentType(contentType);
        }
        if (proxy == null) {
            try (InputStream stream = url.openStream()) {
                log.debug("Saving {} to MinIO server.", url);
                ObjectWriteResponse response = client.putObject(builder.stream(stream, -1, TEN_MB).build());
                return PATH_SEPARATOR + response.bucket() + PATH_SEPARATOR + response.object();
            }
        } else {
            try (InputStream stream = url.openConnection(proxy).getInputStream()) {
                log.debug("Saving {} to MinIO server.", url);
                ObjectWriteResponse response = client.putObject(builder.stream(stream, -1, TEN_MB).build());
                return PATH_SEPARATOR + response.bucket() + PATH_SEPARATOR + response.object();
            }
        }
    }
}
