package al.logger.client.wrapper.LoggerMC.resource;

import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Field;
import java.util.Map;

@WrapperClass(mcpName = "net.minecraft.client.resources.SimpleReloadableResourceManager",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class SimpleReloadableResourceManager extends IReloadableResourceManager{
    @WrapField(mcpName = "domainResourceManagers", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field domainResourceManagers;
    public SimpleReloadableResourceManager(Object obj) {
        super(obj);
    }
    public Map getDomainResourceManagers(){
        return (Map) getField(domainResourceManagers);
    }
}
