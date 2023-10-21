package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.inventory.ContainerChest",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.inventory.ContainerChest",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ContainerChest extends Container{
@ClassInstance    
public static Class ContainerChestClass;
    @WrapField(mcpName = "lowerChestInventory",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "lowerChestInventory",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field lowerChestInventory;
    public ContainerChest(Object obj) {
        super(obj);
    }
    public static boolean isContainerChest(Container container){
        return ContainerChestClass.isInstance(container.getWrappedObject());
    }
    public IInventory getLowerChestInventory(){
        return new IInventory(getField(lowerChestInventory));
    }
}
