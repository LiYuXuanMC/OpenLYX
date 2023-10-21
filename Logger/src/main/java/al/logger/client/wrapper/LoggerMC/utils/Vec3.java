package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.Vec3",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.math.Vec3d",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Vec3 extends IWrapper {
    @ClassInstance
    public static Class Vec3Class;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {double.class,double.class,double.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {double.class,double.class,double.class})
    public static Constructor Vec3_DDD;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = {Vec3i.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = {Vec3i.class})
    public static Constructor Vec3_Vec3i;
    @WrapMethod(mcpName = "addVector", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "addVector", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method addVector;
    @WrapField(mcpName = "xCoord", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "x", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field xCoord;
    @WrapMethod(mcpName = "dotProduct", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "dotProduct", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method dotProduct;
    @WrapField(mcpName = "yCoord", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "y", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field yCoord;
    @WrapField(mcpName = "zCoord", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "z", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field zCoord;
    @WrapMethod(mcpName = "add", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "add", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method add;
    @WrapMethod(mcpName = "squareDistanceTo", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "squareDistanceTo",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Lnet/minecraft/util/math/Vec3d;)D")
    public static Method squareDistanceTo;
    @WrapMethod(mcpName = "distanceTo",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "distanceTo",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method distanceTo;
    @WrapMethod(mcpName = "subtract", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla}, signature = "(Lnet/minecraft/util/Vec3;)Lnet/minecraft/util/Vec3;")
    @WrapMethod(mcpName = "subtract", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;")
    public static Method subtract_Vec;
    @WrapMethod(mcpName = "lengthVector", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "lengthVector", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method lengthVector;
    @WrapMethod(mcpName = "rotatePitch", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "rotatePitch", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method rotatePitch;
    @WrapMethod(mcpName = "rotateYaw", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "rotateYaw", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method rotateYaw;
    @WrapMethod(mcpName = "normalize", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "normalize", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method normalize;

    public Vec3(Object obj) {
        super(obj);
    }

    public Vec3(Object obj, Object obj2) {
        super(obj);
    }

    public Vec3(Vec3i vec3i) {
        this(construction(Vec3_Vec3i, vec3i.getWrappedObject()));
    }

    public Vec3(double x, double y, double z) {
        this(construction(Vec3_DDD, x, y, z));
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
        return new Vec3(invoke(add,vec3.getWrappedObject()));
    }

    public double squareDistanceTo(Vec3 vec3) {
        return (double) invoke(squareDistanceTo, vec3.getWrappedObject());
    }

    public double distanceTo(Vec3 vec3) {
        return (double) invoke(distanceTo, vec3.getWrappedObject());
    }

    public Vec3 subtract(Vec3 vec3) {
        return new Vec3(invoke(subtract_Vec, vec3.getWrappedObject()));
    }

    public double dotProduct(Vec3 vec3) {
        return (double) invoke(dotProduct, vec3.getWrappedObject());
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
