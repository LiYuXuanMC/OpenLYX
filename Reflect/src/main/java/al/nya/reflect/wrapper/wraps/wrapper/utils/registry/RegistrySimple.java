package al.nya.reflect.wrapper.wraps.wrapper.utils.registry;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;
import al.nya.reflect.wrapper.wraps.wrapper.enchantment.Enchantment;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ResourceLocation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.util.registry.RegistrySimple",targetMap = Maps.Srg1_12_2)
public class RegistrySimple extends IWrapper {
    @WrapField(mcpName = "registryObjects",targetMap = Maps.Srg1_12_2)
    public static Field registryObjects;
    public RegistrySimple(Object obj) {
        super(obj);
    }
    public Map getRegistryObjects(){
        return (Map) getField(registryObjects);
    }
}
