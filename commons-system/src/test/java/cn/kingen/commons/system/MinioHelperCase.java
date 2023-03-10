package cn.kingen.commons.system;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import org.junit.jupiter.api.Test;

/**
 * @author Kingen
 */
class MinioHelperCase {

    @Test
    void save() throws IOException, MinioException, GeneralSecurityException {
        URL url = new URL("https://www.baidu.com/img/flexible/logo/pc/result.png");
        MinioClient client = MinioClient.builder().endpoint("127.0.0.1", 9000, false)
            .credentials("minioadmin", "minioadmin")
            .build();
        String path = MinioHelper.save(client, url, "repository");
        System.out.println(path);
    }

    @Test
    void saveIfAbsent() throws MinioException, IOException, GeneralSecurityException {
        URL url = new URL("https://www.baidu.com/img/flexible/logo/pc/result.png");
        MinioClient client = MinioClient.builder().endpoint("127.0.0.1", 9000, false)
            .credentials("minioadmin", "minioadmin")
            .build();
        String path = MinioHelper.saveIfAbsent(client, url, "repository");
        System.out.println(path);
    }
}