package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemSnowball",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemSnowball",targetMap = Maps.Srg1_12_2)
public class ItemSnowball extends Item{
    @WrapClass(mcpName = "net.minecraft.item.ItemSnowball",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemSnowball",targetMap = Maps.Srg1_12_2)
    public static Class ItemSnowballClass;
    public ItemSnowball(Object obj) {
        super(obj);
    }
    public static boolean isItemSnowball(Item item){
        return ItemSnowballClass.isInstance(item.getWrapObject());
    }
}
