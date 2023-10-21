package com.reflectmc.reflect.wrapper.wrappers.minecraft.item;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.inventory.Slot",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.inventory.Slot",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Slot extends WrapperBase {
    @WrapMethod(deobfName = "getHasStack",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getHasStack",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getHasStack;
    @WrapMethod(deobfName = "getStack",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getStack",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getStack;
    public Slot(Object obj) {
        super(obj);
    }
    public boolean getHasStack(){
        return (boolean) invokeMethod(getHasStack);
    }
    public ItemStack getStack(){
        return new ItemStack(invokeMethod(getStack));
    }
}
