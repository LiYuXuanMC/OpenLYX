package com.reflectmc.reflect.wrapper.wrappers.minecraft.item;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.potion.PotionEffect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(deobfName = "net.minecraft.item.ItemPotion",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.item.ItemPotion",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class ItemPotion extends Item{
    @ClassInstance
    public static Class ItemPotionClass;
    @WrapMethod(deobfName = "getEffects",targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = "(Lnet/minecraft/item/ItemStack;)Ljava/util/List;")
    public static Method getEffects_IS;
    @WrapMethod(deobfName = "isSplash",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isSplash",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isSplash;
    public ItemPotion(Object obj) {
        super(obj);
    }
    public static boolean isItemPotion(Item item){
        return ItemPotionClass.isInstance(item.getWrappedObject());
    }
    public static boolean isSplash(int meta){
        return (boolean) invokeStaticMethod(isSplash);
    }
    public List<PotionEffect> getEffects(ItemStack is){
        List<Object> objects = (List<Object>) invokeMethod(getEffects_IS,is.getWrappedObject());
        List<PotionEffect> potionEffects = new ArrayList<PotionEffect>();
        for (Object object : objects) {
            potionEffects.add(new PotionEffect(object));
        }
        return potionEffects;
    }
}
