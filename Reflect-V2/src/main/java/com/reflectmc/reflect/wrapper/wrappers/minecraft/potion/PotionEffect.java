package com.reflectmc.reflect.wrapper.wrappers.minecraft.potion;

import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(deobfName = "net.minecraft.potion.PotionEffect",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.potion.PotionEffect",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class PotionEffect extends WrapperBase {
    @WrapField(deobfName = "amplifier",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "amplifier",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field amplifier;
    @WrapField(deobfName = "potionID",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    public static Field potionID;
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189},signature = {int.class,int.class,int.class})
    public static Constructor PotionEffect_III;
    public PotionEffect(Object obj) {
        super(obj);
    }
    public PotionEffect(int id, int effectDuration, int effectAmplifier)
    {
        this(construct(PotionEffect_III,id,effectDuration,effectAmplifier));
    }
    public int getAmplifier() {
        return (int) getField(amplifier);
    }
    public int getPotionID(){
        return (int) getField(potionID);
    }
}
