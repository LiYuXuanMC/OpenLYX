package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemBow",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemBow",targetMap = Maps.Srg1_12_2)
public class ItemBow extends Item{
    @WrapClass(mcpName = "net.minecraft.item.ItemBow",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemBow",targetMap = Maps.Srg1_12_2)
    public static Class ItemBowClass;
    public ItemBow(Object obj) {
        super(obj);
    }
    public static boolean isItemBow(Item item){
        return ItemBowClass.isInstance(item.getWrapObject());
    }
}
