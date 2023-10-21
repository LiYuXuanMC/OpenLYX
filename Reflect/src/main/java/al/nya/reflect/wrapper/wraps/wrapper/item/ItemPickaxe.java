package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.item.ItemPickaxe",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemPickaxe",targetMap = Maps.Srg1_12_2)
public class ItemPickaxe extends ItemTool{
    @WrapClass(mcpName = "net.minecraft.item.ItemPickaxe",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemPickaxe",targetMap = Maps.Srg1_12_2)
    public static Class ItemPickaxeClass;
    public ItemPickaxe(Object obj) {
        super(obj);
    }
    public static boolean isItemPickaxe(Item item){
        return ItemPickaxeClass.isInstance(item.getWrapObject());
    }
}
