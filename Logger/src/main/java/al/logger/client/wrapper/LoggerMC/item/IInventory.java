package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.world.IWorldNameable;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.inventory.IInventory",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.inventory.IInventory",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class IInventory extends IWorldNameable {
    @WrapMethod(mcpName = "getSizeInventory",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getSizeInventory",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getSizeInventory;
    @WrapMethod(mcpName = "getStackInSlot",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getStackInSlot",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getStackInSlot;
    public IInventory(Object obj) {
        super(obj);
    }
    public int getSizeInventory(){
        return (int) invoke(getSizeInventory);
    }
    public ItemStack getStackInSlot(int slot){
        return new ItemStack(invoke(getStackInSlot,slot));
    }
}
