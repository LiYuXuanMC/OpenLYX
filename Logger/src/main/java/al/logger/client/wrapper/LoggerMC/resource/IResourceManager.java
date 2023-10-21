package al.logger.client.wrapper.LoggerMC.resource;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.resources.IResourceManager",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class IResourceManager extends IWrapper {
    @ClassInstance
    public static Class<?> IResourceManagerClass;
    @WrapMethod(mcpName = "getResourceDomains",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getResourceDomains;
    @WrapMethod(mcpName = "getResource",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getResource;
    @WrapMethod(mcpName = "getAllResources",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getAllResources;
    public IResourceManager(Object obj) {
        super(obj);
    }
}
