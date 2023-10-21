package com.reflectmc.reflect.wrapper.wrappers.minecraft.item;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

@WrapperClass(deobfName = "net.minecraft.item.ItemBucketMilk", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.item.ItemBucketMilk", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ItemBucketMilk extends Item {
    @ClassInstance
    public static Class ItemBucketMilkClass;

    public ItemBucketMilk(Object obj) {
        super(obj);
    }

    public static boolean isItemBucketMilk(Item item) {
        return ItemBucketMilkClass.isInstance(item.getWrappedObject());
    }
}
