package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils;

import com.reflectmc.reflect.wrapper.annotation.*;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.EnumWrapper;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.util.EnumFacing",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.EnumFacing",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class EnumFacing extends EnumWrapper {
    @ClassInstance
    public static Class EnumFacingClass;
    @WrapField(deobfName = "directionVec",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "directionVec",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field directionVec;
    @WrapMethod(deobfName = "getOpposite",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getOpposite",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getOpposite;
    @WrapMethod(deobfName = "getFrontOffsetX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getFrontOffsetX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getFrontOffsetX;
    @WrapMethod(deobfName = "getFrontOffsetZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getFrontOffsetZ",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getFrontOffsetZ;
    public EnumFacing(Object obj) {
        super(obj);
    }

    public static EnumFacing[] values() {
        return new EnumFacing[]{DOWN,UP,NORTH,SOUTH,WEST,EAST};
    }

    @WrapEnum(deobfName = "DOWN",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "DOWN",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static EnumFacing DOWN;
    @WrapEnum(deobfName = "UP",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "UP",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static EnumFacing UP;
    @WrapEnum(deobfName = "NORTH",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "NORTH",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static EnumFacing NORTH;
    @WrapEnum(deobfName = "SOUTH",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "SOUTH",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static EnumFacing SOUTH;
    @WrapEnum(deobfName = "WEST",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "WEST",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static EnumFacing WEST;
    @WrapEnum(deobfName = "EAST",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapEnum(deobfName = "EAST",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static EnumFacing EAST;

    public Vec3i getDirectionVec(){
        return new Vec3i(getField(directionVec));
    }
    public EnumFacing getOpposite(){
        return new EnumFacing(invokeMethod(getOpposite));
    }
    public int getFrontOffsetX(){
        return (int) invokeMethod(getFrontOffsetX);
    }
    public int getFrontOffsetZ(){
        return (int) invokeMethod(getFrontOffsetZ);
    }
}
