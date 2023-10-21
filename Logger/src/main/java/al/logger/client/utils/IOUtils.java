package al.logger.client.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

public class IOUtils {
    public static InputStream toInputStream(String input, Charset encoding) {
        return new ByteArrayInputStream(input.getBytes(encoding));
    }
    public static InputStream toInputStream(String input) {
        return toInputStream(input, Charset.defaultCharset());
    }
}
