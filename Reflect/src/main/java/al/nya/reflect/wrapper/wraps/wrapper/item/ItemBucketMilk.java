package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.item.ItemBucketMilk", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemBucketMilk", targetMap = Maps.Srg1_12_2)
public class ItemBucketMilk extends Item {

    @WrapClass(mcpName = "net.minecraft.item.ItemBucketMilk", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemBucketMilk", targetMap = Maps.Srg1_12_2)
    public static Class ItemBucketMilkClass;

    public ItemBucketMilk(Object obj) {
        super(obj);
    }

    public static boolean isItemBucketMilk(Item item) {
        return ItemBucketMilkClass.isInstance(item.getWrapObject());
    }
}
