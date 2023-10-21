package al.nya.reflect.wrapper.utils;

import al.nya.reflect.transform.ReflectNative;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {

    public static List<Class<?>> getClasses(String pack) {
        ArrayList<Class<?>> list = new ArrayList<Class<?>>();
        for (Class<?> allLoadedClass : ReflectNative.getAllLoadedClasses()) {
            if (allLoadedClass.getCanonicalName().startsWith(pack)){
                list.add(allLoadedClass);
            }
        }
        return list;
    }
}