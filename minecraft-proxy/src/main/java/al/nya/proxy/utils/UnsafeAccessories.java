package al.nya.proxy.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeAccessories {
    private final static Unsafe unsafe;

    public static Object allocInstance(Class<?> targetClass){
        try {
            return unsafe.allocateInstance(targetClass);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
