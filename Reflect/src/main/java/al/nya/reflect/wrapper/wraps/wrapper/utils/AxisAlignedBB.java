package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.*;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.AxisAlignedBB",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.math.AxisAlignedBB",targetMap = Maps.Srg1_12_2)
public class AxisAlignedBB extends IWrapper {
    @WrapField(mcpName = "minX",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "minX",targetMap = Maps.Srg1_12_2)
    public static Field minX;
    @WrapField(mcpName = "minY",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "minY",targetMap = Maps.Srg1_12_2)
    public static Field minY;
    @WrapField(mcpName = "minZ",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "minZ",targetMap = Maps.Srg1_12_2)
    public static Field minZ;
    @WrapField(mcpName = "maxX",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "maxX",targetMap = Maps.Srg1_12_2)
    public static Field maxX;
    @WrapField(mcpName = "maxY",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "maxY",targetMap = Maps.Srg1_12_2)
    public static Field maxY;
    @WrapField(mcpName = "maxZ",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "maxZ",targetMap = Maps.Srg1_12_2)
    public static Field maxZ;
    @WrapClass(mcpName = "net.minecraft.util.AxisAlignedBB",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.util.math.AxisAlignedBB",targetMap = Maps.Srg1_12_2)
    public static Class AxisAlignedBBClass;
    @WrapMethod(mcpName = "offset",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "offset",targetMap = Maps.Srg1_12_2,signature = "(DDD)Lnet/minecraft/util/math/AxisAlignedBB;")
    public static Method offset_3d;
    @WrapConstructor(targetMap = Maps.Srg1_8_9 , signature = {double.class,double.class,double.class,double.class,double.class,double.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2 , signature = {double.class,double.class,double.class,double.class,double.class,double.class})
    public static Constructor AxisAlignedBB_Constructor_DDDDDD;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {BlockPos.class,BlockPos.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {BlockPos.class,BlockPos.class})
    public static Constructor AxisAlignedBB_Constructor_BlockPos;
    @WrapMethod(mcpName = "expand",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "expand",targetMap = Maps.Srg1_12_2)
    public static Method expand;
    @WrapMethod(mcpName = "addCoord",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "expand",targetMap = Maps.Srg1_12_2)
    public static Method addCoord;
    @WrapMethod(mcpName = "calculateIntercept",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "calculateIntercept",targetMap = Maps.Srg1_12_2)
    public static Method calculateIntercept;
    @WrapMethod(mcpName = "isVecInside",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "contains",targetMap = Maps.Srg1_12_2)
    public static Method isVecInside;
    public AxisAlignedBB(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        super(ReflectUtil.construction(AxisAlignedBB_Constructor_DDDDDD,x1,y1,z1,x2,y2,z2));
    }
    public AxisAlignedBB(BlockPos pos1, BlockPos pos2)
    {
        this(ReflectUtil.construction(AxisAlignedBB_Constructor_BlockPos,pos1.getWrapObject(),pos2.getWrapObject()));
    }
    public AxisAlignedBB(Object obj) {
        super(obj);
    }
    public double getMinZ() {
        return (double) ReflectUtil.getField(minZ,getWrapObject());
    }
    public double getMinY() {
        return (double) ReflectUtil.getField(minY,getWrapObject());
    }
    public double getMaxZ() {
        return (double) ReflectUtil.getField(maxZ,getWrapObject());
    }
    public double getMaxY() {
        return (double) ReflectUtil.getField(maxY,getWrapObject());
    }
    public double getMaxX() {
        return (double) ReflectUtil.getField(maxX,getWrapObject());
    }
    public double getMinX() {
        return (double) ReflectUtil.getField(minX,getWrapObject());
    }
    public AxisAlignedBB offset(double x,double y,double z){
        return new AxisAlignedBB(invoke(offset_3d,x,y,z));
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
        return new AxisAlignedBB(invoke(expand,x,y,z));
    }
    public AxisAlignedBB addCoord(double x,double y,double z){
        return new AxisAlignedBB(invoke(addCoord,x,y,z));
    }
    public MovingObjectPosition calculateIntercept(Vec3 vecA, Vec3 vecB) {
        return new MovingObjectPosition(invoke(calculateIntercept,vecA.getWrapObject(),vecB.getWrapObject()));
    }
    public boolean isVecInside(Vec3 vec3){
        return (boolean) invoke(isVecInside,vec3.getWrapObject());
    }
}
