package al.nya.reflect.wrapper.mapper;

import al.nya.reflect.transform.ReflectNative;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Mapper {
    public Mapper() {

    }

    public void input(byte[] deobfJarBytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(deobfJarBytes);
        ZipInputStream zip = new ZipInputStream(bais);
        List<byte[]> classes = new ArrayList<>();
        try {
            ZipEntry entry = zip.getNextEntry();
            while (entry != null) {
                if (entry.getName().endsWith(".class")) {
                    ByteArrayOutputStream ot = new ByteArrayOutputStream();
                    while (zip.available() != 0) {
                        ot.write(zip.read());
                    }
                    classes.add(ot.toByteArray());
                    ot.close();
                }
                entry = zip.getNextEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReflectNative.log("[Mapper] loadING " + classes.size() + " class(es) in deobf jar");
    }
}
