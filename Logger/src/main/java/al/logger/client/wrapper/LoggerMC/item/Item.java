package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.item.Item", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.Item", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Item extends IWrapper {
    @WrapMethod(mcpName = "getUnlocalizedName", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getUnlocalizedName", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla}, signature = "()Ljava/lang/String;")
    public static Method getUnlocalizedName;
    @WrapField(mcpName = "maxDamage", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "maxDamage", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field maxDamage;
    @WrapMethod(mcpName = "getIdFromItem", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getIdFromItem;
    @WrapMethod(mcpName = "getItemFromBlock", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getItemFromBlock;

    public Item(Object obj) {
        super(obj);
    }

    public String getUnlocalizedName() {
        return (String) invoke(getUnlocalizedName);
    }

    public int getMaxDamage() {
        return (int) getField(maxDamage);
    }

    public static int getIdFromItem(Item item) {
        return (int) ReflectUtil.invoke(getIdFromItem, null, item.getWrappedObject());
    }
    public static Item getItemFromBlock(Block block) {
        return new Item(ReflectUtil.invoke(getItemFromBlock, null, block.getWrappedObject()));
    }
}
