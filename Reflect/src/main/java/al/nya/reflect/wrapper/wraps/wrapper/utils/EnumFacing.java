package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.*;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.util.EnumFacing",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.EnumFacing",targetMap = Maps.Srg1_12_2)
public class EnumFacing extends EnumWrapper {
    @WrapClass(mcpName = "net.minecraft.util.EnumFacing",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.util.EnumFacing",targetMap = Maps.Srg1_12_2)
    public static Class EnumFacingClass;
    @WrapField(mcpName = "directionVec",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "directionVec",targetMap = Maps.Srg1_12_2)
    public static Field directionVec;
    @WrapMethod(mcpName = "getOpposite",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getOpposite",targetMap = Maps.Srg1_12_2)
    public static Method getOpposite;
    @WrapMethod(mcpName = "getFrontOffsetX",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getFrontOffsetX",targetMap = Maps.Srg1_12_2)
    public static Method getFrontOffsetX;
    @WrapMethod(mcpName = "getFrontOffsetZ",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getFrontOffsetZ",targetMap = Maps.Srg1_12_2)
    public static Method getFrontOffsetZ;
    public EnumFacing(Object obj) {
        super(obj);
    }

    public static Enum[] values() {
        return new Enum[]{DOWN,UP,NORTH,SOUTH,WEST,EAST};
    }

    @WrapEnum(mcpName = "DOWN",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "DOWN",targetMap = Maps.Srg1_12_2)
    public static Enum DOWN;
    @WrapEnum(mcpName = "UP",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "UP",targetMap = Maps.Srg1_12_2)
    public static Enum UP;
    @WrapEnum(mcpName = "NORTH",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "NORTH",targetMap = Maps.Srg1_12_2)
    public static Enum NORTH;
    @WrapEnum(mcpName = "SOUTH",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "SOUTH",targetMap = Maps.Srg1_12_2)
    public static Enum SOUTH;
    @WrapEnum(mcpName = "WEST",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "WEST",targetMap = Maps.Srg1_12_2)
    public static Enum WEST;
    @WrapEnum(mcpName = "EAST",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "EAST",targetMap = Maps.Srg1_12_2)
    public static Enum EAST;

    public static Vec3i getDirectionVec(Enum facing){
        return new Vec3i(ReflectUtil.getField(directionVec,facing));
    }
    public static Enum getOpposite(Enum facing){
        return (Enum) ReflectUtil.invoke(getOpposite,facing);
    }
    public static int getFrontOffsetX(Enum e){
        return (int) ReflectUtil.invoke(getFrontOffsetX,e);
    }
    public static int getFrontOffsetZ(Enum e){
        return (int) ReflectUtil.invoke(getFrontOffsetZ,e);
    }
}
