package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemFishingRod",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemFishingRod",targetMap = Maps.Srg1_12_2)
public class ItemFishingRod extends Item{
    @WrapClass(mcpName = "net.minecraft.item.ItemFishingRod",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemFishingRod",targetMap = Maps.Srg1_12_2)
    public static Class ItemFishingRodClass;
    public ItemFishingRod(Object obj) {
        super(obj);
    }
    public static boolean isItemFishingRod(Item item){
        return ItemFishingRodClass.isInstance(item.getWrapObject());
    }
}
