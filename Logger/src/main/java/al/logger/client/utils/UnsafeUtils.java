package al.logger.client.utils;

import al.logger.client.features.modules.SubModuleKeyBase;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtils {
    private static Unsafe unsafe;

    public static <T extends Object> T allocObject(Class<T> clazz){
        try {
            return (T) unsafe.allocateInstance(clazz);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeFinal(Field field,Object object,Object value){
        try {
            unsafe.putObject(object,unsafe.objectFieldOffset(field),value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Enum buildEnum(String name,int ordinal){
        SubModuleKeyBase emptyEnum = UnsafeUtils.allocObject(SubModuleKeyBase.class);
        Field nameField = ReflectUtil.findField(Enum.class,"name");
        Field ordinalField = ReflectUtil.findField(Enum.class,"ordinal");
        UnsafeUtils.writeFinal(nameField,emptyEnum,name);
        UnsafeUtils.writeFinal(ordinalField,emptyEnum,ordinal);
        return emptyEnum;
    }
    static {
        unsafe = (Unsafe) ReflectUtil.getField("sun.misc.Unsafe","theUnsafe",null);
    }
}
