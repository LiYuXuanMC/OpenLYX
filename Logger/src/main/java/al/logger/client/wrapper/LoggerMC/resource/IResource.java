package al.logger.client.wrapper.LoggerMC.resource;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.resources.IResource",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class IResource extends IWrapper {
    @ClassInstance
    public static Class<?> IResourceClass;
    @WrapMethod(mcpName = "getResourceLocation",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getResourceLocation;
    @WrapMethod(mcpName = "getInputStream",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getInputStream;
    @WrapMethod(mcpName = "hasMetadata",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method hasMetadata;
    @WrapMethod(mcpName = "getMetadata",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getMetadata;
    @WrapMethod(mcpName = "getResourcePackName",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getResourcePackName;

    public IResource(Object obj) {
        super(obj);
    }
}
