package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.Wrapper;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.potion.PotionEffect;
import al.logger.client.wrapper.LoggerMC.potion.PotionUtils;
import al.logger.client.wrapper.environment.EnvironmentDetector;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@WrapperClass(mcpName = "net.minecraft.item.ItemPotion",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemPotion",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemPotion extends Item{
@ClassInstance    
public static Class ItemPotionClass;
    @WrapMethod(mcpName = "getEffects",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Lnet/minecraft/item/ItemStack;)Ljava/util/List;")
    public static Method getEffects_IS;
    @WrapMethod(mcpName = "isSplash",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isSplash",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isSplash;
    public ItemPotion(Object obj) {
        super(obj);
    }
    public static boolean isItemPotion(Item item){
        return ItemPotionClass.isInstance(item.getWrappedObject());
    }
    public static boolean isSplash(int meta){
        return (boolean) ReflectUtil.invoke(isSplash,meta);
    }
    public List<PotionEffect> getEffects(ItemStack is){
        if (Wrapper.isTargetMap(new Environment[]{Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, EnvironmentDetector.getMinecraft())) return PotionUtils.getEffectsFromStack(is);
        List<Object> objects = (List<Object>) invoke(getEffects_IS,is.getWrappedObject());
        List<PotionEffect> potionEffects = new ArrayList<PotionEffect>();
        for (Object object : objects) {
            potionEffects.add(new PotionEffect(object));
        }
        return potionEffects;
    }
}
