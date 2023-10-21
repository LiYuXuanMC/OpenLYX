package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.*;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.Vec3",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.math.Vec3d",targetMap = Maps.Srg1_12_2)
public class Vec3 extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.util.Vec3",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.util.math.Vec3d",targetMap = Maps.Srg1_12_2)
    public static Class Vec3Class;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {double.class,double.class,double.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {double.class,double.class,double.class})
    public static Constructor Vec3_DDD;
    @WrapConstructor(targetMap = Maps.Srg1_8_9, signature = {Vec3i.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2, signature = {Vec3i.class})
    public static Constructor Vec3_Vec3i;
    @WrapMethod(mcpName = "addVector", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "addVector", targetMap = Maps.Srg1_12_2)
    public static Method addVector;
    @WrapField(mcpName = "xCoord", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "x", targetMap = Maps.Srg1_12_2)
    public static Field xCoord;
    @WrapMethod(mcpName = "dotProduct", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "dotProduct", targetMap = Maps.Srg1_12_2)
    public static Method dotProduct;
    @WrapField(mcpName = "yCoord", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "y", targetMap = Maps.Srg1_12_2)
    public static Field yCoord;
    @WrapField(mcpName = "zCoord", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "z", targetMap = Maps.Srg1_12_2)
    public static Field zCoord;
    @WrapMethod(mcpName = "add", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "add", targetMap = Maps.Srg1_12_2)
    public static Method add;
    @WrapMethod(mcpName = "squareDistanceTo", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "squareDistanceTo",targetMap = Maps.Srg1_12_2,signature = "(Lnet/minecraft/util/math/Vec3d;)D")
    public static Method squareDistanceTo;
    @WrapMethod(mcpName = "distanceTo",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "distanceTo",targetMap = Maps.Srg1_12_2)
    public static Method distanceTo;
    @WrapMethod(mcpName = "subtract", targetMap = Maps.Srg1_8_9, signature = "(Lnet/minecraft/util/Vec3;)Lnet/minecraft/util/Vec3;")
    @WrapMethod(mcpName = "subtract", targetMap = Maps.Srg1_12_2, signature = "(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;")
    public static Method subtract_Vec;
    @WrapMethod(mcpName = "lengthVector", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "lengthVector", targetMap = Maps.Srg1_12_2)
    public static Method lengthVector;
    @WrapMethod(mcpName = "rotatePitch", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "rotatePitch", targetMap = Maps.Srg1_12_2)
    public static Method rotatePitch;
    @WrapMethod(mcpName = "rotateYaw", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "rotateYaw", targetMap = Maps.Srg1_12_2)
    public static Method rotateYaw;
    @WrapMethod(mcpName = "normalize", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "normalize", targetMap = Maps.Srg1_12_2)
    public static Method normalize;

    public Vec3(Object obj) {
        super(obj);
    }

    public Vec3(Object obj, Object obj2) {
        super(obj);
    }

    public Vec3(Vec3i vec3i) {
        this(ReflectUtil.construction(Vec3_Vec3i, vec3i.getWrapObject()));
    }

    public Vec3(double x, double y, double z) {
        this(ReflectUtil.construction(Vec3_DDD, x, y, z));
    }

    public Vec3 addVector(double x, double y, double z) {
        return new Vec3(invoke(addVector, x, y, z));
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
        return new Vec3(invoke(add,vec3.getWrapObject()));
    }

    public double squareDistanceTo(Vec3 vec3) {
        return (double) invoke(squareDistanceTo, vec3.getWrapObject());
    }

    public double distanceTo(Vec3 vec3) {
        return (double) invoke(distanceTo, vec3.getWrapObject());
    }

    public Vec3 subtract(Vec3 vec3) {
        return new Vec3(invoke(subtract_Vec, vec3.getWrapObject()));
    }

    public double dotProduct(Vec3 vec3) {
        return (double) invoke(dotProduct, vec3.getWrapObject());
    }

    public double lengthVector() {
        return (double) invoke(lengthVector);
    }

    public Vec3 rotatePitch(float pitch) {
        return new Vec3(invoke(rotatePitch, pitch));
    }

    public Vec3 rotateYaw(float yaw) {
        return new Vec3(invoke(rotateYaw, yaw));
    }

    public Vec3 normalize() {
        return new Vec3(invoke(normalize));
    }
}
