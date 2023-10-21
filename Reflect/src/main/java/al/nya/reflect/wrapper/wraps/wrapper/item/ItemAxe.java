package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemAxe",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemAxe",targetMap = Maps.Srg1_12_2)
public class ItemAxe extends ItemTool{
    @WrapClass(mcpName = "net.minecraft.item.ItemAxe",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemAxe",targetMap = Maps.Srg1_12_2)
    public static Class ItemAxeClass;
    public ItemAxe(Object obj) {
        super(obj);
    }
    public static boolean isItemAxe(Item item){
        return ItemAxeClass.isInstance(item.getWrapObject());
    }
}
