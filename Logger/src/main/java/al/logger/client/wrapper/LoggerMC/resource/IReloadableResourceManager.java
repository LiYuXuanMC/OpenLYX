package al.logger.client.wrapper.LoggerMC.resource;

import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

@WrapperClass(mcpName = "net.minecraft.client.resources.IReloadableResourceManager",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class IReloadableResourceManager extends IResourceManager{
    public IReloadableResourceManager(Object obj) {
        super(obj);
    }
}
