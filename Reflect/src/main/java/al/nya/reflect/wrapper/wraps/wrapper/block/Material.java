package al.nya.reflect.wrapper.wraps.wrapper.block;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.block.material.Material",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.block.material.Material",targetMap = Maps.Srg1_12_2)
public class Material extends IWrapper {
    @WrapField(mcpName = "water",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "WATER",targetMap = Maps.Srg1_12_2)
    public static Field water;
    @WrapMethod(mcpName = "isReplaceable",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isReplaceable",targetMap = Maps.Srg1_12_2)
    public static Method isReplaceable;
    @WrapMethod(mcpName = "isSolid",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isSolid",targetMap = Maps.Srg1_12_2)
    public static Method isSolid;
    @WrapMethod(mcpName = "isLiquid",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isLiquid",targetMap = Maps.Srg1_12_2)
    public static Method isLiquid;
    public Material(Object obj) {
        super(obj);
    }
    public static Material getWater(){
        return new Material(ReflectUtil.getField(water,null));
    }
    public boolean isReplaceable(){
        return (boolean) invoke(isReplaceable);
    }
    public boolean isSolid(){
        return (boolean) invoke(isSolid);
    }
    public boolean isLiquid(){
        return (boolean) invoke(isLiquid);
    }
}
