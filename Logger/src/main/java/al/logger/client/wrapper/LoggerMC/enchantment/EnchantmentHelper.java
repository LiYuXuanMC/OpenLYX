package al.logger.client.wrapper.LoggerMC.enchantment;

import al.logger.client.wrapper.annotations.VersionOnly;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.enchantment.EnchantmentHelper",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.enchantment.EnchantmentHelper",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EnchantmentHelper extends IWrapper {
    @WrapMethod(mcpName = "getEnchantmentLevel",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getEnchantmentLevel",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getEnchantmentLevel;
    public EnchantmentHelper(Object obj) {
        super(obj);
    }
    //1.8.9
    public static int getEnchantmentLevel(int id, ItemStack itemStack){
        return (int) invokeStatic(getEnchantmentLevel,id,itemStack.getWrappedObject());
    }
    //1.12.2
    public static int getEnchantmentLevel(Enchantment enchantment,ItemStack itemStack){
        return (int) ReflectUtil.invoke(getEnchantmentLevel, null, enchantment.getWrappedObject(), itemStack.getWrappedObject());
    }
}
