package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils;

import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.util.Vec3i",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.math.Vec3i",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Vec3i extends WrapperBase {
    @WrapField(deobfName = "x",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "x",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field x;
    @WrapField(deobfName = "y",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "y",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field y;
    @WrapField(deobfName = "z",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "z",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field z;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {double.class,double.class,double.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {double.class,double.class,double.class})
    public static Constructor Vec3i_DDD;
    @WrapMethod(deobfName = "distanceSq",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(DDD)D")
    @WrapMethod(deobfName = "distanceSq",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(DDD)D")
    public static Method distanceSq;

    public Vec3i(Object obj) {
        super(obj);
    }
    public Vec3i(double xIn, double yIn, double zIn)
    {
        this(construct(Vec3i_DDD,xIn,yIn,zIn));
    }

    public int getX() {
        return (int) getField(x);
    }

    public int getY() {
        return (int) getField(y);
    }

    public int getZ() {
        return (int) getField(z);
    }

    public double distanceTo(double posX, double posY, double posZ) {
        return (double) invokeMethod(distanceSq,posX,posY,posZ);
    }
}
