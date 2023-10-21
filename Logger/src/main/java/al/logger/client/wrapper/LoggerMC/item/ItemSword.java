package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.item.ItemSword",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemSword",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemSword extends Item{
    @ClassInstance
    public static Class ItemSwordClass;
    @WrapMethod(mcpName = "getDamageVsEntity",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getAttackDamage",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getDamageVsEntity;
    public ItemSword(Object obj) {
        super(obj);
    }
    public static boolean isItemSword(Item item){
        return ItemSwordClass.isInstance(item.getWrappedObject());
    }
    public float getDamageVsEntity(){
        return (float) invoke(getDamageVsEntity);
    }
}
