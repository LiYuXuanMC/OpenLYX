package al.logger.client.wrapper.LoggerMC.resource;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

@WrapperClass(mcpName = "net.minecraft.client.resources.data.IMetadataSection",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class IMetadataSection extends IWrapper {
    public IMetadataSection(Object obj) {
        super(obj);
    }
}
