package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.inventory.Slot",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.inventory.Slot",targetMap = Maps.Srg1_12_2)
public class Slot extends IWrapper {
    @WrapMethod(mcpName = "getHasStack",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getHasStack",targetMap = Maps.Srg1_12_2)
    public static Method getHasStack;
    @WrapMethod(mcpName = "getStack",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getStack",targetMap = Maps.Srg1_12_2)
    public static Method getStack;
    public Slot(Object obj) {
        super(obj);
    }
    public boolean getHasStack(){
        return (boolean) invoke(getHasStack);
    }
    public ItemStack getStack(){
        return new ItemStack(invoke(getStack));
    }
}
