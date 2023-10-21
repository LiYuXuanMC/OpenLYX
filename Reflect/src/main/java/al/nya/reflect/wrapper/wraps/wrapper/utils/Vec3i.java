package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.Vec3i",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.math.Vec3i",targetMap = Maps.Srg1_12_2)
public class Vec3i extends IWrapper {
    @WrapField(mcpName = "x",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "x",targetMap = Maps.Srg1_12_2)
    public static Field x;
    @WrapField(mcpName = "y",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "y",targetMap = Maps.Srg1_12_2)
    public static Field y;
    @WrapField(mcpName = "z",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "z",targetMap = Maps.Srg1_12_2)
    public static Field z;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {double.class,double.class,double.class})
    @WrapConstructor(targetMap = Maps.Srg1_12_2,signature = {double.class,double.class,double.class})
    public static Constructor Vec3i_DDD;
    @WrapMethod(mcpName = "distanceSq",targetMap = Maps.Srg1_12_2,signature = "(DDD)D")
    @WrapMethod(mcpName = "distanceSq",targetMap = Maps.Srg1_8_9,signature = "(DDD)D")
    public static Method distanceSq;

    public Vec3i(Object obj) {
        super(obj);
    }
    public Vec3i(double xIn, double yIn, double zIn)
    {
        this(ReflectUtil.construction(Vec3i_DDD,xIn,yIn,zIn));
    }

    public int getX() {
        return (int) ReflectUtil.getField(x,getWrapObject());
    }

    public int getY() {
        return (int) ReflectUtil.getField(y,getWrapObject());
    }

    public int getZ() {
        return (int) ReflectUtil.getField(z,getWrapObject());
    }

    public double distanceTo(double posX, double posY, double posZ) {
        return (double) invoke(distanceSq,posX,posY,posZ);
    }
}
