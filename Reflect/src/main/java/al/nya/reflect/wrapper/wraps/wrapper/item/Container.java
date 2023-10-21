package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.inventory.Container",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.inventory.Container",targetMap = Maps.Srg1_12_2)
public class Container extends IWrapper {
    @WrapMethod(mcpName = "getSlot",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getSlot",targetMap = Maps.Srg1_12_2)
    public static Method getSlot;
    @WrapField(mcpName = "windowId",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "windowId",targetMap = Maps.Srg1_12_2)
    public static Field windowId;
    public Container(Object obj) {
        super(obj);
    }
    public Slot getSlot(int i){
        return new Slot(invoke(getSlot,i));
    }
    public int getWindowId(){
        return (int) getField(windowId);
    }
}
