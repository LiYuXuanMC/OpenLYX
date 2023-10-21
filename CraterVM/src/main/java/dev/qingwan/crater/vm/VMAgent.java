package dev.qingwan.crater.vm;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.security.ProtectionDomain;

public class VMAgent {
    @SneakyThrows
    public static Class<?> defineClass(String name,byte[] bytes){
        ClassLoader cl = VMAgent.class.getClassLoader();
        Method method = cl.getClass().getDeclaredMethod("defineClass",String.class,byte[].class,int.class,int.class,ProtectionDomain.class);
        method.setAccessible(true);
        Class clazz = (Class) method.invoke(name,bytes,0,bytes.length,null);
        return clazz;
    }
}
