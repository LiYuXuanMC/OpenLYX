package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemSpade",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemSpade",targetMap = Maps.Srg1_12_2)
public class ItemSpade extends ItemTool{
    @WrapClass(mcpName = "net.minecraft.item.ItemSpade",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemSpade",targetMap = Maps.Srg1_12_2)
    public static Class ItemSpadeClass;
    public ItemSpade(Object obj) {
        super(obj);
    }
    public static boolean isItemSpade(Item item){
        return ItemSpadeClass.isInstance(item.getWrapObject());
    }
}
