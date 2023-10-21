package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemEnderPearl",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemEnderPearl",targetMap = Maps.Srg1_12_2)
public class ItemEnderPearl extends Item{
    @WrapClass(mcpName = "net.minecraft.item.ItemEnderPearl",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemEnderPearl",targetMap = Maps.Srg1_12_2)
    public static Class ItemEnderPearlClass;
    public ItemEnderPearl(Object obj) {
        super(obj);
    }
    public static boolean isItemEnderPearl(Item item){
        return ItemEnderPearlClass.isInstance(item.getWrapObject());
    }
}
