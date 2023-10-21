package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.world.IWorldNameable;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.inventory.IInventory",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.inventory.IInventory",targetMap = Maps.Srg1_12_2)
public class IInventory extends IWorldNameable {
    @WrapMethod(mcpName = "getSizeInventory",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getSizeInventory",targetMap = Maps.Srg1_12_2)
    public static Method getSizeInventory;
    @WrapMethod(mcpName = "getStackInSlot",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getStackInSlot",targetMap = Maps.Srg1_12_2)
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
