package al.logger.client.wrapper;

import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import lombok.Getter;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class IWrapper {
    private Object wrappedObject;

    public IWrapper(Object obj){
        this.wrappedObject = obj;
    }
    public Object getField(Field f){
        try {
            return f.get(wrappedObject);
        } catch (Exception e) {
            return null;
        }
    }
    public void setField(Field f,Object v){
        try {
            f.set(wrappedObject,v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Object getStatic(Field f){
        try {
            return f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Object invoke(Method m, Object... args){
        try {
            return m.invoke(wrappedObject,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Deprecated
    /**
     * 使用请仔细检查参数
     * 效率问题 不会包含object
     */
    public static Object invoke(MethodHandle m, Object... args){
        try {
            return m.invoke(args);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static Object invokeStatic(Method m,Object... args){
        try {
            return m.invoke(null,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Object construction(Constructor<?> constructor, Object... args) {
        try {
            return constructor.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean isNull(){
        return Objects.isNull(wrappedObject);
    }
    public boolean equals(IWrapper wrapper){
        return Objects.equals(wrappedObject,wrapper.getWrappedObject());
    }
    @ExportObfuscate(name = "getWrappedObject")
    public Object getWrappedObject() {
        return wrappedObject;
    }
}
