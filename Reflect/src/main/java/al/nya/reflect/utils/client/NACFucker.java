package al.nya.reflect.utils.client;

import al.nya.reflect.transform.ReflectNative;
import by.radioegor146.nativeobfuscator.Native;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 操你妈nplus
 */
public class NACFucker {
    private static String NAC_RES_CLASS = "cc.nplus.Oo00ooOOO0OooOoo0OO0oOooOo";

    public static boolean NACDetect() {
        return getNAC() != null;
    }

    public static Map<String, String> decrypt() {
        Map<String, String> maps = new HashMap<>();
        Map<String, String> allMaps = getAllMaps();
        ReflectNative.log("Found " + allMaps.size() + " maps");
        allMaps.forEach((key, value) -> {
            ReflectNative.log("key:" + convert(key) + " value:" + convert(value));
            maps.put(convert(key), convert(value));
        });
        return maps;
    }

    private static String convert(Object o) {
        if (o instanceof Long) {
            return (long) o + "L";
        }
        if (o instanceof Double) {
            return (double) o + "D";
        }
        if (o instanceof Float) {
            return (float) o + "F";
        }
        return o.toString();
    }

    private static Map<String, String> getAllMaps() {
        List<List<?>> list = new ArrayList<>();
        Class<?> cls = getNACRes();
        for (Field declaredField : cls.getDeclaredFields()) {
            ReflectNative.log(declaredField.getName() + " " + declaredField.getType().getName());
            declaredField.setAccessible(true);
            if (declaredField.getType() == ArrayList.class) {
                ReflectNative.log("[!]List found " + declaredField.getName());
                Object obj = null;
                try {
                    obj = declaredField.get(null);
                } catch (IllegalAccessException e) {
                    ReflectNative.log(e.getMessage());
                }
                list.add((List<?>) obj);
            }
        }
        Map<String, String> maps = new HashMap<>();
        for (List<?> objects : list) {
            for (Object object : objects) {
                if (object instanceof Map) {
                    ((Map<?, ?>) object).forEach((key, value) -> {
                        ReflectNative.log(key + " -> " + value);
                        if (!key.toString().isEmpty()) {
                            maps.put(key.toString(), value.toString());
                        }
                    });
                }
            }
        }
        return maps;
    }

    public static Class<?> getNAC() {
        try {
            return Class.forName(NAC_RES_CLASS);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Class<?> getNACRes() {
        try {
            return Class.forName(NAC_RES_CLASS);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
