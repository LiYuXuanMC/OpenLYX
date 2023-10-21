package al.nya.reflect.wrapper.wraps.wrapper.potion;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.potion.PotionUtils",targetMap = Maps.Srg1_12_2)
public class PotionUtils extends IWrapper {
    @WrapMethod(mcpName = "getEffectsFromStack",targetMap = Maps.Srg1_12_2)
    public static Method getEffectsFromStack;
    public PotionUtils(Object obj) {
        super(obj);
    }
    public static List<PotionEffect> getEffectsFromStack(ItemStack p_185189_0_) {
        List<Object> objects = (List<Object>) ReflectUtil.invoke(getEffectsFromStack,p_185189_0_.getWrapObject());
        List<PotionEffect> potionEffects = new ArrayList<PotionEffect>();
        for (Object object : objects) {
            potionEffects.add(new PotionEffect(object));
        }
        return potionEffects;
    }
}
