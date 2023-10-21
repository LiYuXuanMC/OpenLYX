package com.reflectmc.reflect.utils;

import com.reflectmc.loader.agent.ReflectNative;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassUtil {
    public static Class defineClass(byte[] classBytes) {
        return ReflectNative.defineClass(Thread.currentThread().getContextClassLoader(), classBytes);
    }
}
