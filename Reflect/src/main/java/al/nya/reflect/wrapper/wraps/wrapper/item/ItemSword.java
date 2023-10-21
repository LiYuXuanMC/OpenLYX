package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.item.ItemSword",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemSword",targetMap = Maps.Srg1_12_2)
public class ItemSword extends Item{
    @WrapClass(mcpName = "net.minecraft.item.ItemSword",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemSword",targetMap = Maps.Srg1_12_2)
    public static Class ItemSwordClass;
    @WrapMethod(mcpName = "getDamageVsEntity",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getAttackDamage",targetMap = Maps.Srg1_12_2)
    public static Method getDamageVsEntity;
    public ItemSword(Object obj) {
        super(obj);
    }
    public static boolean isItemSword(Item item){
        return ItemSwordClass.isInstance(item.getWrapObject());
    }
    public float getDamageVsEntity(){
        return (float) invoke(getDamageVsEntity);
    }
}
