package com.reflectmc.reflect.wrapper.wrappers.minecraft.block;

import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.block.material.Material",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.block.material.Material",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Material extends WrapperBase {
    @WrapField(deobfName = "water",targetEnvironment = {Environment.Forge189, Environment.Vanilla189})
    @WrapField(deobfName = "WATER",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field water;
    @WrapMethod(deobfName = "isReplaceable",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isReplaceable",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isReplaceable;
    @WrapMethod(deobfName = "isSolid",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isSolid",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isSolid;
    @WrapMethod(deobfName = "isLiquid",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isLiquid",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isLiquid;
    public Material(Object obj) {
        super(obj);
    }
    public static Material getWater(){
        return new Material(getStaticField(water));
    }
    public boolean isReplaceable(){
        return (boolean) invokeMethod(isReplaceable);
    }
    public boolean isSolid(){
        return (boolean) invokeMethod(isSolid);
    }
    public boolean isLiquid(){
        return (boolean) invokeMethod(isLiquid);
    }
}
