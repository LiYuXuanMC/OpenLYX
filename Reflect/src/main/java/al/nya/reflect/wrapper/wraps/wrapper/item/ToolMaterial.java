package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.item.Item$ToolMaterial",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.Item$ToolMaterial",targetMap = Maps.Srg1_12_2)
public class ToolMaterial extends EnumWrapper {
    @WrapEnum(mcpName = "WOOD",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "WOOD",targetMap = Maps.Srg1_12_2)
    public static Enum WOOD;
    @WrapEnum(mcpName = "STONE",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "STONE",targetMap = Maps.Srg1_12_2)
    public static Enum STONE;
    @WrapEnum(mcpName = "IRON",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "IRON",targetMap = Maps.Srg1_12_2)
    public static Enum IRON;
    @WrapEnum(mcpName = "EMERALD",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "DIAMOND",targetMap = Maps.Srg1_12_2)
    public static Enum EMERALD;
    @WrapEnum(mcpName = "GOLD",targetMap = Maps.Srg1_8_9)
    @WrapEnum(mcpName = "GOLD",targetMap = Maps.Srg1_12_2)
    public static Enum GOLD;
    @WrapField(mcpName = "efficiencyOnProperMaterial",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "efficiency",targetMap = Maps.Srg1_12_2)
    public static Field efficiencyOnProperMaterial;
    public ToolMaterial(Object obj) {
        super(obj);
    }
    public static float getEfficiency(Enum material){
        return (float) ReflectUtil.getField(efficiencyOnProperMaterial,material);
    }
}
