package al.nya.reflect.utils.client;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DebugUtils {
    public static boolean isDebugging() {
        try {
            Class.forName("al.nya.reflect.debugger.Loader");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static Class defineClass(byte[] classBytes) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        //String name, byte[] b, int off, int len
        try {
            Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
            defineClass.setAccessible(true);
            Object obj = defineClass.invoke(cl, classBytes, 0, classBytes.length);
            return (Class<?>) obj;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void transformClasses() {
        try {
            Class MinecraftLoadingChecker = Class.forName("al.nya.reflect.debugger.MinecraftLoadingChecker");
            Method transformClasses = MinecraftLoadingChecker.getDeclaredMethod("transformClasses");
            transformClasses.setAccessible(true);
            transformClasses.invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
