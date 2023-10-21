package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapEnum;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.EnumWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.inventory.EntityEquipmentSlot",targetMap = Maps.Srg1_12_2)
public class EntityEquipmentSlot extends EnumWrapper {
    @WrapEnum(mcpName = "MAINHAND",targetMap = Maps.Srg1_12_2)
    public static Enum MAINHAND;
    @WrapEnum(mcpName = "OFFHAND",targetMap = Maps.Srg1_12_2)
    public static Enum OFFHAND;
    @WrapEnum(mcpName = "FEET",targetMap = Maps.Srg1_12_2)
    public static Enum FEET;
    @WrapEnum(mcpName = "LEGS",targetMap = Maps.Srg1_12_2)
    public static Enum LEGS;
    @WrapEnum(mcpName = "CHEST",targetMap = Maps.Srg1_12_2)
    public static Enum CHEST;
    @WrapEnum(mcpName = "HEAD",targetMap = Maps.Srg1_12_2)
    public static Enum HEAD;
    @WrapField(mcpName = "index",targetMap = Maps.Srg1_12_2)
    public static Field index;
    public EntityEquipmentSlot(Object obj) {
        super(obj);
    }
    public static int getIndex(Enum e){
        return (int) ReflectUtil.getField(index,e);
    }
}
