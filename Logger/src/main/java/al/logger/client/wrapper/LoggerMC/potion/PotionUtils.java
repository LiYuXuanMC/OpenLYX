package al.logger.client.wrapper.LoggerMC.potion;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.potion.PotionUtils",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class PotionUtils extends IWrapper {
    @WrapMethod(mcpName = "getEffectsFromStack",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getEffectsFromStack;
    public PotionUtils(Object obj) {
        super(obj);
    }
    public static List<PotionEffect> getEffectsFromStack(ItemStack p_185189_0_) {
        List<Object> objects = (List<Object>) invokeStatic(getEffectsFromStack,p_185189_0_.getWrappedObject());
        List<PotionEffect> potionEffects = new ArrayList<PotionEffect>();
        for (Object object : objects) {
            potionEffects.add(new PotionEffect(object));
        }
        return potionEffects;
    }
}
