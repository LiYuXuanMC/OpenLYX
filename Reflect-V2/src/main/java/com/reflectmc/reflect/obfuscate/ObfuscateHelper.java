package com.reflectmc.reflect.obfuscate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObfuscateHelper {
    private static final Map<String,Method> foundMethod = new HashMap<>();
    public static Method findObfMethod(Class<?> clazz,String name){
        if (foundMethod.containsKey(name)){
            return foundMethod.get(name);
        }
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            for (Annotation declaredAnnotation : declaredMethod.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof ExportObfuscate){
                    if (((ExportObfuscate) declaredAnnotation).name().equals(name)){
                        declaredMethod.setAccessible(true);
                        foundMethod.put(name,declaredMethod);
                        return declaredMethod;
                    }
                }
            }
        }
        return null;
    }
}
