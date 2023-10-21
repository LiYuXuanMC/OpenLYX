package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.entity.passive.IAnimals", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.entity.passive.IAnimals", targetMap = Maps.Srg1_12_2)
public class IAnimals extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.entity.passive.IAnimals", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.entity.passive.IAnimals", targetMap = Maps.Srg1_12_2)
    public static Class IAnimalsClass;
    public IAnimals(Object obj) {
        super(obj);
    }
    public static boolean isIAnimals(Entity o){
        return IAnimalsClass.isInstance(o.getWrapObject());
    }
}
