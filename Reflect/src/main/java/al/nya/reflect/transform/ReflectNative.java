package al.nya.reflect.transform;

import al.nya.reflect.utils.client.DebugUtils;

public class ReflectNative {

    public static native byte[] getClassBytes(Class<?> clz);

    public static native int redefineClass(Class<?> clz, byte[] bytes);

    public static native Class<?>[] getAllLoadedClasses();

    public static native int retransform(Class<?> clz, ClassTransformer transformer);

    public static native Class<?> defineClass1(ClassLoader classLoader, byte[] bytes);

    public static native boolean setState(int type, boolean state);

    public static native void log(String log);

    public static Class defineClass(ClassLoader classLoader, byte bytes[]) {
        if (DebugUtils.isDebugging()) {
            return DebugUtils.defineClass(bytes);
        }
        return defineClass1(classLoader, bytes);
    }
}
