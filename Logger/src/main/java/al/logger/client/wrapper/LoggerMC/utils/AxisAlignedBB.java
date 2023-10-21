package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectPosition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.AxisAlignedBB",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.math.AxisAlignedBB",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
//ToDp: Use MethodHandle
public class AxisAlignedBB extends IWrapper {
    @WrapField(mcpName = "minX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "minX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field minX;
    @WrapField(mcpName = "minY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "minY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field minY;
    @WrapField(mcpName = "minZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "minZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field minZ;
    @WrapField(mcpName = "maxX",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "maxX",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field maxX;
    @WrapField(mcpName = "maxY",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "maxY",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field maxY;
    @WrapField(mcpName = "maxZ",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "maxZ",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field maxZ;
    @ClassInstance
    public static Class AxisAlignedBBClass;
    @WrapMethod(mcpName = "offset",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "offset",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(DDD)Lnet/minecraft/util/math/AxisAlignedBB;")
    public static Method offset_3d;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla} , signature = {double.class,double.class,double.class,double.class,double.class,double.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla} , signature = {double.class,double.class,double.class,double.class,double.class,double.class})
    public static Constructor AxisAlignedBB_Constructor_DDDDDD;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {BlockPos.class,BlockPos.class})
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {BlockPos.class,BlockPos.class})
    public static Constructor AxisAlignedBB_Constructor_BlockPos;
    @WrapMethod(mcpName = "expand",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "expand",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method expand;
    @WrapMethod(mcpName = "addCoord",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "expand",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method addCoord;
    @WrapMethod(mcpName = "calculateIntercept",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "calculateIntercept",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method calculateIntercept;
    @WrapMethod(mcpName = "isVecInside",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "contains",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isVecInside;
    @WrapMethod(mcpName = "intersectsWith",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method intersectsWith;
    public AxisAlignedBB(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        super(ReflectUtil.construction(AxisAlignedBB_Constructor_DDDDDD,x1,y1,z1,x2,y2,z2));
    }
    public AxisAlignedBB(BlockPos pos1, BlockPos pos2)
    {
        this(ReflectUtil.construction(AxisAlignedBB_Constructor_BlockPos,pos1.getWrappedObject(),pos2.getWrappedObject()));
    }
    public AxisAlignedBB(Object obj) {
        super(obj);
    }
    public double getMinZ() {
        return (double) ReflectUtil.getField(minZ,getWrappedObject());
    }
    public double getMinY() {
        return (double) ReflectUtil.getField(minY,getWrappedObject());
    }
    public double getMaxZ() {
        return (double) ReflectUtil.getField(maxZ,getWrappedObject());
    }
    public double getMaxY() {
        return (double) ReflectUtil.getField(maxY,getWrappedObject());
    }
    public double getMaxX() {
        return (double) ReflectUtil.getField(maxX,getWrappedObject());
    }
    public double getMinX() {
        return (double) ReflectUtil.getField(minX,getWrappedObject());
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
        return new MovingObjectPosition(invoke(calculateIntercept,vecA.getWrappedObject(),vecB.getWrappedObject()));
    }
    public boolean isVecInside(Vec3 vec3){
        return (boolean) invoke(isVecInside,vec3.getWrappedObject());
    }
}
