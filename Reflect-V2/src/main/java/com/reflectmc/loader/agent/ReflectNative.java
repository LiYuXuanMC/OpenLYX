package com.reflectmc.loader.agent;

import com.reflectmc.builder.annotation.ExportName;

@ExportName(export = "OBF/Native")
public class ReflectNative {
    @ExportName(export = "OBF/Native/startInject")
    public static native void startInject(ClassLoader classLoader);
    @ExportName(export = "OBF/Native/transform")
    public static native int transform(Class<?> target);
    @ExportName(export = "OBF/Native/logToNative")
    public static native void logToNative(String str);
    public static Class<?> defineClass(ClassLoader classLoader,byte[] bytes){
        return defineClass0(classLoader,bytes);
    }
    @ExportName(export = "OBF/Native/defineClass0")
    private static native Class<?> defineClass0(ClassLoader classLoader,byte[] bytes);
}
