package al.logger.client.wrapper.LoggerMC.network;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.network.play.INetHandlerPlayClient",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class INetHandlerPlayClient extends INetHandler{
    public INetHandlerPlayClient(Object obj) {
        super(obj);
    }
}
