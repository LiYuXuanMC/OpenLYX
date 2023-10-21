package com.reflectmc.reflect.wrapper.wrappers.minecraft.world;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text.IChatComponent;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.world.IWorldNameable",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.world.IWorldNameable",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class IWorldNameable extends WrapperBase {
    @WrapMethod(deobfName = "getDisplayName",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getDisplayName",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getDisplayName;
    public IWorldNameable(Object obj) {
        super(obj);
    }
    public IChatComponent getDisplayName(){
        return new IChatComponent(invokeMethod(getDisplayName));
    }
}
