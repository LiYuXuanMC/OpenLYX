package al.logger.client.utils.obfuscate;

import al.logger.client.utils.ReflectUtil;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ObfuscateHelper {
    public static Method targetObfuscatedMethod(Class<?> targetClass,String exportName){
        for (Method declaredMethod : targetClass.getDeclaredMethods()) {
            ExportObfuscate eo = ReflectUtil.getAnnotation(declaredMethod, ExportObfuscate.class);
            if (eo == null){
                continue;
            }
            if (eo.name().equals(exportName)){
                return declaredMethod;
            }
        }
        return null;
    }
    public static Field targetObfuscatedEnum(Enum<?> e){
        Class<?> target = e.getClass();
        for (Field declaredField : target.getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
                Object o = declaredField.get(null);
                if (o == e){
                    return declaredField;
                }
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        }
        return null;
    }
}
