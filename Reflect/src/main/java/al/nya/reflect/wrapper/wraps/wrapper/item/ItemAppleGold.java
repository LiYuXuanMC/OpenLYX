package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemAppleGold",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemAppleGold",targetMap = Maps.Srg1_12_2)
public class ItemAppleGold extends ItemFood{
    @WrapClass(mcpName = "net.minecraft.item.ItemAppleGold",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemAppleGold",targetMap = Maps.Srg1_12_2)
    public static Class ItemAppleGoldClass;
    public ItemAppleGold(Object obj) {
        super(obj);
    }
    public static boolean isItemAppleGold(Item item){
        return ItemAppleGoldClass.isInstance(item.getWrapObject());
    }
}
