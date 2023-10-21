package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.block.IBlockState;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.item.ItemTool",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemTool",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemTool extends Item{
    @WrapField(mcpName = "toolMaterial",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "toolMaterial",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field toolMaterial;
@ClassInstance    
public static Class ItemToolClass;
    @WrapMethod(mcpName = "getStrVsBlock",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getDestroySpeed",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getStrVsBlock;
    public ItemTool(Object obj) {
        super(obj);
    }
    public Enum getToolMaterial(){
        return (Enum) getField(toolMaterial);
    }
    public static boolean isItemTool(Item item){
        return ItemToolClass.isInstance(item.getWrappedObject());
    }
    public float getStrVsBlock(ItemStack itemStack, Block block) {
        return (float) invoke(getStrVsBlock,itemStack.getWrappedObject(),block.getWrappedObject());
    }
    public float getDestroySpeed(ItemStack itemStack, IBlockState block) {
        return (float) invoke(getStrVsBlock,itemStack.getWrappedObject(),block.getWrappedObject());
    }
}
