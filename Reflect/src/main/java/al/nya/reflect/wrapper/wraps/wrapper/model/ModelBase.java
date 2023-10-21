package al.nya.reflect.wrapper.wraps.wrapper.model;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.client.model.ModelBase", targetMap = Maps.Srg1_12_2)
@WrapperClass(mcpName = "net.minecraft.client.model.ModelBase", targetMap = Maps.Srg1_8_9)
public class ModelBase extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.client.model.ModelBase", targetMap = Maps.Srg1_12_2)
    @WrapClass(mcpName = "net.minecraft.client.model.ModelBase", targetMap = Maps.Srg1_8_9)
    public static Class<?> ModelBaseClass;
    public ModelBase(Object obj) {
        super(obj);
    }
}
