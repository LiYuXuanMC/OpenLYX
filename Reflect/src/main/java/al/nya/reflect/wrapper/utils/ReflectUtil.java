package al.nya.reflect.wrapper.utils;

import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {
    public static Object getField(Field f,Object o){
        try {
            return f.get(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void setField(Field f,Object v,Object o){
        try {
            f.set(o,v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Object invoke(Method m,Object o,Object... args){
        try {
            return m.invoke(o,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Deprecated
    public static Object construction(Class<?> classIn,Object... args){
        Class<?>[] arg = new Class<?>[args.length];
        int i = 0;
        for (Object o : args) {
            arg[i] = o.getClass();
            i++;
        }
        try {
            Constructor<?> constructor = classIn.getConstructor(arg);
            return constructor.newInstance(args);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
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

    public static Object getField(String className, String fieldName, Object object) {
        try {
            Class clazz = Class.forName(className);
            Field field = null;
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.getName().equals(fieldName)) field = declaredField;
            }
            if (field == null) {
                throw new NoSuchFieldException();
            }
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object construction(Constructor<?> constructor, Object... args) {
        try {
            return constructor.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
