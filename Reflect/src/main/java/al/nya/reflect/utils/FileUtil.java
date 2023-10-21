package al.nya.reflect.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;

public class FileUtil {
    public static void writeFile(File file, byte[] bytes) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
    }

    public static File createJsonFile(File dir, String name) {
        return new File(dir, name + ".json");
    }

    public static String readInputStream(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null)
                stringBuilder.append(line).append('\n');

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static byte[] readFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        return bytes;
    }

    public static void closeReader(FileReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Error closing reader: IO exception");
        }
    }

    public static FileReader createReader(File file) {
        if (file.exists()) {
            try {
                return new FileReader(file);
            } catch (FileNotFoundException e) {
                System.out.println("Error creating reader: File not found exception");
            }
        }
        return null;
    }

    public static File recreateFile(File file) {
        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("IOException thrown, could not recreate file.");
        }

        return file;
    }

    public static void saveJsonFile(File file, JsonObject jsonObject) {
        try {
            //file.createNewFile();
            FileWriter writer = new FileWriter(file);
            Throwable throwable = null;
            try {
                writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            } catch (Throwable var6_9) {
                throwable = var6_9;
                throw var6_9;
            } finally {
                if (throwable != null) {
                    try {
                        writer.close();
                    } catch (Throwable var6_8) {
                        throwable.addSuppressed(var6_8);
                    }
                } else {
                    writer.close();
                }
            }
        } catch (IOException e) {
            file.delete();
        }
    }

    public static byte[] readStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (is.available() != 0) {
            baos.write(is.read());
        }
        is.close();
        byte[] bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }

    public static byte[] readLClass(String name) throws IOException {
        InputStream is = ClassLoader.getSystemResourceAsStream(name + ".lclass");
        return readStream(is);
    }

    public static InputStream readByte(byte[] b) {
        return new ByteArrayInputStream(b);
    }
}
