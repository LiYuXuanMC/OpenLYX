package al.logger.client.wrapper.LoggerMC.network;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
@WrapperClass(mcpName = "net.minecraft.network.INetHandler",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class INetHandler extends IWrapper {
    public INetHandler(Object obj) {
        super(obj);
    }
}
