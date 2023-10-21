package al.nya.reflect.wrapper.wraps.wrapper.utils;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Field;


@WrapperClass(mcpName = "net.minecraft.util.ResourceLocation",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.util.ResourceLocation",targetMap = Maps.Srg1_12_2)
public class ResourceLocation extends IWrapper {
    @WrapClass(mcpName = "net.minecraft.util.ResourceLocation",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.util.ResourceLocation",targetMap = Maps.Srg1_12_2)
    public static Class ResourceLocation;
    @WrapField(mcpName = "resourcePath",targetMap = Maps.Srg1_12_2)
    public static Field resourcePath;
    public ResourceLocation(Object obj) {
        super(obj);
    }
    public ResourceLocation(String resourceName)
    {
        this(ReflectUtil.construction(ResourceLocation,resourceName));
    }

    public ResourceLocation(String resourceDomainIn, String resourcePathIn)
    {
        this(ReflectUtil.construction(ResourceLocation,resourceDomainIn,resourcePathIn));
    }
    public String getResourcePath(){
        return (String) getField(resourcePath);
    }
}
