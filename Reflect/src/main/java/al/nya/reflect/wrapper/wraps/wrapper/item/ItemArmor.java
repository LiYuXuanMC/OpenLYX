package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.item.ItemArmor",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemArmor",targetMap = Maps.Srg1_12_2)
public class ItemArmor extends Item{
    @WrapClass(mcpName = "net.minecraft.item.ItemArmor",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemArmor",targetMap = Maps.Srg1_12_2)
    public static Class ItemArmorClass;
    @WrapField(mcpName = "damageReduceAmount",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "damageReduceAmount",targetMap = Maps.Srg1_12_2)
    public static Field damageReduceAmount;
    @WrapField(mcpName = "armorType",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "armorType",targetMap = Maps.Srg1_12_2)
    public static Field armorType;
    public ItemArmor(Object obj) {
        super(obj);
    }
    public static boolean isItemArmor(Item item){
        return ItemArmorClass.isInstance(item.getWrapObject());
    }
    public int getDamageReduceAmount(){
        return (int) getField(damageReduceAmount);
    }
    public int getArmorType(){
        if (Wrapper.env.equals(Maps.Srg1_12_2)){
            return EntityEquipmentSlot.getIndex(getArmorTypeV1_12_2());
        }
        return (int) getField(armorType);
    }
    public Enum getArmorTypeV1_12_2(){
        return (Enum) getField(armorType);
    }
}
