package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.mouse;

import com.reflectmc.reflect.wrapper.annotation.WrapEnum;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

@WrapperClass(deobfName = "net.minecraft.util.MovingObjectPosition$MovingObjectType",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.math.RayTraceResult$Type",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class MovingObjectType extends WrapperBase {
    public MovingObjectType(Object obj) {
        super(obj);
    }
    @WrapEnum(deobfName = "BLOCK",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "BLOCK",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static MovingObjectType BLOCK;
    @WrapEnum(deobfName = "MISS",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "MISS",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static MovingObjectType MISS;
    @WrapEnum(deobfName = "ENTITY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "ENTITY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static MovingObjectType ENTITY;

}
