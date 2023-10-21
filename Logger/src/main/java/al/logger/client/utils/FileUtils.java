package al.logger.client.utils;

import lombok.SneakyThrows;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {
    @SneakyThrows
    public static byte[] readStream(InputStream stream) {
        byte[] bytes = new byte[stream.available()];
        stream.read(bytes);
        stream.close();
        return bytes;
    }
    public static boolean writeFile(File f,byte[] bytes){
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bytes);
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @SneakyThrows
    public static Map<String,byte[]> processZip(byte[] zipFile){
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new ByteArrayInputStream((zipFile))));
        System.out.println(zipFile.length);
        Map<String,byte[]> files = new HashMap<>();
        ZipEntry entry = null;
        entry = zis.getNextEntry();
        while (entry != null){
            int len;
            byte[] buffer = new byte[2048];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = zis.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            files.put(entry.getName(),bos.toByteArray());
            entry = zis.getNextEntry();
        }
        return files;
    }
    public static String readFile(File file){
        StringBuilder result = new StringBuilder();
        try {
            FileInputStream fIn = new FileInputStream(file);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fIn))) {
                String str;
                while((str = bufferedReader.readLine()) != null){
                    result.append(str);
                    result.append(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


}
