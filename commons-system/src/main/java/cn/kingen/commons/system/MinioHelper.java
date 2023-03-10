package cn.kingen.commons.system;


import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.io.InputStream;
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
    private static final String NO_SUCH_KEY = "NoSuchKey";

    private MinioHelper() {
    }

    /**
     * Saves an object located by the specified url to MinIO. If the server previously contained the object, the old
     * object is replaced by the new one.
     *
     * @param client     client used to access MinIO
     * @param url        location of the object
     * @param bucketName bucket where the object is saved
     * @return the path to access the object stored in the server
     */
    public static String save(MinioClient client, URL url, String bucketName)
        throws IOException, GeneralSecurityException, MinioException {
        return save(client, url, bucketName, url.getHost() + url.getFile());
    }

    /**
     * Saves an object located by the specified url to MinIO. If the server previously contained the object, the old
     * object is replaced by the new one.
     *
     * @param client     client used to access MinIO
     * @param url        location of the object
     * @param bucketName bucket where the object is saved
     * @param objectName path where the object is saved in the bucket
     * @return the path to access the object stored in the server
     */
    public static String save(MinioClient client, URL url, String bucketName, String objectName)
        throws IOException, GeneralSecurityException, MinioException {
        boolean exists = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            log.debug("Creates a new bucket: {}", bucketName);
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        try (InputStream stream = url.openStream()) {
            PutObjectArgs.Builder builder = PutObjectArgs.builder().bucket(bucketName).object(objectName);
            builder.contentType(URLConnection.getFileNameMap().getContentTypeFor(url.getFile()));
            log.debug("Saving {} to MinIO server.", url);
            ObjectWriteResponse response = client.putObject(builder.stream(stream, -1, TEN_MB).build());
            return PATH_SEPARATOR + response.bucket() + PATH_SEPARATOR + response.object();
        }
    }

    /**
     * Saves the object located by the specified url to MinIO if the server didn't previously contain the object.
     *
     * @param client     client used to access MinIO
     * @param url        location of the object
     * @param bucketName bucket where the object is saved
     * @return the path to access the object (previous or new)
     */
    public static String saveIfAbsent(MinioClient client, URL url, String bucketName)
        throws IOException, GeneralSecurityException, MinioException {
        String objectName = url.getHost() + url.getFile();
        boolean exists = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            log.debug("Creates a new bucket {}", bucketName);
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } else {
            StatObjectArgs statObjectArgs = StatObjectArgs.builder().bucket(bucketName).object(objectName).build();
            try {
                StatObjectResponse response = client.statObject(statObjectArgs);
                return PATH_SEPARATOR + response.bucket() + PATH_SEPARATOR + response.object();
            } catch (ErrorResponseException e) {
                if (!NO_SUCH_KEY.equals(e.errorResponse().code())) {
                    throw e;
                }
            }
        }

        try (InputStream stream = url.openStream()) {
            PutObjectArgs.Builder builder = PutObjectArgs.builder().bucket(bucketName).object(objectName);
            builder.contentType(URLConnection.getFileNameMap().getContentTypeFor(url.getFile()));
            log.debug("Saving {} to MinIO server.", url);
            ObjectWriteResponse response = client.putObject(builder.stream(stream, -1, TEN_MB).build());
            return PATH_SEPARATOR + response.bucket() + PATH_SEPARATOR + response.object();
        }
    }
}
