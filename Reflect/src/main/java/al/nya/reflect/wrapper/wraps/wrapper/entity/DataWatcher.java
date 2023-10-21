package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.entity.DataWatcher", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.datasync.EntityDataManager", targetMap = Maps.Srg1_12_2)
public class DataWatcher extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.entity.DataWatcher", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.datasync.EntityDataManager", targetMap = Maps.Srg1_12_2)
    public static Class<?> DataWatcherClass;
    @WrapMethod(mcpName = "getWatchableObjectByte", targetMap = Maps.Srg1_8_9)
    public static Method getWatchableObjectByte;
    @WrapMethod(mcpName = "updateObject", targetMap = Maps.Srg1_8_9)
    public static Method updateObject;
    public DataWatcher(Object obj) {
        super(obj);
    }

    public void updateObject(int index, Object value) {
        if (Wrapper.env.equals(Maps.Srg1_12_2)) {
            throw new UnsupportedOperationException("1.8.9 does not support this method");
        }
        invoke(updateObject, index, value);
    }

    public byte getWatchableObjectByte(int i1) {
        if (Wrapper.env.equals(Maps.Srg1_12_2)) {
            throw new UnsupportedOperationException("1.8.9 does not support this method");
        }
        return (byte) invoke(getWatchableObjectByte, i1);
    }

    public static boolean isDataWatcher(Object obj) {
        return DataWatcherClass.isInstance(obj);
    }
}
