package al.nya.reflect.wrapper.wraps.wrapper.enchantment;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.enchantment.EnchantmentHelper",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.enchantment.EnchantmentHelper",targetMap = Maps.Srg1_12_2)
public class EnchantmentHelper extends IWrapper {
    @WrapMethod(mcpName = "getEnchantmentLevel",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getEnchantmentLevel",targetMap = Maps.Srg1_12_2)
    public static Method getEnchantmentLevel;
    public EnchantmentHelper(Object obj) {
        super(obj);
    }
    public static int getEnchantmentLevel(int id, ItemStack itemStack){
        if (Wrapper.env.equals(Maps.Srg1_12_2)){
            return (int) ReflectUtil.invoke(getEnchantmentLevel, null, Enchantment.getEnchantmentByID(id).getWrapObject(), itemStack.getWrapObject());
        }
        return (int) ReflectUtil.invoke(getEnchantmentLevel,null,id,itemStack.getWrapObject());
    }
}
