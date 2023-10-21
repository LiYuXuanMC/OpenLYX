package al.logger.client.utils.network;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class WebUtil {

    public static Logger LOGGER = LogManager.getLogger(WebUtil.class);

    @Getter
    @Setter
    private static Proxy proxy = Proxy.NO_PROXY;
    private String baseUrl;

    public WebUtil(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public byte[] get(String header, Connect... connects) throws IOException {
        URL url = new URL(this.baseUrl + header);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
        connection.setRequestMethod("GET");
        for (Connect connect : connects) {
            connection.setRequestProperty(connect.getHeader(), connect.getBody());
        }
        connection.connect();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        InputStream inputStream = connection.getInputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        inputStream.close();
        connection.disconnect();
        return byteArrayOutputStream.toByteArray();
    }

}
