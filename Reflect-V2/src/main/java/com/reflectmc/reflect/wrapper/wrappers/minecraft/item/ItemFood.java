package com.reflectmc.reflect.wrapper.wrappers.minecraft.item;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.item.ItemFood",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.item.ItemFood",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ItemFood extends Item{
    @ClassInstance
    public static Class ItemFoodClass;
    public ItemFood(Object obj) {
        super(obj);
    }
    public static boolean isItemFood(Item item){
        return ItemFoodClass.isInstance(item.getWrappedObject());
    }
}
