package al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;
@WrapperClass(mcpName = "net.minecraft.util.MovingObjectPosition$MovingObjectType",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.math.RayTraceResult$Type",targetMap = Maps.Srg1_12_2)
public class MovingObjectType extends EnumWrapper {
    public MovingObjectType(Object obj) {
        super(obj);
    }
    @WrapEnum(mcpName = "BLOCK",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "BLOCK",targetMap = Maps.Srg1_12_2)
    public static Enum BLOCK;
    @WrapEnum(mcpName = "MISS",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "MISS",targetMap = Maps.Srg1_12_2)
    public static Enum MISS;
    @WrapEnum(mcpName = "ENTITY",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "ENTITY",targetMap = Maps.Srg1_12_2)
    public static Enum ENTITY;

}
