package com.reflectmc.reflect.wrapper.wrappers.minecraft.item;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.inventory.Container",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.inventory.Container",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Container extends WrapperBase {
    @WrapMethod(deobfName = "getSlot",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getSlot",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getSlot;
    @WrapField(deobfName = "windowId",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "windowId",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field windowId;
    public Container(Object obj) {
        super(obj);
    }
    public Slot getSlot(int i){
        return new Slot(invokeMethod(getSlot,i));
    }
    public int getWindowId(){
        return (int) getField(windowId);
    }
}
