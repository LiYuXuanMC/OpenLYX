package al.logger.client.wrapper.LoggerMC.potion;

import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.potion.PotionEffect",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.potion.PotionEffect",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class PotionEffect extends IWrapper {
    @WrapField(mcpName = "amplifier",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "amplifier",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field amplifier;
    @WrapField(mcpName = "potionID",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field potionID;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {int.class,int.class,int.class})
    public static Constructor PotionEffect_III;
    public PotionEffect(Object obj) {
        super(obj);
    }
    public PotionEffect(int id, int effectDuration, int effectAmplifier)
    {
        this(construction(PotionEffect_III,id,effectDuration,effectAmplifier));
    }
    public int getAmplifier() {
        return (int) getField(amplifier);
    }
    public int getPotionID(){
        return (int) getField(potionID);
    }
}
