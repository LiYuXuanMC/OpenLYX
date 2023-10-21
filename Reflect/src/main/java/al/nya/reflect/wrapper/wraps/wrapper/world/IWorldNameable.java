package al.nya.reflect.wrapper.wraps.wrapper.world;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.world.IWorldNameable",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.world.IWorldNameable",targetMap = Maps.Srg1_12_2)
public class IWorldNameable extends IWrapper {
    @WrapMethod(mcpName = "getDisplayName",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getDisplayName",targetMap = Maps.Srg1_12_2)
    public static Method getDisplayName;
    public IWorldNameable(Object obj) {
        super(obj);
    }
    public IChatComponent getDisplayName(){
        return new IChatComponent(invoke(getDisplayName));
    }
}
