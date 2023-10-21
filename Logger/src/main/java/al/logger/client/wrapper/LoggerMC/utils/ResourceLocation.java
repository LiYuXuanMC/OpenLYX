package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.annotations.WrapConstructor;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


@WrapperClass(mcpName = "net.minecraft.util.ResourceLocation",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.ResourceLocation",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ResourceLocation extends IWrapper {

    @ClassInstance
    public static Class ResourceLocation;
    @WrapField(mcpName = "resourcePath",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field resourcePath;
    @WrapConstructor(signature = {String.class},targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Constructor<?> ResourceLocation_String;
    @WrapConstructor(signature = {String.class,String.class},targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Constructor<?> ResourceLocation_String_String;
    public ResourceLocation(Object obj) {
        super(obj);
    }
    public ResourceLocation(String resourceName)
    {
        this(construction(ResourceLocation_String,resourceName));
    }

    public ResourceLocation(String resourceDomainIn, String resourcePathIn)
    {
        this(construction(ResourceLocation_String_String,resourceDomainIn,resourcePathIn));
    }
    public String getResourcePath(){
        return (String) getField(resourcePath);
    }
}
