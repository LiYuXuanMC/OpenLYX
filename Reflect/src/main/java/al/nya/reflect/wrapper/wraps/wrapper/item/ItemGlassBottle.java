package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemGlassBottle",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemGlassBottle",targetMap = Maps.Srg1_12_2)
public class ItemGlassBottle extends Item{
    @WrapClass(mcpName = "net.minecraft.item.ItemGlassBottle",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemGlassBottle",targetMap = Maps.Srg1_12_2)
    public static Class ItemGlassBottleClass;
    public ItemGlassBottle(Object obj) {
        super(obj);
    }
    public static boolean isItemGlassBottle(Item item){
        return ItemGlassBottleClass.isInstance(item.getWrapObject());
    }
}
