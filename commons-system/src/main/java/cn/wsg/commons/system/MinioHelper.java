package cn.wsg.commons.system;

import cn.wsg.commons.lang.WebConsts;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Helper to upload resources to MinIO.
 *
 * @author Kingen
 * @see <a href="https://docs.min.io/cn/minio-quickstart-guide.html">MinIO Quickstart Guide</a>
 */
@Slf4j
public final class MinioHelper {

    private static final long TEN_MB = 10485760;
    private static final String NO_SUCH_KEY = "NoSuchKey";

    private MinioHelper() {
    }

    /**
     * Uploads an object located by the specified url to MinIO. If the server previously contained the object, the old
     * object is replaced by the new one.
     *
     * @param client client used to upload the object
     * @param url    url to locate the object
     * @return the path to access the object stored in the server
     * @throws NullPointerException if any argument is {@code null}
     */
    public static String upload(MinioClient client, URL url)
        throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
        NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        String bucketName = url.getHost(), objectName = url.getFile();
        boolean exists = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            log.info("Creates a new bucket {}", bucketName);
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        try (InputStream stream = url.openStream()) {
            PutObjectArgs.Builder builder = PutObjectArgs.builder().bucket(bucketName).object(objectName);
            builder.contentType(URLConnection.getFileNameMap().getContentTypeFor(url.getFile()));
            log.info("Uploading {} to MinIO server.", url);
            ObjectWriteResponse response = client.putObject(builder.stream(stream, -1, TEN_MB).build());
            return WebConsts.URL_PATH_SEPARATOR + response.bucket() + response.object();
        }
    }

    /**
     * Uploads the object located by the specified url to MinIO if the server didn't previously contain the object.
     *
     * @param client client used to upload the object
     * @param url    url to locate the object
     * @return the path to access the object (previous or new)
     * @throws NullPointerException if any argument is {@code null}
     */
    public static String uploadIfAbsent(MinioClient client, URL url)
        throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
        NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        String bucketName = url.getHost(), objectName = url.getFile();
        boolean exists = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            log.info("Creates a new bucket {}", bucketName);
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } else {
            StatObjectArgs statObjectArgs = StatObjectArgs.builder().bucket(bucketName).object(objectName).build();
            try {
                return handleResponse(client.statObject(statObjectArgs));
            } catch (ErrorResponseException e) {
                if (!NO_SUCH_KEY.equals(e.errorResponse().code())) {
                    throw e;
                }
            }
        }

        try (InputStream stream = url.openStream()) {
            PutObjectArgs.Builder builder = PutObjectArgs.builder().bucket(bucketName).object(objectName);
            builder.contentType(URLConnection.getFileNameMap().getContentTypeFor(url.getFile()));
            log.info("Uploading {} to MinIO server.", url);
            return handleResponse(client.putObject(builder.stream(stream, -1, TEN_MB).build()));
        }
    }

    private static String handleResponse(GenericResponse response) {
        return WebConsts.URL_PATH_SEPARATOR + response.bucket() + response.object();
    }
}
