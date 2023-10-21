package al.nya.reflect.wrapper.wraps.wrapper.potion;

import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.potion.PotionEffect",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.potion.PotionEffect",targetMap = Maps.Srg1_12_2)
public class PotionEffect extends IWrapper {
    @WrapField(mcpName = "amplifier",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "amplifier",targetMap = Maps.Srg1_12_2)
    public static Field amplifier;
    @WrapField(mcpName = "potionID",targetMap = Maps.Srg1_8_9)
    public static Field potionID;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {int.class,int.class,int.class})
    public static Constructor PotionEffect_III;
    public PotionEffect(Object obj) {
        super(obj);
    }
    public PotionEffect(int id, int effectDuration, int effectAmplifier)
    {
        this(ReflectUtil.construction(PotionEffect_III,id,effectDuration,effectAmplifier));
    }
    public int getAmplifier() {
        return (int) ReflectUtil.getField(amplifier,getWrapObject());
    }
    public int getPotionID(){
        return (int) getField(potionID);
    }
}
