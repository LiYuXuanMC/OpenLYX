package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils;

import com.reflectmc.reflect.wrapper.annotation.*;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.util.Vec3",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.math.Vec3d",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class Vec3 extends WrapperBase {
    @ClassInstance
    public static Class Vec3Class;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {double.class,double.class,double.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {double.class,double.class,double.class})
    public static Constructor Vec3_DDD;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = {Vec3i.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = {Vec3i.class})
    public static Constructor Vec3_Vec3i;
    @WrapMethod(deobfName = "addVector", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "addVector", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method addVector;
    @WrapField(deobfName = "xCoord", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "x", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field xCoord;
    @WrapMethod(deobfName = "dotProduct", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "dotProduct", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method dotProduct;
    @WrapField(deobfName = "yCoord", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "y", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field yCoord;
    @WrapField(deobfName = "zCoord", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "z", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field zCoord;
    @WrapMethod(deobfName = "add", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "add", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method add;
    @WrapMethod(deobfName = "squareDistanceTo", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "squareDistanceTo",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(Lnet/minecraft/util/math/Vec3d;)D")
    public static Method squareDistanceTo;
    @WrapMethod(deobfName = "distanceTo",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "distanceTo",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method distanceTo;
    @WrapMethod(deobfName = "subtract", targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = "(Lnet/minecraft/util/Vec3;)Lnet/minecraft/util/Vec3;")
    @WrapMethod(deobfName = "subtract", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122}, signature = "(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;")
    public static Method subtract_Vec;
    @WrapMethod(deobfName = "lengthVector", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "lengthVector", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method lengthVector;
    @WrapMethod(deobfName = "rotatePitch", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "rotatePitch", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method rotatePitch;
    @WrapMethod(deobfName = "rotateYaw", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "rotateYaw", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method rotateYaw;
    @WrapMethod(deobfName = "normalize", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "normalize", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method normalize;

    public Vec3(Object obj) {
        super(obj);
    }

    public Vec3(Object obj, Object obj2) {
        super(obj);
    }

    public Vec3(Vec3i vec3i) {
        this(construct(Vec3_Vec3i, vec3i.getWrappedObject()));
    }

    public Vec3(double x, double y, double z) {
        this(construct(Vec3_DDD, x, y, z));
    }

    public Vec3 addVector(double x, double y, double z) {
        return new Vec3(invokeMethod(addVector, x, y, z));
    }
    public double getXCoord(){
        return (double) getField(xCoord);
    }
    public double getYCoord(){
        return (double) getField(yCoord);
    }
    public double getZCoord(){
        return (double) getField(zCoord);
    }
    public Vec3 add(Vec3 vec3){
        return new Vec3(invokeMethod(add,vec3.getWrappedObject()));
    }
    public double squareDistanceTo(Vec3 vec3) {
        return (double) invokeMethod(squareDistanceTo, vec3.getWrappedObject());
    }
    public double distanceTo(Vec3 vec3) {
        return (double) invokeMethod(distanceTo, vec3.getWrappedObject());
    }
    public Vec3 subtract(Vec3 vec3) {
        return new Vec3(invokeMethod(subtract_Vec, vec3.getWrappedObject()));
    }
    public double dotProduct(Vec3 vec3) {
        return (double) invokeMethod(dotProduct, vec3.getWrappedObject());
    }
    public double lengthVector() {
        return (double) invokeMethod(lengthVector);
    }
    public Vec3 rotatePitch(float pitch) {
        return new Vec3(invokeMethod(rotatePitch, pitch));
    }
    public Vec3 rotateYaw(float yaw) {
        return new Vec3(invokeMethod(rotateYaw, yaw));
    }
    public Vec3 normalize() {
        return new Vec3(invokeMethod(normalize));
    }
}
