package al.logger.client.wrapper.LoggerMC.item;


import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.IBlockState;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

@WrapperClass(mcpName = "net.minecraft.item.ItemStack",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemStack",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemStack extends IWrapper {
    @WrapField(mcpName = "item",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "item",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field item;
    @WrapMethod(mcpName = "getMaxItemUseDuration",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getMaxItemUseDuration",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getMaxItemUseDuration;
@ClassInstance    
public static Class ItemStackClass;
    @WrapField(mcpName = "stackSize",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "stackSize",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field stackSize;
    @WrapMethod(mcpName = "getUnlocalizedName",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getUnlocalizedName",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getUnlocalizedName;
    @WrapMethod(mcpName = "getStrVsBlock",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getDestroySpeed",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getStrVsBlock;
    @WrapMethod(mcpName = "getDisplayName",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getDisplayName",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getDisplayName;
    @WrapMethod(mcpName = "getMetadata",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getMetadata",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getMetadata;
    @WrapField(mcpName = "itemDamage",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "itemDamage",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field itemDamage;
    public ItemStack(Object obj) {
        super(obj);
    }
    public Item getItem(){
        return new Item(getField(item));
    }
    public int getMaxItemUseDuration(){
        return (int) invoke(getMaxItemUseDuration);
    }
    public int getStackSize(){
        return (int) getField(stackSize);
    }
    public String getUnlocalizedName(){
        return (String) invoke(getUnlocalizedName);
    }
    public float getStrVsBlock(Block block){
        return (float) invoke(getStrVsBlock,block.getWrappedObject());
    }
    public float getDestroySpeed(IBlockState block){
        return (float) invoke(getStrVsBlock,block.getWrappedObject());
    }
    public String getDisplayName(){
        return (String) invoke(getDisplayName);
    }
    public int getItemDamage(){
        return (int) getField(itemDamage);
    }

    public int getMetadata(){
        return (int) invoke(getMetadata);
    }
}
