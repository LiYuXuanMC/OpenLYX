package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.inventory.ContainerChest",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.inventory.ContainerChest",targetMap = Maps.Srg1_12_2)
public class ContainerChest extends Container{
    @WrapClass(mcpName = "net.minecraft.inventory.ContainerChest",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.inventory.ContainerChest",targetMap = Maps.Srg1_12_2)
    public static Class ContainerChestClass;
    @WrapField(mcpName = "lowerChestInventory",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "lowerChestInventory",targetMap = Maps.Srg1_12_2)
    public static Field lowerChestInventory;
    public ContainerChest(Object obj) {
        super(obj);
    }
    public static boolean isContainerChest(Container container){
        return ContainerChestClass.isInstance(container.getWrapObject());
    }
    public IInventory getLowerChestInventory(){
        return new IInventory(getField(lowerChestInventory));
    }
}
