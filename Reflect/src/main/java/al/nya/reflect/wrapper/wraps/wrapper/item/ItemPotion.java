package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.Wrapper;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.potion.PotionEffect;
import al.nya.reflect.wrapper.wraps.wrapper.potion.PotionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.item.ItemPotion",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemPotion",targetMap = Maps.Srg1_12_2)
public class ItemPotion extends Item{
    @WrapClass(mcpName = "net.minecraft.item.ItemPotion",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemPotion",targetMap = Maps.Srg1_12_2)
    public static Class ItemPotionClass;
    @WrapMethod(mcpName = "getEffects",targetMap = Maps.Srg1_8_9,signature = "(Lnet/minecraft/item/ItemStack;)Ljava/util/List;")
    public static Method getEffects_IS;
    @WrapMethod(mcpName = "isSplash",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isSplash",targetMap = Maps.Srg1_12_2)
    public static Method isSplash;
    public ItemPotion(Object obj) {
        super(obj);
    }
    public static boolean isItemPotion(Item item){
        return ItemPotionClass.isInstance(item.getWrapObject());
    }
    public static boolean isSplash(int meta){
        return (boolean) ReflectUtil.invoke(isSplash,null);
    }
    public List<PotionEffect> getEffects(ItemStack is){
        if (Wrapper.env.equals(Maps.Srg1_12_2)) return PotionUtils.getEffectsFromStack(is);
        List<Object> objects = (List<Object>) invoke(getEffects_IS,is.getWrapObject());
        List<PotionEffect> potionEffects = new ArrayList<PotionEffect>();
        for (Object object : objects) {
            potionEffects.add(new PotionEffect(object));
        }
        return potionEffects;
    }
}
