package al.nya.proxy.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ReflectUtils {
    public static boolean hasAnnotation(Field field, Class<? extends Annotation> annotation) {
        return field.getAnnotation(annotation) != null;
    }
    public static <T extends Annotation> T getAnnotation(Field field, Class<T> annotation) {
        return field.getAnnotation(annotation);
    }
    public static boolean hasAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        return clazz.getAnnotation(annotation) != null;
    }
    public static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotation) {
        return clazz.getAnnotation(annotation);
    }
}
