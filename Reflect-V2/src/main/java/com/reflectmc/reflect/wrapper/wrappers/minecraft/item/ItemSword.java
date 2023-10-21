package com.reflectmc.reflect.wrapper.wrappers.minecraft.item;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.item.ItemSword",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.item.ItemSword",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ItemSword extends Item{
    @ClassInstance
    public static Class ItemSwordClass;
    @WrapMethod(deobfName = "getDamageVsEntity",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "getAttackDamage",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getDamageVsEntity;
    public ItemSword(Object obj) {
        super(obj);
    }
    public static boolean isItemSword(Item item){
        return ItemSwordClass.isInstance(item.getWrappedObject());
    }
    public float getDamageVsEntity(){
        return (float) invokeMethod(getDamageVsEntity);
    }
}
