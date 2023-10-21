package com.reflectmc.reflect.wrapper.wrappers;

import lombok.Getter;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class WrapperBase {
    @Getter
    private Object wrappedObject;
    public WrapperBase(Object wrap){
        this.wrappedObject = wrap;
    }
    public boolean isNull(){
        return Objects.isNull(wrappedObject);
    }
    public static Object getStaticField(Field field){
        try {
            return field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object construct(Constructor constructor,Object... args){
        try {
            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Object getField(Field field){
        try {
            return field.get(wrappedObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setField(Field field,Object v){
        try {
            field.set(wrappedObject,v);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static Object invokeStaticMethod(Method handle,Object... args){
        try {
            return handle.invoke(null,args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
    public Object invokeMethod(Method handle, Object... args){
        try {
            return handle.invoke(wrappedObject,args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean equals(WrapperBase wrapper){
        return Objects.equals(getWrappedObject(),wrapper.wrappedObject);
    }
}
