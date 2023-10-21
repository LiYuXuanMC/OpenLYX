package al.logger.client.utils;

import al.logger.client.wrapper.map.utils.Signature;
import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectUtil {
    public static <T extends Annotation> T getAnnotation(Method method,Class<T> target){
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            if (annotation.annotationType() == target)
                return (T) annotation;
        }
        return null;
    }
    public static Object callMethod(String className, String methodName, Object object, Object... args) {
        try {
            Class clazz = Class.forName(className);
            Method method = null;
            for (Method declaredMethod : clazz.getDeclaredMethods()) {
                if (declaredMethod.getName().equals(methodName)) method = declaredMethod;
            }
            if (method == null) {
                throw new NoSuchMethodException();
            }
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Field findField(String className,String fieldName){
        try {
            Class clazz = Class.forName(className);
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.getName().equals(fieldName))
                    return declaredField;
            }
            for (Field field : clazz.getFields()) {
                if (field.getName().equals(fieldName))
                    return field;
            }
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Object getField(String className,String fieldName,Object obj){
        Field field = findField(className, fieldName);
        if (!field.isAccessible())
            field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Field findField(Class clazz,String fieldName){
        for (Field declaredField : clazz.getDeclaredFields()) {
            if (declaredField.getName().equals(fieldName))
                return declaredField;
        }
        for (Field field : clazz.getFields()) {
            if (field.getName().equals(fieldName))
                return field;
        }
        return null;
    }
    @SneakyThrows
    public static Constructor findConstructor(Class<?> target, Class... args){
        return target.getConstructor(args);
    }
    public static Method findMethod(String className, String methodName, Class<?>[] args){
        try {
            Class clazz = Class.forName(className);
            for (Method declaredMethod : clazz.getDeclaredMethods()) {
               if (declaredMethod.getName().equals(methodName) && Arrays.equals(declaredMethod.getParameterTypes(),args)){
                   return declaredMethod;
               }
            }
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals(methodName) && Arrays.equals(method.getParameterTypes(),args)){
                    return method;
                }
            }
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
