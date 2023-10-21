package com.reflectmc.reflect.wrapper.wrappers.minecraft.potion;

import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.item.ItemStack;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(deobfName = "net.minecraft.potion.PotionUtils",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class PotionUtils extends WrapperBase {
    @WrapMethod(deobfName = "getEffectsFromStack",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method getEffectsFromStack;
    public PotionUtils(Object obj) {
        super(obj);
    }
    public static List<PotionEffect> getEffectsFromStack(ItemStack p_185189_0_) {
        List<Object> objects = (List<Object>) invokeStaticMethod(getEffectsFromStack,p_185189_0_.getWrappedObject());
        List<PotionEffect> potionEffects = new ArrayList<PotionEffect>();
        for (Object object : objects) {
            potionEffects.add(new PotionEffect(object));
        }
        return potionEffects;
    }
}
