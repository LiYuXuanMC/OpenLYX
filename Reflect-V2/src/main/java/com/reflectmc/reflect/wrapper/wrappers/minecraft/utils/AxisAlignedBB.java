package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils;

import com.reflectmc.reflect.wrapper.annotation.*;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.mouse.MovingObjectPosition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.util.AxisAlignedBB",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.math.AxisAlignedBB",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class AxisAlignedBB extends WrapperBase {
    @WrapField(deobfName = "minX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "minX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field minX;
    @WrapField(deobfName = "minY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "minY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field minY;
    @WrapField(deobfName = "minZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "minZ",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field minZ;
    @WrapField(deobfName = "maxX",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "maxX",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field maxX;
    @WrapField(deobfName = "maxY",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "maxY",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field maxY;
    @WrapField(deobfName = "maxZ",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "maxZ",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field maxZ;
    @ClassInstance
    public static Class AxisAlignedBBClass;
    @WrapMethod(deobfName = "offset",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "offset",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = "(DDD)Lnet/minecraft/util/math/AxisAlignedBB;")
    public static Method offset_3d;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189} , signature = {double.class,double.class,double.class,double.class,double.class,double.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122} , signature = {double.class,double.class,double.class,double.class,double.class,double.class})
    public static Constructor AxisAlignedBB_Constructor_DDDDDD;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {BlockPos.class,BlockPos.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122},signature = {BlockPos.class,BlockPos.class})
    public static Constructor AxisAlignedBB_Constructor_BlockPos;
    @WrapMethod(deobfName = "expand",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "expand",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method expand;
    @WrapMethod(deobfName = "addCoord",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "expand",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method addCoord;
    @WrapMethod(deobfName = "calculateIntercept",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "calculateIntercept",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method calculateIntercept;
    @WrapMethod(deobfName = "isVecInside",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "contains",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isVecInside;
    public AxisAlignedBB(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        super(construct(AxisAlignedBB_Constructor_DDDDDD,x1,y1,z1,x2,y2,z2));
    }
    public AxisAlignedBB(BlockPos pos1, BlockPos pos2)
    {
        this(construct(AxisAlignedBB_Constructor_BlockPos,pos1.getWrappedObject(),pos2.getWrappedObject()));
    }
    public AxisAlignedBB(Object obj) {
        super(obj);
    }
    public double getMinZ() {
        return (double) getField(minZ);
    }
    public double getMinY() {
        return (double) getField(minY);
    }
    public double getMaxZ() {
        return (double) getField(maxZ);
    }
    public double getMaxY() {
        return (double) getField(maxY);
    }
    public double getMaxX() {
        return (double) getField(maxX);
    }
    public double getMinX() {
        return (double) getField(minX);
    }
    public AxisAlignedBB offset(double x,double y,double z){
        return new AxisAlignedBB(invokeMethod(offset_3d,x,y,z));
    }
    public static AxisAlignedBB fromBounds(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        double d0 = Math.min(x1, x2);
        double d1 = Math.min(y1, y2);
        double d2 = Math.min(z1, z2);
        double d3 = Math.max(x1, x2);
        double d4 = Math.max(y1, y2);
        double d5 = Math.max(z1, z2);
        return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
    }
    public AxisAlignedBB expand(double x, double y, double z)
    {
        return new AxisAlignedBB(invokeMethod(expand,x,y,z));
    }
    public AxisAlignedBB addCoord(double x,double y,double z){
        return new AxisAlignedBB(invokeMethod(addCoord,x,y,z));
    }
    public MovingObjectPosition calculateIntercept(Vec3 vecA, Vec3 vecB) {
        return new MovingObjectPosition(invokeMethod(calculateIntercept,vecA.getWrappedObject(),vecB.getWrappedObject()));
    }
    public boolean isVecInside(Vec3 vec3){
        return (boolean) invokeMethod(isVecInside,vec3.getWrappedObject());
    }
}
